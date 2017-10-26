package com.enterprise.domain.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Index;

import com.enterprise.common.entity.ISubPkgMappingRule;
import com.enterprise.domain.entity.meta.Meta;

@Entity
@javax.persistence.Table(name = "SUB_PKG_MAPPING_RULE", schema = "GLOBAL")
@org.hibernate.annotations.Table(
	appliesTo = "SUB_PKG_MAPPING_RULE", 
	indexes = {
		@Index(name = "sub_package_mapping_rule_ix", columnNames = { "ID" } )
	}
)
@org.hibernate.annotations.Entity(
		dynamicInsert = true
)
public class SubPkgMappingRule implements ISubPkgMappingRule, Serializable {

	private static final long serialVersionUID = -2140175638518987021L;
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SUB_PKG_MAPPING_RULE")
	@SequenceGenerator(name = "SUB_PKG_MAPPING_RULE_SEQ", sequenceName = "GLOBAL.SUB_PKG_MAPPING_RULE_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name = "IS_LOAD", nullable = false)
	private boolean isLoad = false; 
	
	@Column(name = "CONDITION", nullable = false, length = 45)
	private String condition;
	
	@Column(name = "VALUE", nullable = false, length = 45)
	private String value;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SUB_PACKAGE_ID", referencedColumnName = "ID", nullable = false)
	})
	private SubPackageDetail subPackageDetail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "META_ID", referencedColumnName = "ID", nullable = false)
	})
	private Meta meta;

	public long getId() {
		return id;
	} 

	public void setId(long id) {
		this.id = id;
	}

	public boolean isLoad() {
		return isLoad;
	}

	public void setLoad(boolean isLoad) {
		this.isLoad = isLoad;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public SubPackageDetail getSubPackageDetail() {
		return subPackageDetail;
	}

	public void setSubPackageDetail(SubPackageDetail subPackageDetail) {
		this.subPackageDetail = subPackageDetail;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public boolean isEmpty() {
		return false;
	}
}
