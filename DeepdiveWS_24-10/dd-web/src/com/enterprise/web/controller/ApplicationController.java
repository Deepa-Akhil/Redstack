package com.enterprise.web.controller;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enterprise.businessservices.PackageBussinessService;
import com.enterprise.common.enums.ScheduleType;
import com.enterprise.common.util.DateUtils;
import com.enterprise.domain.entity.LoadHistory;
import com.enterprise.domain.entity.MenuFunction;
import com.enterprise.domain.entity.SubPackageDetail;
import com.enterprise.user.DeepDiveUser;
import com.enterprise.web.util.GetToken;

@Controller
public class ApplicationController {
	
	@Autowired
	private PackageBussinessService bussinessService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value="/embed", method = RequestMethod.GET)
	public String embed(Model model) throws ServletException {
		String op = GetToken.getTrustedTicket("http://analyticscloud.trade/trusted", "Unilever AU", "http://analyticscloud.trade/views/ActiveContainerManagement/ContainerDeliverySummary?:embed=y&:showAppBanner=false&:showShareOptions=true&:display_count=no&:showVizHome=no");
		model.addAttribute("tokenID", op);
		System.out.println("----------"+op);
		return "embed";
	}
	
	public static void main(String[] args) throws ServletException {
		String op = GetToken.getTrustedTicket("http://analyticscloud.trade/trusted", "Unilever AU", "http://analyticscloud.trade/views/ActiveContainerManagement/ContainerDeliverySummary?:embed=y&:showAppBanner=false&:showShareOptions=true&:display_count=no&:showVizHome=no");
		System.out.println("----------"+op);
	}
	
	@RequestMapping(value="/client", method = RequestMethod.GET)
	public String client(@RequestParam(value = "menuID", required = false) String menuID, Model model) throws ServletException {
		String op1 = null;
		String name =null;
		List<MenuFunction> menus = null;
		try {
			menus = bussinessService.getMenus();
			//op1 = GetToken.getTrustedTicket("http://analyticscloud.trade/trusted", "Unilever AU", "http://analyticscloud.trade/views/ActiveContainerManagement/ContainerDeliverySummary?:embed=y&:showAppBanner=false&:showShareOptions=true&:display_count=no&:showVizHome=no");
			
			/*if(Integer.parseInt(menuID)==2){
				model.addAttribute("tokenID", op2);
				model.addAttribute("name", "ContainerHistory/ContainerManagementSummary");
			} else {
				model.addAttribute("tokenID", op1);
				model.addAttribute("name", "ActiveContainerManagement/ContainerDeliverySummary");
			}*/
			model.addAttribute("menus", menus);
			if(menus!=null && !menus.isEmpty()){
				MenuFunction menuFunction = menus.get(Integer.parseInt(menuID));
				name = menuFunction.getMenuURL();
				op1 = GetToken.getTrustedTicket("http://analyticscloud.trade/trusted", "Unilever AU", "http://analyticscloud.trade/views/"+name+"?:embed=y&:showAppBanner=false&:showShareOptions=true&:display_count=no&:showVizHome=no");
			}
		} catch (Exception e) {
			try {
				MenuFunction menuFunction = menus.get(0);
				name = menuFunction.getMenuURL();
				op1 = GetToken.getTrustedTicket("http://analyticscloud.trade/trusted", "Unilever AU", "http://analyticscloud.trade/views/"+menuFunction.getMenuURL()+"?:embed=y&:showAppBanner=false&:showShareOptions=true&:display_count=no&:showVizHome=no");
			} catch (Exception e1) {
				System.out.println("-------   Error on getting Token/Menu  --------");
			}
		}
		System.out.println("-------   TokenID  --------$"+op1);
		System.out.println("-------   Name  --------$"+name);
		model.addAttribute("tokenID", op1);
		model.addAttribute("name", name);
		getUserSession(model);
		return "client/index";
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index(Model model) {
		List<com.enterprise.domain.entity.Package> packages = null;
		try {
			packages = bussinessService.getPackages();
			getUserSession(model);
		} catch (Exception e){
			e.printStackTrace();
		}
		model.addAttribute("packages", packages);
		return "index";
	}
	
	@RequestMapping(value = "/upload/shipment/savePackage", method = RequestMethod.POST)
    public String savePackage(@ModelAttribute("pkg") com.enterprise.domain.entity.Package pkg, 
    		Model model) {
		try {
			bussinessService.savePackage(pkg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/movePackageArcheived", method = RequestMethod.POST)
    public @ResponseBody String movePackageArcheived(@RequestParam(value = "packageId", required = true) long packageId) {
		try {
			bussinessService.movePackageArcheived(packageId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
	}
	
	@RequestMapping(value = "/deleteSubPkg", method = RequestMethod.POST)
    public @ResponseBody String deleteSubPkg(@RequestParam(value = "subPkgId", required = true) long subPkgId) {
		try {
			bussinessService.deleteSubPkg(subPkgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
	}
	
	@RequestMapping(value = "/getLoadHistory", method = RequestMethod.POST)
    public @ResponseBody String getLoadHistory(@RequestParam(value = "packageId", required = true) long packageId) {
		String jsonList = "";
		try {
			List <LoadHistory> histories = null;
			histories = bussinessService.getLoadHistory(packageId);
			JSONArray jsonArr = new JSONArray();
            for (LoadHistory loadHistory : histories) {
	        	JSONObject histJsonObj = new JSONObject();
	        	histJsonObj.put("date", DateUtils.toString(loadHistory.getLoadDate(), "MMM d, yyyy H:mm"));
	        	histJsonObj.put("pkg", loadHistory.getPkg().getName());
	        	histJsonObj.put("user", loadHistory.getUser().getFirstName() + " " 
	        			+ loadHistory.getUser().getLastName() == null ? "" : loadHistory.getUser().getLastName());
	        	histJsonObj.put("fileName", loadHistory.getFileName() == null ? " " : loadHistory.getFileName());
	        	double successFulShipment = loadHistory.getSuccess().doubleValue();
	        	double failedShipment = loadHistory.getFailure().doubleValue();
	        	double totalShipment = successFulShipment + failedShipment;
	        	double percent = totalShipment >0 ? (successFulShipment / totalShipment) * 100 : 0;
	        	int loadType = loadHistory.getLoadType();
	        	String strLoadType = "";
	        	if(loadType == 1) 
	        		strLoadType = "Shipment";
	            else if(loadType == 2) 
	            	strLoadType = "Rate";
	            else if(loadType == 3) 
	            	strLoadType = "Invoice";
	            else if(loadType == 4) 
	            	strLoadType = "Order";
	            else if(loadType == 5) 
	            	strLoadType = "Custom";
	            else if(loadType == 6) 
	            	strLoadType = "Container status";
				DecimalFormat df = new DecimalFormat("####0.00");
	        	histJsonObj.put("scShp", successFulShipment);
	        	histJsonObj.put("fldShp", failedShipment);
	        	histJsonObj.put("tlShp", totalShipment);
	        	histJsonObj.put("type", strLoadType);
	        	histJsonObj.put("percent", df.format(percent));
	        	String loadOn = "EMAIL";
	        	if(loadHistory.isWebload())
	        		loadOn = "WEB";
	        	histJsonObj.put("loadOn", loadOn);
	        	jsonArr.put(histJsonObj);
			}
            jsonList = jsonArr.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonList;
	}
	
	/*@RequestMapping(value="/test", method = RequestMethod.GET)
	public String test(Model model) {
		return "test";
	}*/
	
	/*@SuppressWarnings("unused")
	@RequestMapping(value = "/loadWithOutPackage", method = RequestMethod.POST)
	public String loadWithOutPackage(MultipartHttpServletRequest request) {
		File up = new File("/home/cynere/Documents/PROJECT_FILES/DEEPDIVE/Akhil Design.xlsx");
		ProcessXLS.processXLSFile("/home/cynere/Documents/PROJECT_FILES/DEEPDIVE/Akhil Design.xlsx", 2);
		return "test";
	}*/
	
	@RequestMapping(value = "/getSubPackageDetails", method = RequestMethod.POST)
    public @ResponseBody String getSubPackageDetails(@RequestParam(value = "packageId", required = true) long packageId) {
		String jsonList = "";
		try {
			List <SubPackageDetail> subPackageDetails = null;
			subPackageDetails = bussinessService.getSubPackageDetails(packageId);
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonArr = new JSONArray();
			String pkgName = "";
            for (SubPackageDetail subPackageDetail : subPackageDetails) {
	        	JSONObject subPkgJSON = new JSONObject();
	        	subPkgJSON.put("createdDate", DateUtils.toString(subPackageDetail.getCreatedDate(), "MMM d, yyyy"));
	        	subPkgJSON.put("updatedDate", DateUtils.toString(subPackageDetail.getLastUpdated(), "MMM d, yyyy"));
	        	String loadType = subPackageDetail.getLoadType().getLoad();
	        	subPkgJSON.put("type", loadType);
	        	subPkgJSON.put("schedule", subPackageDetail.getSchedule().getSchedule());
	        	jsonArr.put(subPkgJSON);
	        	pkgName =  subPackageDetail.getPkg().getName();
	    		Calendar currentDate = Calendar.getInstance();
	    		Calendar updatedDate = Calendar.getInstance();
	    		updatedDate.setTime(subPackageDetail.getLastUpdated());
	    		String loadStatus = "";
	    		if(subPackageDetail.getSchedule().equals(ScheduleType.D)){
	    			if(currentDate.get(Calendar.DATE) == updatedDate.get(Calendar.DATE) && 
	    					currentDate.get(Calendar.MONTH) == updatedDate.get(Calendar.MONTH) &&
	    					currentDate.get(Calendar.YEAR) == updatedDate.get(Calendar.YEAR)){
	    				loadStatus = "Loaded today";
	    			} else {
	    				loadStatus = "Not loaded today";
	    			}
	    		} else if(subPackageDetail.getSchedule().equals(ScheduleType.M)){
	    			if(currentDate.get(Calendar.MONTH) == updatedDate.get(Calendar.MONTH) &&
	    					currentDate.get(Calendar.YEAR) == updatedDate.get(Calendar.YEAR)){
	    				loadStatus = "Loaded this month";
	    			} else {
	    				loadStatus = "Not loaded this month";
	    			}
	    		} else if(subPackageDetail.getSchedule().equals(ScheduleType.W)){
	    			if(currentDate.get(Calendar.WEEK_OF_YEAR) == updatedDate.get(Calendar.WEEK_OF_YEAR) &&
	    					currentDate.get(Calendar.YEAR) == updatedDate.get(Calendar.YEAR)){
	    				loadStatus = "Loaded this week";
	    			} else {
	    				loadStatus = "Not loaded this week";
	    			}
	    		} else {
	    			loadStatus = "Not Applicable";
	    		}
	    		subPkgJSON.put("loadStatus", loadStatus);
	    		subPkgJSON.put("id", subPackageDetail.getId());
			}
            jsonObj.put("detailArray", jsonArr);
            jsonObj.put("packageName", pkgName);
            jsonList = jsonObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonList;
	}
	
	private void getUserSession(Model model) {
		String userName = "";
		String compName = "";
		try {
			DeepDiveUser sessionUser = (DeepDiveUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			userName = sessionUser.getUserName();
			compName = sessionUser.getCompName();
		} catch (Exception e){
			e.printStackTrace();
		}
		model.addAttribute("userName", userName);
		model.addAttribute("compName", compName == null? "" : compName.toUpperCase());
	}
}
