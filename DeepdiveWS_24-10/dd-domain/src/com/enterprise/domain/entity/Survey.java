package com.enterprise.domain.entity;



/*@Entity
@Table(name = "SURVEY")
@org.hibernate.annotations.Table(
	appliesTo = "SURVEY", 
	indexes = { 
		@Index(name = "currentTimeMillis_idx", columnNames = { "CURRENT_TIME_MILLIS" } ),
		@Index(name = "idNumber_idx", columnNames = { "ID_NUMBER" } ),
		@Index(name = "subLocality_idx", columnNames = { "SUB_LOCALITY" } ),
		@Index(name = "locality_idx", columnNames = { "LOCALITY" } ),
		@Index(name = "administrativeAreaLevel3_idx", columnNames = { "ADMINISTRATIVE_AREA_LEVEL_3" } ),
		@Index(name = "administrativeAreaLevel2_idx", columnNames = { "ADMINISTRATIVE_AREA_LEVEL_2" } ),
		@Index(name = "administrativeAreaLevel1_idx", columnNames = { "ADMINISTRATIVE_AREA_LEVEL_1" } ),
		@Index(name = "country_idx", columnNames = { "COUNTRY" } ),
		@Index(name = "timestamp_idx", columnNames = { "TIMESTAMP" } )
	}
)*/
public class Survey /*implements IEntity, Serializable*/ {
	private static final long serialVersionUID = -6234638335517051195L;
/*
	@Id
	@GeneratedValue
    @Column(name = "ID", nullable = false)
    long id;
	
	@Column(name = "CURRENT_TIME_MILLIS", nullable = false)
	private long currentTimeMillis;
	
	@Column(name = "LATITUDE", nullable = false, length = 16)
	private String latitude;
	
	@Column(name = "LONGITUDE", nullable = false, length = 16)
	private String longitude;
	
	@Column(name = "NAME", nullable = true, length = 256)
	private String name;

	@Column(name = "SURNAME", nullable = true, length = 256)
	private String surname;
	
	@Column(name = "ID_NUMBER", nullable = true, length = 13)
	private String idNumber;
	
	@Column(name = "AGE", nullable = true, length = 3)
	private String age;
	
	@Column(name = "SEX", nullable = true, length = 1)
	private String sex;
	
	@Column(name = "MALE_ADULT_AT_ADDRESS", nullable = false)
	private int maleAdultAtAddress;
	
	@Column(name = "FEMALE_ADULT_AT_ADDRESS", nullable = false)
	private int femaleAdultAtAddress;
	
	@Column(name = "CHILDREN_AT_ADDRESS", nullable = false)
	private int childrenAtAddress;
	
	@Column(name = "FOOD_EXPENDITURE", nullable = false, precision = 11, scale = 2)
	private BigDecimal foodExpenditure = BigDecimal.ZERO;
	
	@Column(name = "AIRTIME_EXPENDITURE", nullable = false, precision = 11, scale = 2)
	private BigDecimal airtimeExpenditure = BigDecimal.ZERO;
	
	@Column(name = "TRANSPORT_EXPENDITURE", nullable = false, precision = 11, scale = 2)
	private BigDecimal transportExpenditure = BigDecimal.ZERO;
	
	@Column(name = "CLOTHING_EXPENDITURE", nullable = false, precision = 11, scale = 2)
	private BigDecimal clothingExpenditure = BigDecimal.ZERO;
	
	@Column(name = "SUB_LOCALITY", nullable = true, length = 256) 
	private String subLocality;
	
	@Column(name = "LOCALITY", nullable = true, length = 256) 
	private String locality;
	
	@Column(name = "ADMINISTRATIVE_AREA_LEVEL_3", nullable = true, length = 256) 
	private String administrativeAreaLevel3;
	
	@Column(name = "ADMINISTRATIVE_AREA_LEVEL_2", nullable = true, length = 256) 
	private String administrativeAreaLevel2;
	
	@Column(name = "ADMINISTRATIVE_AREA_LEVEL_1", nullable = true, length = 256) 
	private String administrativeAreaLevel1;
	
	@Column(name = "COUNTRY", nullable = true, length = 256) 
	private String country;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP", nullable = false)
    private Date timestamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCurrentTimeMillis() {
		return currentTimeMillis;
	}

	public void setCurrentTimeMillis(long currentTimeMillis) {
		this.currentTimeMillis = currentTimeMillis;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
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

	public int getMaleAdultAtAddress() {
		return maleAdultAtAddress;
	}

	public void setMaleAdultAtAddress(int maleAdultAtAddress) {
		this.maleAdultAtAddress = maleAdultAtAddress;
	}

	public int getFemaleAdultAtAddress() {
		return femaleAdultAtAddress;
	}

	public void setFemaleAdultAtAddress(int femaleAdultAtAddress) {
		this.femaleAdultAtAddress = femaleAdultAtAddress;
	}

	public int getChildrenAtAddress() {
		return childrenAtAddress;
	}

	public void setChildrenAtAddress(int childrenAtAddress) {
		this.childrenAtAddress = childrenAtAddress;
	}

	public BigDecimal getFoodExpenditure() {
		return foodExpenditure;
	}

	public void setFoodExpenditure(BigDecimal foodExpenditure) {
		this.foodExpenditure = foodExpenditure;
	}

	public BigDecimal getAirtimeExpenditure() {
		return airtimeExpenditure;
	}

	public void setAirtimeExpenditure(BigDecimal airtimeExpenditure) {
		this.airtimeExpenditure = airtimeExpenditure;
	}

	public BigDecimal getTransportExpenditure() {
		return transportExpenditure;
	}

	public void setTransportExpenditure(BigDecimal transportExpenditure) {
		this.transportExpenditure = transportExpenditure;
	}

	public BigDecimal getClothingExpenditure() {
		return clothingExpenditure;
	}

	public void setClothingExpenditure(BigDecimal clothingExpenditure) {
		this.clothingExpenditure = clothingExpenditure;
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

	public String getAdministrativeAreaLevel3() {
		return administrativeAreaLevel3;
	}

	public void setAdministrativeAreaLevel3(String administrativeAreaLevel3) {
		this.administrativeAreaLevel3 = administrativeAreaLevel3;
	}

	public String getAdministrativeAreaLevel2() {
		return administrativeAreaLevel2;
	}

	public void setAdministrativeAreaLevel2(String administrativeAreaLevel2) {
		this.administrativeAreaLevel2 = administrativeAreaLevel2;
	}

	public String getAdministrativeAreaLevel1() {
		return administrativeAreaLevel1;
	}

	public void setAdministrativeAreaLevel1(String administrativeAreaLevel1) {
		this.administrativeAreaLevel1 = administrativeAreaLevel1;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}*/
}