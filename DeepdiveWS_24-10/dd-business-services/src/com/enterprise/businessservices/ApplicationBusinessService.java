package com.enterprise.businessservices;

import com.enterprise.common.enums.StatusTypes;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  03 Mar 2014 12:42:16 PM
 * @author rafourie
 */
public interface ApplicationBusinessService {
	
	public abstract StatusTypes loadInvoiceMetaModel() throws RuntimeException;
	
	public abstract StatusTypes loadShipmentMetaModel() throws RuntimeException;
	
	public abstract StatusTypes loadCityMetaModel() throws RuntimeException;
	
	public abstract StatusTypes loadAirportMetaModel() throws RuntimeException;
	
	public abstract StatusTypes loadSeaportMetaModel() throws RuntimeException;
	
	public abstract StatusTypes loadAirCarrierMetaModel() throws RuntimeException;
	
	public abstract StatusTypes loadSeaCarrierMetaModel() throws RuntimeException;
	
	public abstract StatusTypes loadRateMetaModel() throws RuntimeException;
	
	public abstract StatusTypes loadOrderMetaModel() throws RuntimeException;
	
	public abstract StatusTypes loadCustomMetaModel() throws RuntimeException;
	
	public abstract StatusTypes loadContainerMetaModel() throws RuntimeException;
}