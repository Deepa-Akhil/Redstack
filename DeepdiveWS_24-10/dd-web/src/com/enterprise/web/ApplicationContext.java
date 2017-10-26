package com.enterprise.web;

import org.springframework.web.context.WebApplicationContext;

import com.enterprise.businessservices.ApplicationBusinessService;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  03 Mar 2014 12:18:29 PM
 * @author rafourie
 */
public final class ApplicationContext {
	private static ApplicationBusinessService applicationService;
	
	public static void init(WebApplicationContext applicationContext) {
		applicationService 
			= (ApplicationBusinessService)applicationContext.getBean("applicationBusinessService");
		ApplicationContext.loadInvoiceMetaModel();
		ApplicationContext.loadShipmentMetaModel();
		ApplicationContext.loadCityMetaModel();
		ApplicationContext.loadAirportMetaModel();
		ApplicationContext.loadSeaportMetaModel();
		ApplicationContext.loadAirCarrierMetaModel();
		ApplicationContext.loadSeaCarrierMetaModel();
		ApplicationContext.loadRateMetaModel();
		ApplicationContext.loadOrderMetaModel();
		ApplicationContext.loadCustomMetaModel();
		ApplicationContext.loadContainerMetaModel();
		System.out.println("ApplicationContext class initialised ...");
	}
	
	private static void loadInvoiceMetaModel() {
		try {
			applicationService.loadInvoiceMetaModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	private static void loadShipmentMetaModel() {
		try {
			applicationService.loadShipmentMetaModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadCityMetaModel() {
		try {
			applicationService.loadCityMetaModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadAirportMetaModel() {
		try {
			applicationService.loadAirportMetaModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadSeaportMetaModel() {
		try {
			applicationService.loadSeaportMetaModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadAirCarrierMetaModel() {
		try {
			applicationService.loadAirCarrierMetaModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadSeaCarrierMetaModel() {
		try {
			applicationService.loadSeaCarrierMetaModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadRateMetaModel() {
		try {
			applicationService.loadRateMetaModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadOrderMetaModel() {
		try {
			applicationService.loadOrderMetaModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadCustomMetaModel() {
		try {
			applicationService.loadCustomMetaModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadContainerMetaModel() {
		try {
			applicationService.loadContainerMetaModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}