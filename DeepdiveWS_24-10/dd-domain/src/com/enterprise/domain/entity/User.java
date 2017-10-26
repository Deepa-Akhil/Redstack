package com.enterprise.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;

@Entity
@javax.persistence.Table(name = "USER", schema = "GLOBAL")
@org.hibernate.annotations.Table(
	appliesTo = "USER", 
	indexes = {
		@Index(name = "user_username_ix", columnNames = { "USERNAME" } )
	}
)
public class User implements IEntity {

	/** ID decimal(19) PRIMARY KEY NOT NULL, */
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ")
	@SequenceGenerator(name = "USER_SEQ", sequenceName = "GLOBAL.USER_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	/** USERNAME varchar2(128) NOT NULL, */
	@Column(name = "USERNAME", nullable = false, length = 128)
	private String username;
	
	/** REGISTRATION_DATE timestamp DEFAULT SYSDATE  NOT NULL, */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGISTRATION_DATE", nullable = false)
	private Date registrationDate = new Date();
	
    @Column(name = "PASSWORD", nullable = false, length = 128)
	private String password;
    
    @Column(name = "FIRSTNAME", nullable = false, length = 45)
   	private String firstName;
    
    @Column(name = "LASTNAME", nullable = true, length = 45)
   	private String lastName;
    
    @Column(name="USER_ROLE", columnDefinition = "TINYINT", length=1, updatable = false)
    private int userRole = 1;
    
    @OneToMany(mappedBy = "createdUser", fetch = FetchType.LAZY)
	private List<Package> packages = new ArrayList<Package>();
    
    
    @OneToMany(mappedBy = "lastEditedUser", fetch = FetchType.LAZY)
   	private List<Package> lastEditedPackages = new ArrayList<Package>();
    
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
   	private List<UserAccess> userAccesses = new ArrayList<UserAccess>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<LoadHistory> loadHistories = new ArrayList<LoadHistory>();
	
	@OneToMany(mappedBy = "lastEditedUser", fetch = FetchType.LAZY)
	private List<Rate> rates = new ArrayList<Rate>();
	
	@OneToMany(mappedBy = "createdUser", fetch = FetchType.LAZY)
	private List<Package> createdSubpackages = new ArrayList<Package>();
	    
	    
	@OneToMany(mappedBy = "lastEditedUser", fetch = FetchType.LAZY)
	private List<Package> lastEditedSubpackages = new ArrayList<Package>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<MenuFunction> menuFunctions = new ArrayList<MenuFunction>();
	
	@Column(name = "EMAIL_ID", nullable = false, length = 45)
   	private String emailId;
	
	@Column(name = "COMPANY_NAME", nullable = true, length = 45)
   	private String companyName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}
	
	public List<UserAccess> getUserAccesses() {
		return userAccesses;
	}

	public void setUserAccesses(List<UserAccess> userAccesses) {
		this.userAccesses = userAccesses;
	}
	
	public List<Package> getLastEditedPackages() {
		return lastEditedPackages;
	}

	public void setLastEditedPackages(List<Package> lastEditedPackages) {
		this.lastEditedPackages = lastEditedPackages;
	}
	
	public List<LoadHistory> getLoadHistories() {
		return loadHistories;
	}

	public void setLoadHistories(List<LoadHistory> loadHistories) {
		this.loadHistories = loadHistories;
	}
	
	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public IEntity clone() {
		return null;
	}

	public boolean isEmpty() {
		return false;
	}

	public List<Package> getCreatedSubpackages() {
		return createdSubpackages;
	}

	public void setCreatedSubpackages(List<Package> createdSubpackages) {
		this.createdSubpackages = createdSubpackages;
	}

	public List<Package> getLastEditedSubpackages() {
		return lastEditedSubpackages;
	}

	public void setLastEditedSubpackages(List<Package> lastEditedSubpackages) {
		this.lastEditedSubpackages = lastEditedSubpackages;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setMenuFunctions(List<MenuFunction> menuFunctions) {
		this.menuFunctions = menuFunctions;
	}

	public List<MenuFunction> getMenuFunctions() {
		return menuFunctions;
	}
}