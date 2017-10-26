package com.enterprise.common.pojo.sensus;

import java.io.Serializable;

public class HouseholdSensusDataBean implements Serializable {
	private static final long serialVersionUID = -3817721582320426424L;
	private String maleAdultAtAddress;
	private String femaleAdultAtAddress;
	private String childrenAtAddress;
	private String foodExpenditure;
	private String airtimeExpenditure;
	private String transportExpenditure;
	private String clothingExpenditure;
	
	public String getMaleAdultAtAddress() {
		return maleAdultAtAddress;
	}
	
	public void setMaleAdultAtAddress(String maleAdultAtAddress) {
		this.maleAdultAtAddress = maleAdultAtAddress;
	}
	
	public String getFemaleAdultAtAddress() {
		return femaleAdultAtAddress;
	}
	
	public void setFemaleAdultAtAddress(String femaleAdultAtAddress) {
		this.femaleAdultAtAddress = femaleAdultAtAddress;
	}
	
	public String getChildrenAtAddress() {
		return childrenAtAddress;
	}
	
	public void setChildrenAtAddress(String childrenAtAddress) {
		this.childrenAtAddress = childrenAtAddress;
	}
	
	public String getFoodExpenditure() {
		return foodExpenditure;
	}
	
	public void setFoodExpenditure(String foodExpenditure) {
		this.foodExpenditure = foodExpenditure;
	}
	
	public String getAirtimeExpenditure() {
		return airtimeExpenditure;
	}
	
	public void setAirtimeExpenditure(String airtimeExpenditure) {
		this.airtimeExpenditure = airtimeExpenditure;
	}
	
	public String getTransportExpenditure() {
		return transportExpenditure;
	}
	
	public void setTransportExpenditure(String transportExpenditure) {
		this.transportExpenditure = transportExpenditure;
	}
	
	public String getClothingExpenditure() {
		return clothingExpenditure;
	}
	
	public void setClothingExpenditure(String clothingExpenditure) {
		this.clothingExpenditure = clothingExpenditure;
	}
}