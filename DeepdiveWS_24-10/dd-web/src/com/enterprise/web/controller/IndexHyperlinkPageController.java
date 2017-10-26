package com.enterprise.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.enterprise.common.pojo.ModalTemplateBean;

@Controller
@SessionAttributes({"jSessionBean", "modalTemplateBean", "dataUploadBean"})
public class IndexHyperlinkPageController {
	
	@RequestMapping(value="/upload/invoice", method = {RequestMethod.POST, RequestMethod.GET})
	public String uploadInvoiceRequest(@ModelAttribute("modalTemplateBean") ModalTemplateBean modalTemplateBean) {
		modalTemplateBean.setDataIncludePage("/WEB-INF/jsp/upload/page.jsp");
		modalTemplateBean.setIframeSrc("upload/invoice/form");
		modalTemplateBean.setHeading("Upload Invoices and Billings");
		return "template/modal";
	}
	
	@RequestMapping(value="/upload/shipment", method = {RequestMethod.POST, RequestMethod.GET})
	public String uploadShipmentRequest(@ModelAttribute("modalTemplateBean") ModalTemplateBean modalTemplateBean) {
		modalTemplateBean.setDataIncludePage("/WEB-INF/jsp/upload/page.jsp");
		modalTemplateBean.setIframeSrc("upload/shipment/form");
		modalTemplateBean.setHeading("Upload Shipment Data");
		return "template/modal";
	}
	
	@RequestMapping(value="/upload/city", method = {RequestMethod.POST, RequestMethod.GET})
	public String uploadCityRequest(@ModelAttribute("modalTemplateBean") ModalTemplateBean modalTemplateBean) {
		modalTemplateBean.setDataIncludePage("/WEB-INF/jsp/upload/page.jsp");
		modalTemplateBean.setIframeSrc("upload/city/form");
		modalTemplateBean.setHeading("Upload City Data");
		return "template/modal";
	}
	
	@RequestMapping(value="/upload/airport", method = {RequestMethod.POST, RequestMethod.GET})
	public String uploadAirportRequest(@ModelAttribute("modalTemplateBean") ModalTemplateBean modalTemplateBean) {
		modalTemplateBean.setDataIncludePage("/WEB-INF/jsp/upload/page.jsp");
		modalTemplateBean.setIframeSrc("upload/airport/form");
		modalTemplateBean.setHeading("Upload Airport Data");
		return "template/modal";
	}
	
	@RequestMapping(value="/upload/seaport", method = {RequestMethod.POST, RequestMethod.GET})
	public String uploadSeaportRequest(@ModelAttribute("modalTemplateBean") ModalTemplateBean modalTemplateBean) {
		modalTemplateBean.setDataIncludePage("/WEB-INF/jsp/upload/page.jsp");
		modalTemplateBean.setIframeSrc("upload/seaport/form");
		modalTemplateBean.setHeading("Upload Ocean Data");
		return "template/modal";
	}
	
	@RequestMapping(value="/upload/aircarrier", method = {RequestMethod.POST, RequestMethod.GET})
	public String uploadAirCarrierRequest(@ModelAttribute("modalTemplateBean") ModalTemplateBean modalTemplateBean) {
		modalTemplateBean.setDataIncludePage("/WEB-INF/jsp/upload/page.jsp");
		modalTemplateBean.setIframeSrc("upload/aircarrier/form");
		modalTemplateBean.setHeading("Upload Air Carrier Data");
		return "template/modal";
	}
	
	@RequestMapping(value="/upload/seacarrier", method = {RequestMethod.POST, RequestMethod.GET})
	public String uploadSeaCarrierRequest(@ModelAttribute("modalTemplateBean") ModalTemplateBean modalTemplateBean) {
		modalTemplateBean.setDataIncludePage("/WEB-INF/jsp/upload/page.jsp");
		modalTemplateBean.setIframeSrc("upload/seacarrier/form");
		modalTemplateBean.setHeading("Upload Sea Carrier Data");
		return "template/modal";
	}
	
	@RequestMapping(value="/tableau", method = {RequestMethod.POST, RequestMethod.GET})
	public String tableauRequest(@ModelAttribute("modalTemplateBean") ModalTemplateBean modalTemplateBean) {
		return "tableau";
	}
	
	@RequestMapping(value="/load", method = {RequestMethod.POST, RequestMethod.GET})
	public String loadRequest(@ModelAttribute("modalTemplateBean") ModalTemplateBean modalTemplateBean) {
		return "load";
	}
}