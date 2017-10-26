package com.enterprise.businessservices.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.businessservices.ApplicationBusinessService;
import com.enterprise.common.enums.StatusTypes;
import com.enterprise.dataservices.ApplicationDataService;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  03 Mar 2014 12:44:20 PM
 * @author rafourie
 */
@Service(value="applicationBusinessService")
public class ApplicationBusinessServiceImpl implements ApplicationBusinessService {
	
	@Autowired
	private ApplicationDataService applicationDataService;
	
	@Transactional
	public StatusTypes loadInvoiceMetaModel() throws RuntimeException {
		return applicationDataService.loadInvoiceMetaModel();
	}

	@Transactional
	public StatusTypes loadShipmentMetaModel() throws RuntimeException {
		return applicationDataService.loadShipmentMetaModel();
	}

	@Transactional
	public StatusTypes loadCityMetaModel() throws RuntimeException {
		return applicationDataService.loadCityMetaModel();
	}

	@Transactional
	public StatusTypes loadAirportMetaModel() throws RuntimeException {
		return applicationDataService.loadAirportMetaModel();
	}

	@Transactional
	public StatusTypes loadSeaportMetaModel() throws RuntimeException {
		return applicationDataService.loadSeaportMetaModel();
	}

	@Transactional
	public StatusTypes loadAirCarrierMetaModel() throws RuntimeException {
		return applicationDataService.loadAirCarrierMetaModel();
	}

	@Transactional
	public StatusTypes loadSeaCarrierMetaModel() throws RuntimeException {
		return applicationDataService.loadSeaCarrierMetaModel();
	}
	
	@Transactional
	public StatusTypes loadRateMetaModel() throws RuntimeException {
		return applicationDataService.loadRateMetaModel();
	}

	@Transactional
	public StatusTypes loadOrderMetaModel() throws RuntimeException {
		return applicationDataService.loadOrderMetaModel();
	}
	
	@Transactional
	public StatusTypes loadCustomMetaModel() throws RuntimeException {
		return applicationDataService.loadCustomMetaModel();
	}
	
	@Transactional
	public StatusTypes loadContainerMetaModel() throws RuntimeException {
		return applicationDataService.loadContainerStatusMetaModel();
	}
}