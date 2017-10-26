package com.enterprise.common.pojo.sensus;

import java.io.Serializable;

import com.enterprise.common.util.Constants;
import com.enterprise.common.util.StringUtils;

public class SurveyDataBean implements Serializable {
	private static final long serialVersionUID = 5277618806428144168L;
	private long currentTimeMillis;
	private String latitude;
    private String longitude;
	private String name;
	private String surname;
	private String idNumber;
	private String age;
	private String sex;
	private HouseholdSensusDataBean householdSensusData = new HouseholdSensusDataBean();
    //geocode address details
    private String subLocality;
    private String locality;
    private String administrativeAreaLevel1;
    private String administrativeAreaLevel2;
    private String administrativeAreaLevel3;
    private String country;
	
	public SurveyDataBean() {
		currentTimeMillis = System.currentTimeMillis();
		householdSensusData = new HouseholdSensusDataBean();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getIdNumber() {
		return (StringUtils.isEmpty(idNumber) ? Constants.UNKNOWN_STR : idNumber);
	}
	
	public void setIdNumber(String idNumber) {
		this.idNumber = (StringUtils.isEmpty(idNumber) || Constants.UNKNOWN_STR.equals(idNumber) ? null : idNumber);
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
	
	public HouseholdSensusDataBean getHouseholdSensusData() {
		return householdSensusData;
	}
	
	public void setHouseholdSensusData(HouseholdSensusDataBean householdSensusData) {
		this.householdSensusData = householdSensusData;
	}

	public long getCurrentTimeMillis() {
		return currentTimeMillis;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getSubLocality() {
		return subLocality;
	}

	public void setSubLocality(String subLocality) {
		this.subLocality = subLocality;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
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

	public void setCurrentTimeMillis(long currentTimeMillis) {
		this.currentTimeMillis = currentTimeMillis;
	}
}