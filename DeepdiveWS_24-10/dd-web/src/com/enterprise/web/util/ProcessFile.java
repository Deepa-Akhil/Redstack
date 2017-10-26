package com.enterprise.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.enterprise.businessservices.FileUploadBusinessService;
import com.enterprise.common.enums.LoadType;
import com.enterprise.common.exception.DataUploadException;
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.common.pojo.FileUploadSessionBean;
import com.enterprise.common.util.DateUtils;
import com.enterprise.common.util.StringUtils;
import com.enterprise.domain.entity.SubPackageDetail;
import com.enterprise.domain.entity.User;

@Component
public class ProcessFile {
	
	@Autowired
	private FileUploadBusinessService fileUploadBusinessService;
	
	@SuppressWarnings("rawtypes")
	public String LoadFile(long subPackageId, File file, String fileName) throws IOException {
		String message = "success";
		FileInputStream fis = new FileInputStream(file);
		try {
			SubPackageDetail subPkg = fileUploadBusinessService.getSubPackage(subPackageId);
			if(subPkg==null){
				return "error";
			}
			long packageId = subPkg.getPkg().getId();
			User user = fileUploadBusinessService.getUser(subPkg.getEmailId());
			if(null==user){
				user = fileUploadBusinessService.saveNewEmailUser(subPkg.getEmailId(), packageId);
			}
			FileUploadSessionBean fileUploadSessionBean = new FileUploadSessionBean();
			String textStr[] = null;
			String mimeType = FilenameUtils.getExtension(fileName);
			if (mimeType.equalsIgnoreCase("csv")) {
				textStr = IOUtils.toString(fis, "UTF-8").split("\\r?\\n");
				fileUploadSessionBean.setCSVFile(true);
			} else if(mimeType.equalsIgnoreCase("xls") || mimeType.equalsIgnoreCase("xlsx")){
				textStr = ProcessXLS.processGeneralXLSFile(subPkg, user.getId(), fis);
			}
			if(textStr==null || textStr.length==0){
				return "error";
			}
			fileUploadSessionBean.reset();
			fileUploadSessionBean.setSubPackageId(subPackageId);
			fileUploadSessionBean.setFileName(fileName);
            fileUploadSessionBean.setPackageId(packageId);
            com.enterprise.domain.entity.Package pkg = fileUploadBusinessService.getPackage(packageId);
			fileUploadSessionBean.setPkgName(pkg.getName());
			fileUploadSessionBean.setLastEditUser(pkg.getLastEditedUser().getFirstName()+ " " +pkg.getLastEditedUser().getLastName());
			fileUploadSessionBean.setLastUpdateOn(DateUtils.toString(pkg.getLastUpdated(), "MMM d, yyyy"));
			fileUploadSessionBean.setUserId(user.getId());
			
    		fileUploadSessionBean.setTotalLines(textStr.length - 1);
    		fileUploadSessionBean.setBatchSize(textStr.length);
    		fileUploadSessionBean.setUuid(UUID.randomUUID());
    		fileUploadSessionBean.setFileProcessed(false);
    		
    		fileUploadSessionBean.setMailProcess(true);
    		fileUploadSessionBean.setAliaseMatchingEnabled(subPkg.isAlias());
    		LoadType loadType = subPkg.getLoadType();
    		
    		switch (loadType) {
	    		case S:
	    			fileUploadSessionBean = processShipmentFileContent(fileUploadSessionBean, textStr);
	    			break;
	    		case O:
	    			fileUploadSessionBean = processOrderFileContent(fileUploadSessionBean, textStr);
	    			break;
	    		case R:
	    			fileUploadSessionBean = processRateFileContent(fileUploadSessionBean, textStr);
	    			break;
	    		case CS:
	    			fileUploadSessionBean = processContainerStatusFileContent(fileUploadSessionBean, textStr);
	    			break;
	    		case C:
	    			fileUploadSessionBean = processCustomsFileContent(fileUploadSessionBean, textStr);
	    			break;
	    		case I:
	    			fileUploadSessionBean = processInvoiceFileContent(fileUploadSessionBean, textStr);
	    			break;
	    		default:
	    			break;
    		}
    		if (fileUploadSessionBean.isFileProcessed()){
    			fileUploadBusinessService.saveLoadHistory(subPackageId, fileUploadSessionBean.getSuccessShipments(), fileUploadSessionBean.getFailedShipment(), fileUploadSessionBean.getLoadType(), user.getId(), fileUploadSessionBean.getFileName());
			}
    		List infoMsg = fileUploadSessionBean.getInfoMsgList();
    		List errorOrSuccMsg = null;
    		String errorType = "Success";
    		if(fileUploadSessionBean.isHasErrors()){
    			errorType = "Errors";
    			errorOrSuccMsg = fileUploadSessionBean.getErrorList();
    		} else if(fileUploadSessionBean.isHasWarnings()){
    			errorType = "Warning";
    			errorOrSuccMsg = fileUploadSessionBean.getWarningList();
    		}
    		if(!StringUtils.isEmpty(subPkg.getEmailId())){
    			EmailOutBox.sendUserDetailMail1(subPkg.getEmailId(), user.getFirstName(), infoMsg,  errorOrSuccMsg, errorType, subPkg.getLoadType().getLoad(), pkg.getName(), pkg.getId());
    		}
		} catch (IOException e) {
        	e.printStackTrace();
        	message = "error";
        } catch (Exception e) {
			e.printStackTrace();
			message = "error";
		} finally {
			if(fis!=null) {
				fis.close();
			}
		}
		System.out.println("                 >>>>>>>>>>>>>>>>>>>>>>>>>>"+message);
		return message;
	}

	private FileUploadSessionBean processShipmentFileContent(FileUploadSessionBean fileUploadSessionBean, String[] textStr) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, DataUploadWarning, DataUploadException {
		fileUploadSessionBean.setLoadType(1);
		fileUploadSessionBean.setBatchSize(64 > textStr.length ? textStr.length-2:64);
		if(textStr.length==2){
			fileUploadSessionBean.setBatchSize(2);
		}
		while (!fileUploadSessionBean.isFileProcessed()) {
			fileUploadSessionBean = fileUploadBusinessService.processShipmentFileContent(textStr, fileUploadSessionBean);
		}
		return fileUploadSessionBean;
	}
	
	private FileUploadSessionBean processOrderFileContent(FileUploadSessionBean fileUploadSessionBean, String[] textStr) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, DataUploadWarning, DataUploadException {
		fileUploadSessionBean.setLoadType(4);
		fileUploadSessionBean.setBatchSize(64);
		while (!fileUploadSessionBean.isFileProcessed()) {
			fileUploadSessionBean = fileUploadBusinessService.processOrderFileContent(textStr, fileUploadSessionBean);
		}
		return fileUploadSessionBean;
	}
	
	private FileUploadSessionBean processRateFileContent(FileUploadSessionBean fileUploadSessionBean, String[] textStr) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, DataUploadWarning, DataUploadException {
		fileUploadSessionBean.setLoadType(2);
		fileUploadSessionBean.setBatchSize(64 > textStr.length ? textStr.length-2:64);
		while (!fileUploadSessionBean.isFileProcessed()) {
			fileUploadSessionBean = fileUploadBusinessService.processRateFileContent(textStr, fileUploadSessionBean);
		}
		return fileUploadSessionBean;
	}
	
	private FileUploadSessionBean processContainerStatusFileContent(FileUploadSessionBean fileUploadSessionBean, String[] textStr) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, DataUploadWarning, DataUploadException {
		fileUploadSessionBean.setLoadType(6);
		fileUploadSessionBean.setBatchSize(64 > textStr.length ? textStr.length-2:64);
		while (!fileUploadSessionBean.isFileProcessed()) {
			fileUploadSessionBean = fileUploadBusinessService.processContainerStatusFileContent(textStr, fileUploadSessionBean);
		}
		return fileUploadSessionBean;
	}
	
	private FileUploadSessionBean processCustomsFileContent(FileUploadSessionBean fileUploadSessionBean, String[] textStr) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, DataUploadWarning, DataUploadException {
		fileUploadSessionBean.setLoadType(5);
		fileUploadSessionBean.setBatchSize(64 > textStr.length ? textStr.length-2:64);
		while (!fileUploadSessionBean.isFileProcessed()) {
			fileUploadSessionBean = fileUploadBusinessService.processCustomFileContent(textStr, fileUploadSessionBean);
		}
		return fileUploadSessionBean;
	}
	
	private FileUploadSessionBean processInvoiceFileContent(FileUploadSessionBean fileUploadSessionBean, String[] textStr) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, DataUploadWarning, DataUploadException {
		fileUploadSessionBean.setLoadType(3);
		fileUploadSessionBean.setBatchSize(64);
		while (!fileUploadSessionBean.isFileProcessed()) {
			fileUploadSessionBean = fileUploadBusinessService.processInvoiceFileContent(textStr, fileUploadSessionBean);
		}
		return fileUploadSessionBean;
	}
	
	public static String[] checkForQuotes(String[] al) {
        ArrayList<String> parsedList = new ArrayList<String>();
        String correctedHeader = "";
        boolean isParseError = false;
        L1: for (String string : al) {
               if (isParseError) {
                     correctedHeader = correctedHeader + string;
               }
               else {
                     correctedHeader = string;
               }
               String[] datas = string.split(",");
               for (String string2 : datas) {
                     if (string2.startsWith("\"") && !string2.endsWith("\"")) {
                            correctedHeader = string;
                            isParseError = true;
                            continue L1;
                     }
                     if (string2.endsWith("\"") && isParseError) {
                            isParseError = false;
                     }
               }
               if (!isParseError) {
                     parsedList.add(correctedHeader);
               }
        }
        return parsedList.toArray(new String[0]);
 }
}
