package com.enterprise.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.Index;
import org.hibernate.criterion.Restrictions;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.enums.ModeTypes;
import com.enterprise.common.util.DateUtils;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "RATE", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"LANE_ID", "PACKAGE_ID"})
)
@org.hibernate.annotations.Table(
	appliesTo = "RATE", 
	indexes = { 
		@Index(name = "rate_id_ix", columnNames = { "ID" } )
	}
)
public class Rate implements IEntity, Serializable {

	private static final long serialVersionUID = 6718156787507631011L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RATE_SEQ")
	@SequenceGenerator(name = "RATE_SEQ", sequenceName = "GLOBAL.RATE_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name="LANE_ID", nullable = false)
	private String laneId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MODE", nullable = false, length = 1)
	private ModeTypes mode;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORIG_PORT_ID", referencedColumnName = "ID", nullable = false)
	})
	private Location origPort;
	
	/** DEST_PORT_ID decimal(19), */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DEST_PORT_ID", referencedColumnName = "ID", nullable = false)
	})
	private Location destPort;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORIGIN_COUNTRY_ID", referencedColumnName = "ID", nullable = true)
	})
	private Country originCountry;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DEST_COUNTRY_ID", referencedColumnName = "ID", nullable = true)
	})
	private Country destCountry;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORIGIN_CITY_COUNTRY_ID", referencedColumnName = "ID", nullable = true)
	})
	private Country originCityCountry;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DEST_CITY_COUNTRY_ID", referencedColumnName = "ID", nullable = true)
	})
	private Country destCityCountry;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "PACKAGE_ID", referencedColumnName = "ID", nullable = true)
	})
	private Package pkg;
	
	@OneToMany(mappedBy = "rate", fetch = FetchType.LAZY)
	private List<RateDetail> rateDetails = new ArrayList<RateDetail>();
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "LAST_UPDATED_USER", referencedColumnName = "ID", nullable = true)
	})
	private User lastEditedUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BASE_RATE_VALIDITY_START", nullable = false)
	private Date validityStartDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BASE_RATE_VALIDITY_END", nullable = false)
	private Date validityEndDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON")
	private Date updatedOn = new Date();
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLaneId() {
		return laneId;
	}

	public void setLaneId(String laneId) {
		this.laneId = laneId;
	}

	public ModeTypes getMode() {
		return mode;
	}

	public void setMode(ModeTypes mode) {
		this.mode = mode;
	}
	
	public void setMode(String mode) {
		ModeTypes modeType = null;
		try {
			modeType = ModeTypes.valueOf(mode);
			this.mode = modeType;
		} catch (Throwable e) {
			modeType = ModeTypes.valueOfByMode(mode);
			if (modeType == null)
				throw new RuntimeException("Value [" + mode + "] as the rate mode is invalid. The system expects " + 
						"a 1 character value of either 'A','O' or 'M'.");
			this.mode = modeType;
		}
	}
	
	public Location getOrigPort() {
		return origPort;
	}

	public void setOrigPort(Location origPort) {
		this.origPort = origPort;
	}
	
	public void setOrigPort(IPort origPort) {
		this.origPort = (Location)origPort;
	}

	public Location getDestPort() {
		return destPort;
	}

	public void setDestPort(Location destPort) {
		this.destPort = destPort;
	}
	
	public void setDestPort(IPort destPort) {
		this.destPort = (Location)destPort;
	}
	
	public Package getPkg() {
		return pkg;
	}

	public void setPkg(Package pkg) {
		this.pkg = pkg;
	}
	
	public List<RateDetail> getRateDetails() {
		return rateDetails;
	}

	public void setRateDetails(List<RateDetail> rateDetails) {
		this.rateDetails = rateDetails;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static List<Rate> load(String laneId, Session session, long packageId) {
		Criteria criteria = session.createCriteria(Rate.class)
			.add(Restrictions.eq("laneId", laneId));
		criteria.setFetchMode("rateDetails", FetchMode.LAZY);
		if(packageId != 0L)
			criteria.add(Restrictions.eq("pkg.id", packageId));
		List<Rate> rates = criteria.list();
		return rates;
	}
	
	public static void setRateDetailInactive(Set<Long> ids, Set<Long> rateIds, Session session) {
		if(ids!=null && rateIds!=null && !ids.isEmpty() && !rateIds.isEmpty()) {
			String hql = "UPDATE RateDetail set isActive = :isActive "  + 
		             "WHERE id not in(:id) and rate.id in(:rateIds)";
			Query query = session.createQuery(hql);
			query.setParameter("isActive", false);
			query.setParameterList("id", ids);
			query.setParameterList("rateIds", rateIds);
			query.executeUpdate();
		}
	}
	
	public static void resetOldStartDate(Long id, Date newStartDate, Session session) {
		String hql = "UPDATE Rate set validityStartDate = :validityStartDate "  + 
	             "WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("validityStartDate", newStartDate);
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	public static void resetOldEndDate(Long id, Date newEndDate, Session session) {
		String hql = "UPDATE Rate set validityEndDate = :validityEndDate "  + 
	             "WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("validityEndDate", newEndDate);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	public boolean isEmpty() {
		return false;
	}

	public User getLastEditedUser() {
		return lastEditedUser;
	}

	public void setLastEditedUser(User lastEditedUser) {
		this.lastEditedUser = lastEditedUser;
	}

	public Country getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(Country originCountry) {
		this.originCountry = originCountry;
	}

	public Country getDestCountry() {
		return destCountry;
	}

	public void setDestCountry(Country destCountry) {
		this.destCountry = destCountry;
	}

	public Date getValidityStartDate() {
		return validityStartDate;
	}

	public void setValidityStartDate(Date validityStartDate) {
		this.validityStartDate = validityStartDate;
	}
	
	public void setValidityStartDate(String validityStartDate) {		
		try {
			this.validityStartDate = DateUtils.toValidatedDate(StringUtils.upper(validityStartDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + validityStartDate + "] as the validity start date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getValidityEndDate() {
		return validityEndDate;
	}

	public void setValidityEndDate(Date validityEndDate) {
		this.validityEndDate = validityEndDate;
	}
	
	public void setValidityEndDate(String validityEndDate) {		
		try {
			this.validityEndDate = DateUtils.toValidatedDate(StringUtils.upper(validityEndDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + validityEndDate + "] as the validity end date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public void setOriginCityCountry(Country originCityCountry) {
		this.originCityCountry = originCityCountry;
	}

	public Country getOriginCityCountry() {
		return originCityCountry;
	}

	public void setDestCityCountry(Country destCityCountry) {
		this.destCityCountry = destCityCountry;
	}

	public Country getDestCityCountry() {
		return destCityCountry;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}
}