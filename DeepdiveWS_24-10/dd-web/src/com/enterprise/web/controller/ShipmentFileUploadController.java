package com.enterprise.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.enterprise.businessservices.FileUploadBusinessService;
import com.enterprise.common.exception.DataUploadException;
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.common.pojo.AsyncSessionObject;
import com.enterprise.common.pojo.FileUploadForm;
import com.enterprise.common.pojo.FileUploadSessionBean;
import com.enterprise.common.util.DateUtils;
import com.enterprise.domain.entity.Package;
import com.enterprise.domain.entity.SubPackageDetail;
import com.enterprise.web.util.ProcessFile;
import com.enterprise.web.util.ProcessXLS;
import com.enterprise.web.util.ProcessXml;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  10 Mar 2014 4:49:11 PM
 * @author rafourie
 */
@Controller
@SessionAttributes({"fileUploadSessionBean", "asyncSessionObject"})
public class ShipmentFileUploadController implements IFileUploadController {
	@Autowired
	private FileUploadBusinessService fileUploadBusinessService;
	
	private long pkgID;
	private long subPkgID;
	
	@RequestMapping(value = "/upload/shipment/save", method = RequestMethod.POST)
    public String uploadModuleSaveRequest(
    		@ModelAttribute("shipmentUploadForm") FileUploadForm fileUploadForm, 
    		@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean fileUploadSessionBean,
    		Model model) {
		fileUploadSessionBean.reset();
		if(fileUploadSessionBean.getLoadType() == 1) {
			fileUploadSessionBean.addInfoMsg("To upload shipment records successfully, each record must have at least a Mode" +  
					"('A' / 'O') and a Shipment Number (5100946238).");
		} else if(fileUploadSessionBean.getLoadType() == 2) {
			fileUploadSessionBean.addInfoMsg("To upload Rate records successfully, each record must have at least a Mode" +  
					"('A' / 'O'), Origin Code (BOM) and Destination Code(SYD).");
		} else if(fileUploadSessionBean.getLoadType() == 3) {
			fileUploadSessionBean.addInfoMsg("To upload Invoice records successfully, each record must have at least a Shipment Number" +  
					"(25000089210) and Invoice Number (25000089210).");
		} else if(fileUploadSessionBean.getLoadType() == 4) {
			fileUploadSessionBean.addInfoMsg("To upload Invoice records successfully, each record must have at least a " +  
					"Order Number(TCVP12272).");
		}else if(fileUploadSessionBean.getLoadType() == 5){
			fileUploadSessionBean.addInfoMsg("To upload Custom records successfully, each record must have at least a " +  
			"Mode('A' / 'O'), Part Number(TCVP12272) and Shipment Number (5100946238).");
		}else if(fileUploadSessionBean.getLoadType() == 6){
			fileUploadSessionBean.addInfoMsg("To upload Container status records successfully, each record must have at least a " +  
			"Dest Port(AUFRE) and Container Number (CMAU2095859).");
		}
		fileUploadSessionBean.addInfoMsg("Should the system not be able to upload records, warnings will be visible as " + 
				"the result.");
		MultipartFile multipartFile = fileUploadForm.getFile();
        String fileName = "";
        if (multipartFile != null) {
            fileName = multipartFile.getOriginalFilename();
            fileUploadSessionBean.setFileName(fileName);
            try {
            	byte[] fileContentAsByteArr = multipartFile.getBytes();
            	if (fileContentAsByteArr == null || fileContentAsByteArr.length == 0)
            		model.addAttribute("error", "Please select valid file.");
            	else {
            		if(multipartFile.getContentType().equalsIgnoreCase("text/xml")){
                		fileUploadSessionBean.setContent(ProcessXml.processXml(multipartFile.getInputStream()));
                	} else {
                		String fileContentAsStr = new String(fileContentAsByteArr, "UTF-8");
                		fileUploadSessionBean.setContent(fileContentAsStr);
                	}
            		SubPackageDetail subPackageDetail= fileUploadBusinessService.getSubPackage(subPkgID);
            		if(subPackageDetail!=null){
            			fileUploadSessionBean.setAliaseMatchingEnabled(subPackageDetail.isAlias());
            			fileUploadSessionBean.setLocationAliaseEnabled(subPackageDetail.isLocationAlias());
            			fileUploadSessionBean.setSpecialChar(subPackageDetail.isSpecialChar());
            		}
            		fileUploadSessionBean.setRequestMapping("/upload/shipment/process");
            		fileUploadSessionBean.setJavascript("ajax('ajaxModalPageDiv','" + fileUploadSessionBean.getRequestMapping() + "');");
                    return "upload/redirect";
            	}
            } catch (IOException e) {
            	e.printStackTrace();
            	model.addAttribute("error", "There was an exception reading the file contents.");
            } catch (Exception e) {
            	e.printStackTrace();
            	model.addAttribute("error", "There was an exception reading the file contents.");
            }
        } else
        	model.addAttribute("error", "Please select valid file.");
        return this.uploadModuleFormRequest(model);
    }
	
	@RequestMapping(value = "/upload/shipment/process", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadModuleProcessRequest(@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean bean, Model model) {
		String fileName = bean.getFileName();
		model.addAttribute("files", fileName);
		String fileContent = bean.getContent();
		String textStr[] = fileContent.split("\\r?\\n");
		bean.setTotalLines(textStr.length - 1);
		bean.setBatchSize(64);
		bean.setUuid(UUID.randomUUID());
		bean.setFileProcessed(false);
		try {
			if(bean.isSpecialChar())
				textStr = ProcessFile.checkForQuotes(textStr);
			
			if(bean.getLoadType() == 1)
				bean = fileUploadBusinessService.processShipmentFileContent(textStr, bean);
			else if(bean.getLoadType() == 2){
				if(bean.isRateProfileReset())
					fileUploadBusinessService.resetRateProfile(bean.getPackageId());
				bean = fileUploadBusinessService.processRateFileContent(textStr, bean);
			}
			else if(bean.getLoadType() == 3)
				bean = fileUploadBusinessService.processInvoiceFileContent(textStr, bean);
			else if(bean.getLoadType() == 4)
				bean = fileUploadBusinessService.processOrderFileContent(textStr, bean);
			else if(bean.getLoadType() == 5){
				bean = fileUploadBusinessService.processCustomFileContent(textStr, bean);
			} else if(bean.getLoadType() == 6){
				bean = fileUploadBusinessService.processContainerStatusFileContent(textStr, bean);
			}
			
			if (!bean.isFileProcessed())
				bean.setJavascript("parent.ajax('ajaxAsyncDiv','/upload/shipment/processing');");
			else {
				if(bean.getCurrentLine() > 0) {
					fileUploadBusinessService.saveLoadHistory(bean.getSubPackageId(), bean.getSuccessShipments(), bean.getFailedShipment(), bean.getLoadType(), bean.getFileName());
				}
				bean.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
			}
		} catch (DataUploadWarning e) {
			bean.addWarningMsg(e.getMessage());
			bean.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/shipment/processing');");
		} catch (DataUploadException e) {
			bean.addErrorMsg(e.getLocalizedMessage());
			bean.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "upload/processing";
	}
	
	@RequestMapping(value="/upload/shipment/form", method = {RequestMethod.POST, RequestMethod.GET})
	public String uploadModuleFormRequest(Model model) {
		return "upload/shipment/form";
	}
	
	@RequestMapping(value = "/upload/shipment/processing", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadShipmentProcessingRequest(
    		@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean bean, 
    		@ModelAttribute("asyncSessionObject") AsyncSessionObject asyncSessionObj,
    		Model model) {
		String fileContent = bean.getContent();
		String textStr[] = fileContent.split("\\r?\\n");
		try {
			if(bean.getLoadType() == 1) 
				bean = fileUploadBusinessService.processShipmentFileContent(textStr, bean);
			else if(bean.getLoadType() == 2) 
				bean = fileUploadBusinessService.processRateFileContent(textStr, bean);
			else if(bean.getLoadType() == 3)
				bean = fileUploadBusinessService.processInvoiceFileContent(textStr, bean);
			else if(bean.getLoadType() == 4)
				bean = fileUploadBusinessService.processOrderFileContent(textStr, bean);
			else if(bean.getLoadType() == 5){
				bean = fileUploadBusinessService.processCustomFileContent(textStr, bean);
			} else if(bean.getLoadType() == 6){
				bean = fileUploadBusinessService.processContainerStatusFileContent(textStr, bean);
			}
			
			if (!bean.isFileProcessed())
				asyncSessionObj.setJavascript("parent.ajax('ajaxAsyncDiv','/upload/shipment/processing');");
			else {
				fileUploadBusinessService.saveLoadHistory(bean.getSubPackageId(), bean.getSuccessShipments(), bean.getFailedShipment(), bean.getLoadType(), bean.getFileName());
				asyncSessionObj.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
			}
		} catch (DataUploadWarning e) {
			bean.addWarningMsg(e.getMessage());
			asyncSessionObj.setJavascript("parent.ajax('ajaxAsyncDiv','/upload/shipment/processing');");
		} catch (Exception e) {
			asyncSessionObj.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
			bean.addErrorMsg("Processing failed at line " + (bean.getCurrentLine() + 1) + " of the file.");
			e.printStackTrace();
		}
		return "async";
	}
	
	@RequestMapping(value = "/packageUpload", method = RequestMethod.POST)
    public @ResponseBody String packageUpload(@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean fileUploadSessionBean,
    		@RequestParam(value = "subPackageId", required = false) long subPackageId) {
		setSubPkgID(subPackageId);
		fileUploadSessionBean.setPackageId(getPkgID());
		fileUploadSessionBean.setSubPackageId(subPackageId);
		fileUploadSessionBean.setAliaseMatchingEnabled(false);
		fileUploadSessionBean.setConflictChecked(false);
		fileUploadSessionBean.setRateProfileReset(false);
		fileUploadSessionBean.setLoadType(1);
		try {
			SubPackageDetail subPkg = fileUploadBusinessService.getSubPackage(subPackageId);
			if(subPkg==null){
				return "redirect:/upload/shipment/form";
			}
			fileUploadSessionBean.setPkgName(subPkg.getLoadType().getLoad());
			fileUploadSessionBean.setPackageId(subPkg.getPkg().getId());
			fileUploadSessionBean.setLastEditUser(subPkg.getLastEditedUser().getFirstName());
			fileUploadSessionBean.setLastUpdateOn(DateUtils.toString(subPkg.getLastUpdated(), "MMM d, yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/upload/shipment/form";
	}
	
	@RequestMapping(value = "/setLoadType", method = RequestMethod.POST)
    public @ResponseBody String setLoadType(@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean fileUploadSessionBean,
    		@RequestParam(value = "loadType", required = true) int loadType) {
		fileUploadSessionBean.setLoadType(loadType);
		return "Success";
	}
	
	@RequestMapping(value = "/setAliasMapping", method = RequestMethod.POST)
    public @ResponseBody String setAliasMapping(@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean fileUploadSessionBean,
    		@RequestParam(value = "isChkd", required = true) boolean isChkd) {
		fileUploadSessionBean.setAliaseMatchingEnabled(isChkd);
		return "Success";
	}
	
	@RequestMapping(value = "/setConflictMerging", method = RequestMethod.POST)
    public @ResponseBody String setConflictMerging(@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean fileUploadSessionBean,
    		@RequestParam(value = "isChkd", required = true) boolean isChkd) {
		fileUploadSessionBean.setConflictChecked(isChkd);
		return "Success";
	}
	
	@RequestMapping(value = "/resetRateProfile", method = RequestMethod.POST)
    public @ResponseBody String resetRateProfile(@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean fileUploadSessionBean,
    		@RequestParam(value = "isChkd", required = true) boolean isChkd) {
		fileUploadSessionBean.setRateProfileReset(isChkd);
		return "Success";
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String test(Model model) {
		return "test";
	}
	
	@RequestMapping(value = "/emailLoad", method = RequestMethod.POST)
	public String emailLoad(MultipartHttpServletRequest request,
    		@ModelAttribute("shipmentUploadForm") FileUploadForm fileUploadForm, 
    		@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean fileUploadSessionBean,
    		Model model) {
		String message = "success";
		try {
			long userId = Long.parseLong(request.getParameter("userId"));
			long packageId = Long.parseLong(request.getParameter("packageId"));
			int removedRows = Integer.parseInt(request.getParameter("rowsToRemove"));
			
			//String path = "/home/cynere/Documents/PROJECT_FILES/DEEPDIVE/";
			SubPackageDetail detail = new SubPackageDetail();
			detail.setRowsToRemove(removedRows);
			detail.setColumnsToRemove(2);
			MultipartFile multipartFile = fileUploadForm.getFile();
			InputStream fis = multipartFile.getInputStream();
			/*UploadFile uploadFile = new UploadFile();
			uploadFile.uploadImage(path+"Rate"+userId+".xlsx", multipartFile);*/
			String textStr[] = ProcessXLS.processXLSFile(detail, userId, fis);
			
			//File file = new File(path+"Rate"+userId+".csv");
		    
			fileUploadSessionBean.reset();
	        String fileName = multipartFile.getOriginalFilename();
            fileUploadSessionBean.setFileName(fileName);
            fileUploadSessionBean.setPackageId(packageId);
            com.enterprise.domain.entity.Package pkg = fileUploadBusinessService.getPackage(packageId);
			fileUploadSessionBean.setPkgName(pkg.getName());
			fileUploadSessionBean.setLastEditUser(pkg.getLastEditedUser().getFirstName()+ " " +pkg.getLastEditedUser().getLastName());
			fileUploadSessionBean.setLastUpdateOn(DateUtils.toString(pkg.getLastUpdated(), "MMM d, yyyy"));
			fileUploadSessionBean.setUserId(userId);
			
        	/*byte[] fileContentAsByteArr = multipartFile.getBytes();
        	byte[] fileContentAsByteArr = new byte[(int)file.length()];
        	fileContentAsByteArr = FileUtils.readFileToByteArray(file); */
        	if (multipartFile == null || multipartFile.isEmpty())
        		model.addAttribute("error", "Please select valid file.");
        	else {
        		//String fileContentAsStr = new String(fileContentAsByteArr);
        		//fileUploadSessionBean.setContent(fileContentAsStr);
        		model.addAttribute("files", multipartFile.getName());
        		//String fileContent = fileUploadSessionBean.getContent();
        		//String textStr[] = fileContent.split("\\r?\\n");
        		fileUploadSessionBean.setTotalLines(textStr.length - 1);
        		fileUploadSessionBean.setBatchSize(64);
        		fileUploadSessionBean.setUuid(UUID.randomUUID());
        		fileUploadSessionBean.setFileProcessed(false);
        		fileUploadSessionBean.setLoadType(2);
        		fileUploadSessionBean.setRequestMapping("/upload/shipment/process");
        		fileUploadSessionBean.setJavascript("ajax('ajaxModalPageDiv','" + fileUploadSessionBean.getRequestMapping() + "');");
        		fileUploadSessionBean = fileUploadBusinessService.processRateFileContent(textStr, fileUploadSessionBean);
        		fileUploadSessionBean = fileUploadBusinessService.processRateFileContent(textStr, fileUploadSessionBean);
        		if (fileUploadSessionBean.isFileProcessed()){
        			
    				//fileUploadBusinessService.saveLoadHistory(fileUploadSessionBean.getPackageId(), fileUploadSessionBean.getSuccessShipments(), fileUploadSessionBean.getFailedShipment(), fileUploadSessionBean.getLoadType());
    			}
        	}
		} catch (IOException e) {
        	e.printStackTrace();
        	message = "error";
        } catch (Exception e) {
			e.printStackTrace();
			message = "error";
		}
		return "redirect:/test?message="+message;
	}
	
	@RequestMapping(value = "/setPackageID", method = RequestMethod.POST)
    public @ResponseBody String setPackageID(@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean fileUploadSessionBean,
    		@RequestParam(value = "packageId", required = true) long packageId) {
		setPkgID(packageId);
		return "Success";
	}
	
	@RequestMapping(value = "/getLoadTypes", method = RequestMethod.POST)
    public @ResponseBody String getLoadTypes(@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean fileUploadSessionBean) {
		long pkgID = getPkgID();
		long subPkgID = getSubPkgID();
		String jsonList = "";
		String currentLoadType = "Shipment";
		try {
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonArr = new JSONArray();
			List<String> loadTypes = fileUploadBusinessService.getLoadTypes(pkgID);
			Package pkg = fileUploadBusinessService.getPackage(pkgID);
			if(subPkgID!=0) {
				SubPackageDetail subPackageDetail= fileUploadBusinessService.getSubPackage(subPkgID);
				currentLoadType = subPackageDetail.getLoadType().getLoad();
			}
			for (String loadType : loadTypes) {
				jsonArr.put(loadType);
			}
			jsonObj.put("loadType", jsonArr);
			jsonObj.put("subPkgID", subPkgID);
			jsonObj.put("currentLoadType", currentLoadType);
			fileUploadSessionBean.setPkgName(pkg.getName());
			fileUploadSessionBean.setLastEditUser(pkg.getLastEditedUser().getFirstName()+ " " +pkg.getLastEditedUser().getLastName());
			fileUploadSessionBean.setLastUpdateOn(DateUtils.toString(pkg.getLastUpdated(), "MMM d, yyyy"));
			
			jsonList = jsonObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonList;
	}

	public long getPkgID() {
		return pkgID;
	}

	public void setPkgID(long pkgID) {
		this.pkgID = pkgID;
	}

	public long getSubPkgID() {
		return subPkgID;
	}

	public void setSubPkgID(long subPkgID) {
		this.subPkgID = subPkgID;
	}
}