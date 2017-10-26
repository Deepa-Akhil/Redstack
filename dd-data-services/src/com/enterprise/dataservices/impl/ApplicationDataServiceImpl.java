package com.enterprise.dataservices.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enterprise.common.enums.StatusTypes;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.dataservices.ApplicationDataService;
import com.enterprise.dataservices.meta.AirCarrierMetaDataService;
import com.enterprise.dataservices.meta.AirportMetaDataService;
import com.enterprise.dataservices.meta.CityMetaDataService;
import com.enterprise.dataservices.meta.ContainerMetaDataService;
import com.enterprise.dataservices.meta.CustomMetaDataService;
import com.enterprise.dataservices.meta.InvoiceMetaDataService;
import com.enterprise.dataservices.meta.OrderMetaDataService;
import com.enterprise.dataservices.meta.RateMetaDataService;
import com.enterprise.dataservices.meta.SeaCarrierMetaDataService;
import com.enterprise.dataservices.meta.SeaportMetaDataService;
import com.enterprise.dataservices.meta.ShipmentMetaDataService;
import com.enterprise.domain.entity.AirCarrier;
import com.enterprise.domain.entity.Airport;
import com.enterprise.domain.entity.Branch;
import com.enterprise.domain.entity.CMF;
import com.enterprise.domain.entity.Carrier;
import com.enterprise.domain.entity.City;
import com.enterprise.domain.entity.Container;
import com.enterprise.domain.entity.ContainerStatus;
import com.enterprise.domain.entity.ContainerType;
import com.enterprise.domain.entity.Country;
import com.enterprise.domain.entity.Currency;
import com.enterprise.domain.entity.Custom;
import com.enterprise.domain.entity.Invoice;
import com.enterprise.domain.entity.InvoiceDetail;
import com.enterprise.domain.entity.LogisticsServiceProvider;
import com.enterprise.domain.entity.Order;
import com.enterprise.domain.entity.OrderExpedLine;
import com.enterprise.domain.entity.OrderLine;
import com.enterprise.domain.entity.Port;
import com.enterprise.domain.entity.Rate;
import com.enterprise.domain.entity.RateDetail;
import com.enterprise.domain.entity.RateDetailCharges;
import com.enterprise.domain.entity.RefType;
import com.enterprise.domain.entity.SeaCarrier;
import com.enterprise.domain.entity.Seaport;
import com.enterprise.domain.entity.Shipment;
import com.enterprise.domain.entity.ShipmentContainer;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  03 Mar 2014 12:47:54 PM
 * @author rafourie
 */
@Repository(value="applicationDataService")
public class ApplicationDataServiceImpl implements ApplicationDataService {
		
	@Autowired
	protected SessionFactory sessionFactory;
	
	@Autowired
	private InvoiceMetaDataService invoiceMetaDataService;
	
	@Autowired
	private ShipmentMetaDataService shipmentMetaDataService;
	
	@Autowired
	private CityMetaDataService cityMetaDataService;
	
	@Autowired
	private AirportMetaDataService airportMetaDataService;
	
	@Autowired
	private SeaportMetaDataService seaportMetaDataService;
	
	@Autowired
	private SeaCarrierMetaDataService seaCarrierMetaDataService;
	
	@Autowired
	private AirCarrierMetaDataService airCarrierMetaDataService;
	
	@Autowired
	private RateMetaDataService rateMetaDataService;
	
	@Autowired
	private OrderMetaDataService orderMetaDataService;
	
	@Autowired
	private CustomMetaDataService customMetaDataService;
	
	@Autowired
	private ContainerMetaDataService containerMetaDataService;
	
	
	public StatusTypes loadInvoiceMetaModel() throws RuntimeException {
		invoiceMetaDataService.load("Shipment Number", Invoice.class, "getShipmentNumber", "setShipmentNumber", SubTypes.Invoice);
		invoiceMetaDataService.load("Invoice Number", Invoice.class, "getInvoiceNumber", "setInvoiceNumber", SubTypes.Invoice);
		invoiceMetaDataService.load("Invoice Date", Invoice.class, "getInvoiceDate", "setInvoiceDate", SubTypes.Invoice);
		invoiceMetaDataService.load("Charge Category", InvoiceDetail.class, "getChargeCategory", "setChargeCategory", SubTypes.InvoiceDetail);
		invoiceMetaDataService.load("Charge Code", InvoiceDetail.class, "getChargeCode", "setChargeCode", SubTypes.InvoiceDetail);
		invoiceMetaDataService.load("Charge Description", InvoiceDetail.class, "getChargeDescription", "setChargeDescription", SubTypes.InvoiceDetail);
		invoiceMetaDataService.load("Local Amount", InvoiceDetail.class, "getLocalAmount", "setLocalAmount", SubTypes.InvoiceDetail);
		invoiceMetaDataService.load("Local Currency", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency);
		invoiceMetaDataService.load("USD Amount", InvoiceDetail.class, "getUsdAmount", "setUsdAmount", SubTypes.InvoiceDetail);
		invoiceMetaDataService.load("Charge Level", InvoiceDetail.class, "getChargeLevel", "setChargeLevel", SubTypes.InvoiceDetail);
		
		invoiceMetaDataService.load("Billing Country", Country.class, "getCountryCd", "setCountryCd", SubTypes.Country);
		invoiceMetaDataService.load("Billing Mode", Invoice.class, "getBillingMode", "setBillingMode", SubTypes.Invoice);
		invoiceMetaDataService.load("Bill to Part", Invoice.class, "getBillingModePart", "setBillingModePart", SubTypes.Invoice);
		invoiceMetaDataService.load("User Defined 1", Invoice.class, "getUserDefined1", "setUserDefined1", SubTypes.Invoice);
		invoiceMetaDataService.load("User Defined 2", Invoice.class, "getUserDefined2", "setUserDefined2", SubTypes.Invoice);
		invoiceMetaDataService.load("User Defined 3", Invoice.class, "getUserDefined3", "setUserDefined3", SubTypes.Invoice);
		
		invoiceMetaDataService.load("Freight charges", Invoice.class, "getFreightCharges", "setFreightCharges", SubTypes.Invoice);
		invoiceMetaDataService.load("Fuel surchage", Invoice.class, "getFuelSurchage", "setFuelSurchage", SubTypes.Invoice);
		invoiceMetaDataService.load("Security surcharge", Invoice.class, "getSecuritySurcharge", "setSecuritySurcharge", SubTypes.Invoice);
		invoiceMetaDataService.load("War risk surcharge", Invoice.class, "getWarRiskSurcharge", "setWarRiskSurcharge", SubTypes.Invoice);
		invoiceMetaDataService.load("Pickup charges", Invoice.class, "getPickupCharges", "setPickupCharges", SubTypes.Invoice);
		invoiceMetaDataService.load("Documentation", Invoice.class, "getDocumentation", "setDocumentation", SubTypes.Invoice);
		invoiceMetaDataService.load("DG fees", Invoice.class, "getDgFees", "setDgFees", SubTypes.Invoice);
		invoiceMetaDataService.load("Terminal handling", Invoice.class, "getTerminalHandling", "setTerminalHandling", SubTypes.Invoice);
		invoiceMetaDataService.load("Cntr charges", Invoice.class, "getCntrCharges", "setCntrCharges", SubTypes.Invoice);
		invoiceMetaDataService.load("Brokerage/Clearance", Invoice.class, "getBrokerageClearance", "setBrokerageClearance", SubTypes.Invoice);
		invoiceMetaDataService.load("Duties & Taxes", Invoice.class, "getDutiesTaxes", "setDutiesTaxes", SubTypes.Invoice);
		invoiceMetaDataService.load("Customs & OGA charges", Invoice.class, "getCustomsOGACharges", "setCustomsOGACharges", SubTypes.Invoice);
		invoiceMetaDataService.load("Inland charges", Invoice.class, "getInlandCharges", "setInlandCharges", SubTypes.Invoice);
		invoiceMetaDataService.load("Delivery charges", Invoice.class, "getDeliveryCharges", "setDeliveryCharges", SubTypes.Invoice);
		invoiceMetaDataService.load("Other Accessorials", Invoice.class, "getOtherAccessorials", "setOtherAccessorials", SubTypes.Invoice);
		invoiceMetaDataService.load("Storage & Warehousing ", Invoice.class, "getStorageWarehousing", "setStorageWarehousing", SubTypes.Invoice);
		invoiceMetaDataService.load("Total Charges (Incl Duties)", Invoice.class, "getTotalChargesInDuty", "setTotalChargesInDuty", SubTypes.Invoice);
		invoiceMetaDataService.load("Total Charges (Excl Duties)", Invoice.class, "getTotalChargesExDuty", "setTotalChargesExDuty", SubTypes.Invoice);
		invoiceMetaDataService.load("Origin Country Code", Country.class, "getCountryCd", "setCountryCd", SubTypes.OriginCountry);
		invoiceMetaDataService.load("Destination Country Code", Country.class, "getCountryCd", "setCountryCd", SubTypes.DestinationCountry);
		invoiceMetaDataService.load("Origin Port Code", Port.class, "getLocCd", "setLocCd", SubTypes.OriginPort);
		invoiceMetaDataService.load("Origin Port Long Code", Port.class, "getUniqueCd", "setUniqueCd", SubTypes.OriginPort);
		invoiceMetaDataService.load("Destination Port Code", Port.class, "getLocCd", "setLocCd", SubTypes.DestinationPort);
		invoiceMetaDataService.load("Destination Port Long Code", Port.class, "getUniqueCd", "setUniqueCd", SubTypes.DestinationPort);
		return StatusTypes.Successful;
	}

	public StatusTypes loadShipmentMetaModel() throws RuntimeException {
		shipmentMetaDataService.load("Shipment Number", Shipment.class, "getShipmentNumber", "setShipmentNumber", SubTypes.Shipment);
		shipmentMetaDataService.load("Origin City Code", City.class, "getLocCd", "setLocCd", SubTypes.OriginCity);
		shipmentMetaDataService.load("Origin Country Code", Country.class, "getCountryCd", "setCountryCd", SubTypes.OriginCountry);
		shipmentMetaDataService.load("Destination City Code", City.class, "getLocCd", "setLocCd", SubTypes.DestinationCity);
		shipmentMetaDataService.load("Destination Country Code", Country.class, "getCountryCd", "setCountryCd", SubTypes.DestinationCountry);
		shipmentMetaDataService.load("Year", Shipment.class, "getYear", "setYear", SubTypes.Shipment);
		shipmentMetaDataService.load("Mode", Shipment.class, "getMode", "setMode", SubTypes.Shipment);
		shipmentMetaDataService.load("Number of Pieces", ShipmentContainer.class, "getNumberOfPieces", "setNumberOfPieces", SubTypes.ShipmentContainer);
		shipmentMetaDataService.load("Weight", ShipmentContainer.class, "getWeight", "setWeight", SubTypes.ShipmentContainer);
		shipmentMetaDataService.load("Chargeable Weight", ShipmentContainer.class, "getChargeableWeight", "setChargeableWeight", SubTypes.ShipmentContainer);
		shipmentMetaDataService.load("Volume", ShipmentContainer.class, "getVolume", "setVolume", SubTypes.ShipmentContainer);
		//shipmentMetaDataService.load("Shipment Type", ShipmentContainer.class, "getShipmentType", "setShipmentType", SubTypes.ShipmentContainer);
		shipmentMetaDataService.load("Service Provider", LogisticsServiceProvider.class, "getClientCd", "setClientCd", SubTypes.ServiceProvider);
		shipmentMetaDataService.load("Origin Port Code", Port.class, "getLocCd", "setLocCd", SubTypes.OriginPort);
		shipmentMetaDataService.load("Origin Port Long Code", Port.class, "getUniqueCd", "setUniqueCd", SubTypes.OriginPort);
		shipmentMetaDataService.load("Destination Port Code", Port.class, "getLocCd", "setLocCd", SubTypes.DestinationPort);
		shipmentMetaDataService.load("Destination Port Long Code", Port.class, "getUniqueCd", "setUniqueCd", SubTypes.DestinationPort);
		shipmentMetaDataService.load("Origin Branch Code", Branch.class, "getLocCd", "setLocCd", SubTypes.OriginBranch);
		shipmentMetaDataService.load("Destination Branch Code", Branch.class, "getLocCd", "setLocCd", SubTypes.DestinationBranch);
		shipmentMetaDataService.load("Origin Branch Long Code", Branch.class, "getUniqueCd", "setUniqueCd", SubTypes.OriginBranch);
		shipmentMetaDataService.load("Destination Branch Long Code", Branch.class, "getUniqueCd", "setUniqueCd", SubTypes.DestinationBranch);
		shipmentMetaDataService.load("Shipper Code", CMF.class, "getClientCd", "setClientCd", SubTypes.Shipper);
		shipmentMetaDataService.load("Shipper Alias", CMF.class, "getAlias", "setAlias", SubTypes.Shipper);
		shipmentMetaDataService.load("Consignee Code", CMF.class, "getClientCd", "setClientCd", SubTypes.Consignee);
		shipmentMetaDataService.load("Consignee Alias", CMF.class, "getAlias", "setAlias", SubTypes.Consignee);
		shipmentMetaDataService.load("Carrier", Carrier.class, "getCarrierCd", "setCarrierCd", SubTypes.Carrier);
		shipmentMetaDataService.load("Container Number", Container.class, "getContainerNumber", "setContainerNumber", SubTypes.Container);
		//shipmentMetaDataService.load("Container Type", Container.class, "getType", "setType", SubTypes.Container);
		shipmentMetaDataService.load("Container Type", ContainerType.class, "getType", "setType", SubTypes.ContainerType);
		shipmentMetaDataService.load("Container Description", Container.class, "getContainerDesc", "setContainerDesc", SubTypes.Container);
		shipmentMetaDataService.load("Estimated Departure Date", Shipment.class, "getEtd", "setEtd", SubTypes.Shipment);
		shipmentMetaDataService.load("Estimated Arrival Date", Shipment.class, "getEta", "setEta", SubTypes.Shipment);
		shipmentMetaDataService.load("Actual Departure Date", Shipment.class, "getAtd", "setAtd", SubTypes.Shipment);
		shipmentMetaDataService.load("Actual Arrival Date", Shipment.class, "getAta", "setAta", SubTypes.Shipment);
		shipmentMetaDataService.load("Create Date", Shipment.class, "getCreateDate", "setCreateDate", SubTypes.Shipment);
		shipmentMetaDataService.load("Booking Date", Shipment.class, "getBookingDate", "setBookingDate", SubTypes.Shipment);
		shipmentMetaDataService.load("Received Date", Shipment.class, "getReceivedDate", "setReceivedDate", SubTypes.Shipment);
		shipmentMetaDataService.load("Delivery Date", Shipment.class, "getDeliveryDate", "setDeliveryDate", SubTypes.Shipment);
		shipmentMetaDataService.load("Required Delivery Date", Shipment.class, "getRequiredDeliveryDate", "setRequiredDeliveryDate", SubTypes.Shipment);
		shipmentMetaDataService.load("MasterBill Number", Shipment.class, "getMastBillNo", "setMastBillNo", SubTypes.Shipment);
		shipmentMetaDataService.load("Carrier Name", Shipment.class, "getCarrierName", "setCarrierName", SubTypes.Shipment);
		shipmentMetaDataService.load("Voyage Name", Shipment.class, "getVoyageName", "setVoyageName", SubTypes.Shipment);
		shipmentMetaDataService.load("Voyage Number", Shipment.class, "getVoyageNumber", "setVoyageNumber", SubTypes.Shipment);
		shipmentMetaDataService.load("Flight Number", Shipment.class, "setFlightNumber", "setFlightNumber", SubTypes.Shipment);
		shipmentMetaDataService.load("Flight Number", Shipment.class, "setFlightNumber", "setFlightNumber", SubTypes.Shipment);
		shipmentMetaDataService.load("User Defined 1", Shipment.class, "getUserDefined1", "setUserDefined1", SubTypes.Shipment);
		shipmentMetaDataService.load("User Defined 2", Shipment.class, "getUserDefined2", "setUserDefined2", SubTypes.Shipment);
		shipmentMetaDataService.load("User Defined 3", Shipment.class, "getUserDefined3", "setUserDefined3", SubTypes.Shipment);
		shipmentMetaDataService.load("In Customs Date", Shipment.class, "getInCustomerDate", "setInCustomerDate", SubTypes.Shipment);
		shipmentMetaDataService.load("Out Customs Date", Shipment.class, "getOutCustomerDate", "setOutCustomerDate", SubTypes.Shipment);
		shipmentMetaDataService.load("Comments", Shipment.class, "getComments", "setComments", SubTypes.Shipment);
		shipmentMetaDataService.load("Good Description", Shipment.class, "getGoodDescription", "setGoodDescription", SubTypes.Shipment);
		
		shipmentMetaDataService.load("20 Ft Dry Bulk Cntrs", Shipment.class, "getDryBulkCntrs20Ft", "setDryBulkCntrs20Ft", SubTypes.Shipment);
		shipmentMetaDataService.load("20 Ft Dry Cntrs", Shipment.class, "getDryCntrs20Ft", "setDryCntrs20Ft", SubTypes.Shipment);
		shipmentMetaDataService.load("20 Ft Reefer Cntrs", Shipment.class, "getReeferCntrs20Ft", "setReeferCntrs20Ft", SubTypes.Shipment);
		shipmentMetaDataService.load("20 Ft Tank Cntrs", Shipment.class, "getTankCntrs20Ft", "setTankCntrs20Ft", SubTypes.Shipment);
		shipmentMetaDataService.load("20 Ft Flat Cntrs", Shipment.class, "getFlatCntrs20Ft", "setFlatCntrs20Ft", SubTypes.Shipment);
		shipmentMetaDataService.load("20 Ft High Cube Dry Cntrs", Shipment.class, "getHighCubeDryCntrs20Ft", "setHighCubeDryCntrs20Ft", SubTypes.Shipment);
		shipmentMetaDataService.load("20 Ft Insulated Cntrs", Shipment.class, "getInsulatedCntrs20Ft", "setInsulatedCntrs20Ft", SubTypes.Shipment);
		shipmentMetaDataService.load("20 Ft Open Top Cntrs", Shipment.class, "getOpenTopCntrs20Ft", "setOpenTopCntrs20Ft", SubTypes.Shipment);
		shipmentMetaDataService.load("40 Ft Dry Cntrs", Shipment.class, "getDryCntrs40Ft", "setDryCntrs40Ft", SubTypes.Shipment);
		shipmentMetaDataService.load("40 Ft High Cube Dry Cntrs", Shipment.class, "getHighCubeDryCntrs40Ft", "setHighCubeDryCntrs40Ft", SubTypes.Shipment);
		shipmentMetaDataService.load("40 Ft High Cube Reefer Cntrs", Shipment.class, "getHighCubeReeferCntrs40Ft", "setHighCubeReeferCntrs40Ft", SubTypes.Shipment);
		shipmentMetaDataService.load("40 Ft Reefer Cntrs", Shipment.class, "getReeferCntrs40Ft", "setReeferCntrs40Ft", SubTypes.Shipment);
		shipmentMetaDataService.load("45 Ft High Cube Dry Cntrs", Shipment.class, "getHighCubeDryCntrs45Ft", "setHighCubeDryCntrs45Ft", SubTypes.Shipment);
		shipmentMetaDataService.load("Other Cntrs", Shipment.class, "getOtherCntrs", "setOtherCntrs", SubTypes.Shipment);
		shipmentMetaDataService.load("HB Number", Shipment.class, "getHbNumber", "setHbNumber", SubTypes.Shipment);
		shipmentMetaDataService.load("Customs Number", Shipment.class, "getCustomsNumber", "setCustomsNumber", SubTypes.Shipment);
		shipmentMetaDataService.load("User Defined 4", Shipment.class, "getUserDefined4", "setUserDefined4", SubTypes.Shipment);
		shipmentMetaDataService.load("User Defined 5", Shipment.class, "getUserDefined5", "setUserDefined5", SubTypes.Shipment);
		shipmentMetaDataService.load("User Defined 6", Shipment.class, "getUserDefined6", "setUserDefined6", SubTypes.Shipment);
		shipmentMetaDataService.load("User Defined 7", Shipment.class, "getUserDefined7", "setUserDefined7", SubTypes.Shipment);
		shipmentMetaDataService.load("Reference Type1", RefType.class, "getRefType1", "setRefType1", SubTypes.RefType);
		shipmentMetaDataService.load("Reference Type2", RefType.class, "getRefType2", "setRefType2", SubTypes.RefType);
		shipmentMetaDataService.load("Reference Type3", RefType.class, "getRefType3", "setRefType3", SubTypes.RefType);
		return StatusTypes.Successful;
	}

	public StatusTypes loadCityMetaModel() throws RuntimeException {
		cityMetaDataService.load("City Code", City.class, "getLocCd", "setLocCd", SubTypes.City);
		cityMetaDataService.load("Country Code", Country.class, "getCountryCd", "setCountryCd", SubTypes.Country);
		cityMetaDataService.load("City Name", City.class, "getLocName", "setLocName", SubTypes.City);
		cityMetaDataService.load("Unique Code", City.class, "getUniqueCd", "setUniqueCd", SubTypes.City);
		return StatusTypes.Successful;
	}
	
	public StatusTypes loadAirportMetaModel() throws RuntimeException {
		airportMetaDataService.load("Airport Code", Airport.class, "getLocCd", "setLocCd", SubTypes.Airport);
		airportMetaDataService.load("Country Code", Country.class, "getCountryCd", "setCountryCd", SubTypes.Country);
		airportMetaDataService.load("Airport Name", Airport.class, "getLocName", "setLocName", SubTypes.Airport);
		return StatusTypes.Successful;
	}

	public StatusTypes loadSeaportMetaModel() throws RuntimeException {
		seaportMetaDataService.load("Seaport Code", Seaport.class, "getLocCd", "setLocCd", SubTypes.Seaport);
		seaportMetaDataService.load("Country Code", Country.class, "getCountryCd", "setCountryCd", SubTypes.Country);
		seaportMetaDataService.load("Seaport Name", Seaport.class, "getLocName", "setLocName", SubTypes.Seaport);
		seaportMetaDataService.load("Unique Code", Seaport.class, "getUniqueCd", "setUniqueCd", SubTypes.Seaport);
		return StatusTypes.Successful;
	}
	
	public StatusTypes loadAirCarrierMetaModel() throws RuntimeException {
		airCarrierMetaDataService.load("Airline", AirCarrier.class, "getName", "setName", SubTypes.Carrier);
		airCarrierMetaDataService.load("Description", AirCarrier.class, "getDescription", "setDescription", SubTypes.Carrier);
		airCarrierMetaDataService.load("IATA", AirCarrier.class, "getCarrierCd", "setCarrierCd", SubTypes.Carrier);
		airCarrierMetaDataService.load("ICAO", AirCarrier.class, "getCarrierAlias", "setCarrierAlias", SubTypes.Carrier);
		return StatusTypes.Successful;
	}
	
	public StatusTypes loadSeaCarrierMetaModel() throws RuntimeException {
		seaCarrierMetaDataService.load("Name", SeaCarrier.class, "getName", "setName", SubTypes.Carrier);
		seaCarrierMetaDataService.load("Description", SeaCarrier.class, "getDescription", "setDescription", SubTypes.Carrier);
		seaCarrierMetaDataService.load("SCAC", SeaCarrier.class, "getCarrierCd", "setCarrierCd", SubTypes.Carrier);
		seaCarrierMetaDataService.load("Website", SeaCarrier.class, "getWebUrl", "setWebUrl", SubTypes.Carrier);
		return StatusTypes.Successful;
	}
	
	public StatusTypes loadRateMetaModel() throws RuntimeException {
		rateMetaDataService.load("Mode", Rate.class, "getMode", "setMode", SubTypes.Rate);
		rateMetaDataService.load("Origin Port Code", Port.class, "getLocCd", "setLocCd", SubTypes.OriginPort);
		rateMetaDataService.load("Destination Port Code", Port.class, "getLocCd", "setLocCd", SubTypes.DestinationPort);
		rateMetaDataService.load("Carrier Code", Carrier.class, "getCarrierCd", "setCarrierCd", SubTypes.Carrier);
		rateMetaDataService.load("Container Size", RateDetail.class, "getContainerSize", "setContainerSize", SubTypes.RateDetail);
		rateMetaDataService.load("Charge Category", RateDetail.class, "getChargeCategory", "setChargeCategory", SubTypes.RateDetail);
		rateMetaDataService.load("Charge Item", RateDetail.class, "getChargeItem", "setChargeItem", SubTypes.RateDetail);
		rateMetaDataService.load("Base Currency", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency);
		rateMetaDataService.load("Base Charge", RateDetail.class, "getLocalAmount", "setLocalAmount", SubTypes.RateDetail);
		rateMetaDataService.load("Base USD Charge", RateDetail.class, "getUsdAmount", "setUsdAmount", SubTypes.RateDetail);
		rateMetaDataService.load("Charge Level", RateDetail.class, "getChargeLevel", "setChargeLevel", SubTypes.RateDetail);
		rateMetaDataService.load("Origin Port Country Code", Country.class, "getCountryCd", "setCountryCd", SubTypes.OriginCountry);
		rateMetaDataService.load("Destination Port Country Code", Country.class, "getCountryCd", "setCountryCd", SubTypes.DestinationCountry);
		rateMetaDataService.load("Base Start Date", Rate.class, "getValidityStartDate", "setValidityStartDate", SubTypes.Rate);
		rateMetaDataService.load("Base End Date", Rate.class, "getValidityEndDate", "setValidityEndDate", SubTypes.Rate);
		rateMetaDataService.load("Mandatory", RateDetail.class, "isMandatory", "setMandatory", SubTypes.RateDetail);
		rateMetaDataService.load("Notes", RateDetail.class, "getNotes", "setNotes", SubTypes.RateDetail);
		rateMetaDataService.load("Transit Time", RateDetail.class, "getTransitTime", "setTransitTime", SubTypes.RateDetail);
		
		rateMetaDataService.load("Origin City", City.class, "getLocCd", "setLocCd", SubTypes.OriginCity);
		rateMetaDataService.load("Destination City", City.class, "getLocCd", "setLocCd", SubTypes.DestinationCity);
		rateMetaDataService.load("Origin City Country Code", Country.class, "getCountryCd", "setCountryCd", SubTypes.OriginCityCountry);
		rateMetaDataService.load("Destination City Country Code", Country.class, "getCountryCd", "setCountryCd", SubTypes.DestinationCityCountry);
		rateMetaDataService.load("Allocation Amount", RateDetail.class, "getAllocationAmount", "setAllocationAmount", SubTypes.RateDetail);
		rateMetaDataService.load("Service Type", RateDetail.class, "getServiceType", "setServiceType", SubTypes.RateDetail);
		rateMetaDataService.load("User Defined 1", RateDetail.class, "getUserdefined1", "setUserdefined1", SubTypes.RateDetail);
		rateMetaDataService.load("User Defined 2", RateDetail.class, "getUserdefined2", "setUserdefined2", SubTypes.RateDetail);
		rateMetaDataService.load("User Defined 3", RateDetail.class, "getUserdefined3", "setUserdefined3", SubTypes.RateDetail);
		
		rateMetaDataService.load("Service String", RateDetail.class, "getServiceString", "setServiceString", SubTypes.RateDetail);
		rateMetaDataService.load("Sailing Frequency", RateDetail.class, "getSailingFreeq", "setSailingFreeq", SubTypes.RateDetail);
		rateMetaDataService.load("Orig Free days", RateDetail.class, "getOrigFreeDays", "setOrigFreeDays", SubTypes.RateDetail);
		rateMetaDataService.load("Orig Port Free days", RateDetail.class, "getOrigPortFreeDays", "setOrigPortFreeDays", SubTypes.RateDetail);
		rateMetaDataService.load("Destination Free days", RateDetail.class, "getDestFreeDays", "setDestFreeDays", SubTypes.RateDetail);
		rateMetaDataService.load("Destination Port Free days", RateDetail.class, "getDestPortFreeDays", "setDestPortFreeDays", SubTypes.RateDetail);
		
		rateMetaDataService.load("Haz Orig Free days", RateDetail.class, "getOrigFreeDaysHaz", "setOrigFreeDaysHaz", SubTypes.RateDetail);
		rateMetaDataService.load("Haz Orig Port Free days", RateDetail.class, "getOrigPortFreeDaysHaz", "setOrigPortFreeDaysHaz", SubTypes.RateDetail);
		rateMetaDataService.load("Haz Destination Free days", RateDetail.class, "getDestFreeDaysHaz", "setDestFreeDaysHaz", SubTypes.RateDetail);
		rateMetaDataService.load("Haz Destination Port Free days", RateDetail.class, "getDestPortFreeDaysHaz", "setDestPortFreeDaysHaz", SubTypes.RateDetail);
		
		rateMetaDataService.load("CY Cuttoff day", RateDetail.class, "getCyCutOffDays", "setCyCutOffDays", SubTypes.RateDetail);
		rateMetaDataService.load("Sailing day", RateDetail.class, "getSailingDay", "setSailingDay", SubTypes.RateDetail);
		rateMetaDataService.load("Arrival day", RateDetail.class, "getArrivalday", "setArrivalday", SubTypes.RateDetail);
		rateMetaDataService.load("Service Level", RateDetail.class, "getServiceLevel", "setServiceLevel", SubTypes.RateDetail);
		
		rateMetaDataService.load("Charge1", RateDetailCharges.class, "getCharge1", "setCharge1", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency1", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency1);
		rateMetaDataService.load("Start Date1", RateDetailCharges.class, "getStartDate1", "setStartDate1", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date1", RateDetailCharges.class, "getEndDate1", "setEndDate1", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge2", RateDetailCharges.class, "getCharge2", "setCharge2", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency2", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency2);
		rateMetaDataService.load("Start Date2", RateDetailCharges.class, "getStartDate2", "setStartDate2", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date2", RateDetailCharges.class, "getEndDate2", "setEndDate2", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge3", RateDetailCharges.class, "getCharge3", "setCharge3", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency3", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency3);
		rateMetaDataService.load("Start Date3", RateDetailCharges.class, "getStartDate3", "setStartDate3", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date3", RateDetailCharges.class, "getEndDate3", "setEndDate3", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge4", RateDetailCharges.class, "getCharge4", "setCharge4", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency4", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency4);
		rateMetaDataService.load("Start Date4", RateDetailCharges.class, "getStartDate4", "setStartDate4", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date4", RateDetailCharges.class, "getEndDate4", "setEndDate4", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge5", RateDetailCharges.class, "getCharge5", "setCharge5", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency5", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency5);
		rateMetaDataService.load("Start Date5", RateDetailCharges.class, "getStartDate5", "setStartDate5", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date5", RateDetailCharges.class, "getEndDate5", "setEndDate5", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge6", RateDetailCharges.class, "getCharge6", "setCharge6", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency6", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency6);
		rateMetaDataService.load("Start Date6", RateDetailCharges.class, "getStartDate6", "setStartDate6", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date6", RateDetailCharges.class, "getEndDate6", "setEndDate6", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge7", RateDetailCharges.class, "getCharge7", "setCharge7", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency7", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency7);
		rateMetaDataService.load("Start Date7", RateDetailCharges.class, "getStartDate7", "setStartDate7", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date7", RateDetailCharges.class, "getEndDate7", "setEndDate7", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge8", RateDetailCharges.class, "getCharge8", "setCharge8", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency8", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency8);
		rateMetaDataService.load("Start Date8", RateDetailCharges.class, "getStartDate8", "setStartDate8", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date8", RateDetailCharges.class, "getEndDate8", "setEndDate8", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge9", RateDetailCharges.class, "getCharge9", "setCharge9", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency9", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency9);
		rateMetaDataService.load("Start Date9", RateDetailCharges.class, "getStartDate9", "setStartDate9", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date9", RateDetailCharges.class, "getEndDate9", "setEndDate9", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge10", RateDetailCharges.class, "getCharge10", "setCharge10", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency10", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency10);
		rateMetaDataService.load("Start Date10", RateDetailCharges.class, "getStartDate10", "setStartDate10", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date10", RateDetailCharges.class, "getEndDate10", "setEndDate10", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge11", RateDetailCharges.class, "getCharge11", "setCharge11", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency11", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency11);
		rateMetaDataService.load("Start Date11", RateDetailCharges.class, "getStartDate11", "setStartDate11", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date11", RateDetailCharges.class, "getEndDate11", "setEndDate11", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge12", RateDetailCharges.class, "getCharge12", "setCharge12", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency12", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency12);
		rateMetaDataService.load("Start Date12", RateDetailCharges.class, "getStartDate12", "setStartDate12", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date12", RateDetailCharges.class, "getEndDate12", "setEndDate12", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge13", RateDetailCharges.class, "getCharge13", "setCharge13", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency13", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency13);
		rateMetaDataService.load("Start Date13", RateDetailCharges.class, "getStartDate13", "setStartDate13", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date13", RateDetailCharges.class, "getEndDate13", "setEndDate13", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge14", RateDetailCharges.class, "getCharge14", "setCharge14", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency14", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency14);
		rateMetaDataService.load("Start Date14", RateDetailCharges.class, "getStartDate14", "setStartDate14", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date14", RateDetailCharges.class, "getEndDate14", "setEndDate14", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge15", RateDetailCharges.class, "getCharge15", "setCharge15", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency15", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency15);
		rateMetaDataService.load("Start Date15", RateDetailCharges.class, "getStartDate15", "setStartDate15", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date15", RateDetailCharges.class, "getEndDate15", "setEndDate15", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge16", RateDetailCharges.class, "getCharge16", "setCharge16", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency16", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency16);
		rateMetaDataService.load("Start Date16", RateDetailCharges.class, "getStartDate16", "setStartDate16", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date16", RateDetailCharges.class, "getEndDate16", "setEndDate16", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge17", RateDetailCharges.class, "getCharge17", "setCharge17", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency17", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency17);
		rateMetaDataService.load("Start Date17", RateDetailCharges.class, "getStartDate17", "setStartDate17", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date17", RateDetailCharges.class, "getEndDate17", "setEndDate17", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge18", RateDetailCharges.class, "getCharge18", "setCharge18", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency18", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency18);
		rateMetaDataService.load("Start Date18", RateDetailCharges.class, "getStartDate18", "setStartDate18", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date18", RateDetailCharges.class, "getEndDate18", "setEndDate18", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge19", RateDetailCharges.class, "getCharge19", "setCharge19", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency19", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency19);
		rateMetaDataService.load("Start Date19", RateDetailCharges.class, "getStartDate19", "setStartDate19", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date19", RateDetailCharges.class, "getEndDate19", "setEndDate19", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Charge20", RateDetailCharges.class, "getCharge20", "setCharge20", SubTypes.RateDetailCharges);
		rateMetaDataService.load("Currency20", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency20);
		rateMetaDataService.load("Start Date20", RateDetailCharges.class, "getStartDate20", "setStartDate20", SubTypes.RateDetailCharges);
		rateMetaDataService.load("End Date20", RateDetailCharges.class, "getEndDate20", "setEndDate", SubTypes.RateDetailCharges);
		
		rateMetaDataService.load("Orig Penalty Cur", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.OrigPenCur);
		rateMetaDataService.load("Orig Penalty Charge", RateDetail.class, "getOrigPenCharge", "setOrigPenCharge", SubTypes.RateDetail);
		rateMetaDataService.load("Orig Free Days Type", RateDetail.class, "getOrigFreeDayType", "setOrigFreeDayType", SubTypes.RateDetail);
		
		rateMetaDataService.load("Orig Port Penalty Cur", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.OrigPortPenCur);
		rateMetaDataService.load("Orig Port Penalty Charge", RateDetail.class, "getOrigPortPenCharge", "setOrigPortPenCharge", SubTypes.RateDetail);
		rateMetaDataService.load("Orig Port Free Days Type", RateDetail.class, "getOrigPortFreeDayType", "setOrigPortFreeDayType", SubTypes.RateDetail);
		
		rateMetaDataService.load("Dest Penalty Cur", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.DestPenCur);
		rateMetaDataService.load("Dest Penalty Charge", RateDetail.class, "getDestPenCharge", "setDestPenCharge", SubTypes.RateDetail);
		rateMetaDataService.load("Dest Free Days Type", RateDetail.class, "getDestFreeDayType", "setDestFreeDayType", SubTypes.RateDetail);
		
		rateMetaDataService.load("Dest Port Penalty Cur", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.DestPortPenCur);
		rateMetaDataService.load("Dest Port Penalty Charge", RateDetail.class, "getDestPortPenCharge", "setDestPortPenCharge", SubTypes.RateDetail);
		rateMetaDataService.load("Dest Port Free Days Type", RateDetail.class, "getDestPortFreeDayType", "setDestPortFreeDayType", SubTypes.RateDetail);
		return StatusTypes.Successful;
	}

	public StatusTypes loadOrderMetaModel() throws RuntimeException {
		orderMetaDataService.load("Order.Number", Order.class, "getOrderNumber", "setOrderNumber", SubTypes.Order);
		orderMetaDataService.load("Order.Type", Order.class, "getOrderType", "setOrderType", SubTypes.Order);
		orderMetaDataService.load("Order.Buyer", Order.class, "getBuyer", "setBuyer", SubTypes.Order);
		orderMetaDataService.load("Order.Supplier", Order.class, "getSupplier", "setSupplier", SubTypes.Order);
		orderMetaDataService.load("Order.End Consumer", Order.class, "getEndConsumer", "setEndConsumer", SubTypes.Order);
		orderMetaDataService.load("Order.Origin Port Code", Port.class, "getLocCd", "setLocCd", SubTypes.OrderOriginPort);
		orderMetaDataService.load("Order.Destination Port Code", Port.class, "getLocCd", "setLocCd", SubTypes.OrderDestinationPort);
		orderMetaDataService.load("Order.Mode", Order.class, "getMode", "setMode", SubTypes.Order);
		orderMetaDataService.load("Order.Currency", Currency.class, "getCurrencyCode", "setCurrencyCode", SubTypes.Currency);
		orderMetaDataService.load("Order.Raised Date", Order.class, "getOredrRaisedOn", "setOredrRaisedOn", SubTypes.Order);
		
		orderMetaDataService.load("OrderLine.Line No", OrderLine.class, "getLineID", "setLineID", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.Item Ref1", OrderLine.class, "getItemRef1", "setItemRef1", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.Item Ref2", OrderLine.class, "getItemRef2", "setItemRef2", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.Item Ref3", OrderLine.class, "getItemRef3", "setItemRef3", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.Item Ref4", OrderLine.class, "getItemRef4", "setItemRef4", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.Description", OrderLine.class, "getDescription", "setDescription", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.Net Amount", OrderLine.class, "getNetAmount", "setNetAmount", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.Quantity Ordered", OrderLine.class, "getQtyOrdered", "setQtyOrdered", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.UOM", OrderLine.class, "getUom", "setUom", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.Expected Ready Date", OrderLine.class, "getExpReadyDate", "setExpReadyDate", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.Earlier Shipment Date", OrderLine.class, "getEarlShipDate", "setEarlShipDate", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.Latest Shipment Date", OrderLine.class, "getLatShipDate", "setLatShipDate", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.Requested On Site Date", OrderLine.class, "getRoseDate", "setRoseDate", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.User Def Date1", OrderLine.class, "getUserDefDate1", "setUserDefDate1", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.User Def Date2", OrderLine.class, "getUserDefDate2", "setUserDefDate2", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.User Def Date3", OrderLine.class, "getUserDefDate3", "setUserDefDate3", SubTypes.OrderLine);
		orderMetaDataService.load("OrderLine.User Def Date4", OrderLine.class, "getUserDefDate4", "setUserDefDate4", SubTypes.OrderLine);
		
		orderMetaDataService.load("OrderExpedLine.Quantity Booked", OrderExpedLine.class, "getQtyBooked", "setQtyBooked", SubTypes.OrderExpedLine);
		orderMetaDataService.load("OrderExpedLine.Quantity Received", OrderExpedLine.class, "getQtyReceived", "setQtyReceived", SubTypes.OrderExpedLine);
		orderMetaDataService.load("OrderExpedLine.Quantity Shipped", OrderExpedLine.class, "getQtyShipped", "setQtyShipped", SubTypes.OrderExpedLine);
		orderMetaDataService.load("OrderExpedLine.Cartons Booked", OrderExpedLine.class, "getCartonsBooked", "setCartonsBooked", SubTypes.OrderExpedLine);
		orderMetaDataService.load("OrderExpedLine.Cartons Received", OrderExpedLine.class, "getCartonsReceived", "setCartonsReceived", SubTypes.OrderExpedLine);
		orderMetaDataService.load("OrderExpedLine.Cartons Shipped", OrderExpedLine.class, "getCartonsShipped", "setCartonsShipped", SubTypes.OrderExpedLine);
		orderMetaDataService.load("OrderExpedLine.CBM Booked", OrderExpedLine.class, "getCbmBooked", "setCbmBooked", SubTypes.OrderExpedLine);
		orderMetaDataService.load("OrderExpedLine.CBM Received", OrderExpedLine.class, "getCbmReceived", "setCbmReceived", SubTypes.OrderExpedLine);
		orderMetaDataService.load("OrderExpedLine.CBM Shipped", OrderExpedLine.class, "getCbmShipped", "setCbmShipped", SubTypes.OrderExpedLine);
		orderMetaDataService.load("OrderExpedLine.Shipment Number", OrderExpedLine.class, "getShipmentNumber", "setShipmentNumber", SubTypes.OrderExpedLine);
		orderMetaDataService.load("OrderExpedLine.Container Number", Container.class, "getContainerNumber", "setContainerNumber", SubTypes.Container);
		//orderMetaDataService.load("OrderExpedLine.Container Type", Container.class, "getType", "setType", SubTypes.Container);
		orderMetaDataService.load("OrderExpedLine.Container Description", Container.class, "getContainerDesc", "setContainerDesc", SubTypes.Container);
		orderMetaDataService.load("OrderExpedLine.Container Type", ContainerType.class, "getType", "setType", SubTypes.ContainerType);
		
		orderMetaDataService.load("Shipment.Origin City Code", City.class, "getLocCd", "setLocCd", SubTypes.OriginCity);
		orderMetaDataService.load("Shipment.Origin Country Code", Country.class, "getCountryCd", "setCountryCd", SubTypes.OriginCountry);
		orderMetaDataService.load("Shipment.Destination City Code", City.class, "getLocCd", "setLocCd", SubTypes.DestinationCity);
		orderMetaDataService.load("Shipment.Destination Country Code", Country.class, "getCountryCd", "setCountryCd", SubTypes.DestinationCountry);
		orderMetaDataService.load("Shipment.Year", Shipment.class, "getYear", "setYear", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Mode", Shipment.class, "getMode", "setMode", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Type", Shipment.class, "getShipmentType", "setShipmentType", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Number of Pieces", ShipmentContainer.class, "getNumberOfPieces", "setNumberOfPieces", SubTypes.ShipmentContainer);
		orderMetaDataService.load("Shipment.Weight", ShipmentContainer.class, "getWeight", "setWeight", SubTypes.ShipmentContainer);
		orderMetaDataService.load("Shipment.Chargeable Weight", ShipmentContainer.class, "getChargeableWeight", "setChargeableWeight", SubTypes.ShipmentContainer);
		orderMetaDataService.load("Shipment.Volume", ShipmentContainer.class, "getVolume", "setVolume", SubTypes.ShipmentContainer);
		orderMetaDataService.load("Shipment.Service Provider", LogisticsServiceProvider.class, "getClientCd", "setClientCd", SubTypes.ServiceProvider);
		orderMetaDataService.load("Shipment.Origin Port Code", Port.class, "getLocCd", "setLocCd", SubTypes.OriginPort);
		orderMetaDataService.load("Shipment.Origin Port Long Code", Port.class, "getUniqueCd", "setUniqueCd", SubTypes.OriginPort);
		orderMetaDataService.load("Shipment.Destination Port Code", Port.class, "getLocCd", "setLocCd", SubTypes.DestinationPort);
		orderMetaDataService.load("Shipment.Destination Port Long Code", Port.class, "getUniqueCd", "setUniqueCd", SubTypes.DestinationPort);
		orderMetaDataService.load("Shipment.Origin Branch Code", Branch.class, "getLocCd", "setLocCd", SubTypes.OriginBranch);
		orderMetaDataService.load("Shipment.Destination Branch Code", Branch.class, "getLocCd", "setLocCd", SubTypes.DestinationBranch);
		orderMetaDataService.load("Shipment.Origin Branch Long Code", Branch.class, "getUniqueCd", "setUniqueCd", SubTypes.OriginBranch);
		orderMetaDataService.load("Shipment.Destination Branch Long Code", Branch.class, "getUniqueCd", "setUniqueCd", SubTypes.DestinationBranch);
		orderMetaDataService.load("Shipment.Shipper Code", CMF.class, "getClientCd", "setClientCd", SubTypes.Shipper);
		orderMetaDataService.load("Shipment.Shipper Alias", CMF.class, "getAlias", "setAlias", SubTypes.Shipper);
		orderMetaDataService.load("Shipment.Consignee Code", CMF.class, "getClientCd", "setClientCd", SubTypes.Consignee);
		orderMetaDataService.load("Shipment.Consignee Alias", CMF.class, "getAlias", "setAlias", SubTypes.Consignee);
		orderMetaDataService.load("Shipment.Carrier", Carrier.class, "getCarrierCd", "setCarrierCd", SubTypes.Carrier);
		orderMetaDataService.load("Shipment.Estimated Departure Date", Shipment.class, "getEtd", "setEtd", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Estimated Arrival Date", Shipment.class, "getEta", "setEta", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Actual Departure Date", Shipment.class, "getAtd", "setAtd", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Actual Arrival Date", Shipment.class, "getAta", "setAta", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Create Date", Shipment.class, "getCreateDate", "setCreateDate", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Booking Date", Shipment.class, "getBookingDate", "setBookingDate", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Received Date", Shipment.class, "getReceivedDate", "setReceivedDate", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Delivery Date", Shipment.class, "getDeliveryDate", "setDeliveryDate", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Required Delivery Date", Shipment.class, "getRequiredDeliveryDate", "setRequiredDeliveryDate", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.MasterBill Number", Shipment.class, "getMastBillNo", "setMastBillNo", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Carrier Name", Shipment.class, "getCarrierName", "setCarrierName", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Voyage Name", Shipment.class, "getVoyageName", "setVoyageName", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Voyage Number", Shipment.class, "getVoyageNumber", "setVoyageNumber", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.Flight Number", Shipment.class, "setFlightNumber", "setFlightNumber", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.User Defined 1", Shipment.class, "getUserDefined1", "setUserDefined1", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.User Defined 2", Shipment.class, "getUserDefined2", "setUserDefined2", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.User Defined 3", Shipment.class, "getUserDefined3", "setUserDefined3", SubTypes.Shipment);
		orderMetaDataService.load("In Customs Date", Shipment.class, "getInCustomerDate", "setInCustomerDate", SubTypes.Shipment);
		orderMetaDataService.load("Out Customs Date", Shipment.class, "getOutCustomerDate", "setOutCustomerDate", SubTypes.Shipment);
		
		orderMetaDataService.load("20 Ft Dry Bulk Cntrs", Shipment.class, "getDryBulkCntrs20Ft", "setDryBulkCntrs20Ft", SubTypes.Shipment);
		orderMetaDataService.load("20 Ft Dry Cntrs", Shipment.class, "getDryCntrs20Ft", "setDryCntrs20Ft", SubTypes.Shipment);
		orderMetaDataService.load("20 Ft Reefer Cntrs", Shipment.class, "getReeferCntrs20Ft", "setReeferCntrs20Ft", SubTypes.Shipment);
		orderMetaDataService.load("20 Ft Tank Cntrs", Shipment.class, "getTankCntrs20Ft", "setTankCntrs20Ft", SubTypes.Shipment);
		orderMetaDataService.load("20 Ft Flat Cntrs", Shipment.class, "getFlatCntrs20Ft", "setFlatCntrs20Ft", SubTypes.Shipment);
		orderMetaDataService.load("20 Ft High Cube Dry Cntrs", Shipment.class, "getHighCubeDryCntrs20Ft", "setHighCubeDryCntrs20Ft", SubTypes.Shipment);
		orderMetaDataService.load("20 Ft Insulated Cntrs", Shipment.class, "getInsulatedCntrs20Ft", "setInsulatedCntrs20Ft", SubTypes.Shipment);
		orderMetaDataService.load("20 Ft Open Top Cntrs", Shipment.class, "getOpenTopCntrs20Ft", "setOpenTopCntrs20Ft", SubTypes.Shipment);
		orderMetaDataService.load("40 Ft Dry Cntrs", Shipment.class, "getDryCntrs40Ft", "setDryCntrs40Ft", SubTypes.Shipment);
		orderMetaDataService.load("40 Ft High Cube Dry Cntrs", Shipment.class, "getHighCubeDryCntrs40Ft", "setHighCubeDryCntrs40Ft", SubTypes.Shipment);
		orderMetaDataService.load("40 Ft High Cube Reefer Cntrs", Shipment.class, "getHighCubeReeferCntrs40Ft", "setHighCubeReeferCntrs40Ft", SubTypes.Shipment);
		orderMetaDataService.load("40 Ft Reefer Cntrs", Shipment.class, "getReeferCntrs40Ft", "setReeferCntrs40Ft", SubTypes.Shipment);
		orderMetaDataService.load("45 Ft High Cube Dry Cntrs", Shipment.class, "getHighCubeDryCntrs45Ft", "setHighCubeDryCntrs45Ft", SubTypes.Shipment);
		orderMetaDataService.load("Other Cntrs", Shipment.class, "getOtherCntrs", "setOtherCntrs", SubTypes.Shipment);
		orderMetaDataService.load("HB Number", Shipment.class, "getHbNumber", "setHbNumber", SubTypes.Shipment);
		orderMetaDataService.load("Customs Number", Shipment.class, "getCustomsNumber", "setCustomsNumber", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.User Defined 4", Shipment.class, "getUserDefined4", "setUserDefined4", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.User Defined 5", Shipment.class, "getUserDefined5", "setUserDefined5", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.User Defined 6", Shipment.class, "getUserDefined6", "setUserDefined6", SubTypes.Shipment);
		orderMetaDataService.load("Shipment.User Defined 7", Shipment.class, "getUserDefined7", "setUserDefined7", SubTypes.Shipment);
		return StatusTypes.Successful;
	}
	
	public StatusTypes loadCustomMetaModel() throws RuntimeException {
		customMetaDataService.load("Imp Code", Custom.class, "getImpCode", "setImpCode", SubTypes.Custom);
		customMetaDataService.load("Mode", Custom.class, "getMode", "setMode", SubTypes.Custom);
		customMetaDataService.load("Part Number", Custom.class, "getPartNo", "setPartNo", SubTypes.Custom);
		customMetaDataService.load("Part Name", Custom.class, "getPartName", "setPartName", SubTypes.Custom);
		customMetaDataService.load("Class of Part", Custom.class, "getClassOfPart", "setClassOfPart", SubTypes.Custom);
		customMetaDataService.load("Inco.", Custom.class, "getInco", "setInco", SubTypes.Custom);
		customMetaDataService.load("Total Quantity", Custom.class, "getTotalQuantity", "setTotalQuantity", SubTypes.Custom);
		customMetaDataService.load("Job Number", Custom.class, "getJobNumber", "setJobNumber", SubTypes.Custom);
		customMetaDataService.load("Shipment Number", Custom.class, "getShipmentNumber", "setShipmentNumber", SubTypes.Custom);
		
		customMetaDataService.load("HB Origin", Custom.class, "getHbOrigin", "setHbOrigin", SubTypes.Custom);
		customMetaDataService.load("Item Origin", Custom.class, "getItemOrig", "setItemOrig", SubTypes.Custom);
		customMetaDataService.load("Master Bill Number", Custom.class, "getMasterBillNumber", "setMasterBillNumber", SubTypes.Custom);
		customMetaDataService.load("Vessel", Custom.class, "getVessel", "setVessel", SubTypes.Custom);
		customMetaDataService.load("Voyage Flight", Custom.class, "getVoyageFlight", "setVoyageFlight", SubTypes.Custom);
		customMetaDataService.load("Tariff", Custom.class, "getTariff", "setTariff", SubTypes.Custom);
		customMetaDataService.load("Tariff Desc", Custom.class, "getTariffDesc", "setTariffDesc", SubTypes.Custom);
		customMetaDataService.load("Part AUS VFD", Custom.class, "getPartAusVFD", "setPartAusVFD", SubTypes.Custom);
		customMetaDataService.load("Duty", Custom.class, "getDuty", "setDuty", SubTypes.Custom);
		
		customMetaDataService.load("FRT Amount", Custom.class, "getFrtAmount", "setFrtAmount", SubTypes.Custom);
		customMetaDataService.load("Cart Amount", Custom.class, "getCartAmount", "setCartAmount", SubTypes.Custom);
		customMetaDataService.load("Other Amount", Custom.class, "getOtherAmount", "setOtherAmount", SubTypes.Custom);
		customMetaDataService.load("GST", Custom.class, "getGst", "setGst", SubTypes.Custom);
		customMetaDataService.load("Total Amount", Custom.class, "getTotalAmount", "setTotalAmount", SubTypes.Custom);
		customMetaDataService.load("First Invoice Date", Custom.class, "getFirstInvoiceDate", "setFirstInvoiceDate", SubTypes.Custom);
		customMetaDataService.load("Invoice Number", Custom.class, "getInvoiceNumber", "setInvoiceNumber", SubTypes.Custom);
		customMetaDataService.load("Last Invoice Date", Custom.class, "getLastInvoiceDate", "setLastInvoiceDate", SubTypes.Custom);
		customMetaDataService.load("Last Invoice Number", Custom.class, "getLastInvoiceNumber", "setLastInvoiceNumber", SubTypes.Custom);
		customMetaDataService.load("User Defined 1", Custom.class, "getUserdefined1", "setUserdefined1", SubTypes.Custom);
		customMetaDataService.load("User Defined 2", Custom.class, "getUserdefined2", "setUserdefined2", SubTypes.Custom);
		customMetaDataService.load("User Defined 3", Custom.class, "getUserdefined3", "setUserdefined3", SubTypes.Custom);
		customMetaDataService.load("Line No", Custom.class, "getLineNo", "setLineNo", SubTypes.Custom);
		return StatusTypes.Successful;
	}
	
	public StatusTypes loadContainerStatusMetaModel() throws RuntimeException {
		containerMetaDataService.load("Job Number", ContainerStatus.class, "getJobNumber", "setJobNumber", SubTypes.ContainerStatus);
		containerMetaDataService.load("Vessel", ContainerStatus.class, "getVessel", "setVessel", SubTypes.ContainerStatus);
		containerMetaDataService.load("Voyage", ContainerStatus.class, "getVoyage", "setVoyage", SubTypes.ContainerStatus);
		containerMetaDataService.load("Destination Port", ContainerStatus.class, "getDestPort", "setDestPort", SubTypes.ContainerStatus);
		containerMetaDataService.load("Container Number", ContainerStatus.class, "getContainerNumber", "setContainerNumber", SubTypes.ContainerStatus);
		containerMetaDataService.load("Shipment Type", ContainerStatus.class, "getShipmentType", "setShipmentType", SubTypes.ContainerStatus);
		containerMetaDataService.load("Container Size", ContainerStatus.class, "getContainerSize", "setContainerSize", SubTypes.ContainerStatus);
		containerMetaDataService.load("Weight", ContainerStatus.class, "getWieght", "setWieght", SubTypes.ContainerStatus);
		containerMetaDataService.load("ETA Date", ContainerStatus.class, "getEtaDate", "setEtaDate", SubTypes.ContainerStatus);
		containerMetaDataService.load("Warf Available Date", ContainerStatus.class, "getWarfAvailDate", "setWarfAvailDate", SubTypes.ContainerStatus);
		containerMetaDataService.load("Warf Storage Date", ContainerStatus.class, "setWharfStoreDate", "setWharfStoreDate", SubTypes.ContainerStatus);
		containerMetaDataService.load("Yard Avaliable Date", ContainerStatus.class, "getYardAvailableDate", "setYardAvailableDate", SubTypes.ContainerStatus);
		containerMetaDataService.load("Estimated Delivery Date", ContainerStatus.class, "getEstDelDate", "setEstDelDate", SubTypes.ContainerStatus);
		containerMetaDataService.load("Estimated Delivery Time", ContainerStatus.class, "getEstDelTime", "setEstDelTime", SubTypes.ContainerStatus);
		containerMetaDataService.load("Delivery Location", ContainerStatus.class, "getDeliveryLocation", "setDeliveryLocation", SubTypes.ContainerStatus);
		containerMetaDataService.load("Delivery Date", ContainerStatus.class, "getDeliveryDate", "setDeliveryDate", SubTypes.ContainerStatus);
		containerMetaDataService.load("Detention Date", ContainerStatus.class, "getDetentionDate", "setDetentionDate", SubTypes.ContainerStatus);
		containerMetaDataService.load("Empty Return Target Date", ContainerStatus.class, "getEmptyRetTargetDate", "setEmptyRetTargetDate", SubTypes.ContainerStatus);
		containerMetaDataService.load("Empty Return Date", ContainerStatus.class, "getEmptyRetDate", "setEmptyRetDate", SubTypes.ContainerStatus);
		containerMetaDataService.load("Dehire Date", ContainerStatus.class, "getDehireDate", "setDehireDate", SubTypes.ContainerStatus);
		containerMetaDataService.load("Shipment Number", ContainerStatus.class, "getShipmentNumber", "setShipmentNumber", SubTypes.ContainerStatus);
		containerMetaDataService.load("User Defined1", ContainerStatus.class, "getUserDefined1", "setUserDefined1", SubTypes.ContainerStatus);
		containerMetaDataService.load("User Defined2", ContainerStatus.class, "getUserDefined2", "setUserDefined2", SubTypes.ContainerStatus);
		containerMetaDataService.load("User Defined3", ContainerStatus.class, "getUserDefined3", "setUserDefined3", SubTypes.ContainerStatus);
		return StatusTypes.Successful;
	}
}