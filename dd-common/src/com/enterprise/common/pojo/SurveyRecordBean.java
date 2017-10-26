package com.enterprise.common.pojo;

import com.enterprise.common.interfaces.impl.AbstractRecordBean;

public class SurveyRecordBean extends AbstractRecordBean {
	private static final long serialVersionUID = -6980539630473448832L;
	private String age;
	private String sex;
	private String locality;
	private String subLocality;
	private String administrativeAreaLevel1;
    private String administrativeAreaLevel2;
    private String administrativeAreaLevel3;
    private String country;
    private String maleAdultAtAddress;
	private String femaleAdultAtAddress;
	private String childrenAtAddress;
	private String foodExpenditure;
	private String airtimeExpenditure;
	private String transportExpenditure;
	private String clothingExpenditure;

	@Override
	public long getRecordId() {
		return 0;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getSubLocality() {
		return subLocality;
	}

	public void setSubLocality(String subLocality) {
		this.subLocality = subLocality;
	}

	public String getAdministrativeAreaLevel1() {
		return administrativeAreaLevel1;
	}

	public void setAdministrativeAreaLevel1(String administrativeAreaLevel1) {
		this.administrativeAreaLevel1 = administrativeAreaLevel1;
	}

	public String getAdministrativeAreaLevel2() {
		return administrativeAreaLevel2;
	}

	public void setAdministrativeAreaLevel2(String administrativeAreaLevel2) {
		this.administrativeAreaLevel2 = administrativeAreaLevel2;
	}

	public String getAdministrativeAreaLevel3() {
		return administrativeAreaLevel3;
	}

	public void setAdministrativeAreaLevel3(String administrativeAreaLevel3) {
		this.administrativeAreaLevel3 = administrativeAreaLevel3;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}