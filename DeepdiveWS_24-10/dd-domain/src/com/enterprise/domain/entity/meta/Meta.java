package com.enterprise.domain.entity.meta;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IMeta;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.common.util.StringUtils;
import com.enterprise.domain.entity.Package;
import com.enterprise.domain.entity.SubPackageDetail;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  11 Mar 2014 2:27:16 PM
 * @author rafourie
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "DTYPE",
    discriminatorType = DiscriminatorType.STRING,
    length = 64
)
@Table(name = "META", 
		schema = "GLOBAL", 
		uniqueConstraints = @UniqueConstraint(columnNames = {"COLUMN_NAME", "DTYPE", "SUB_PACKAGE_ID"})
)
@org.hibernate.annotations.Table(
	appliesTo = "META", 
	indexes = { 
		@Index(name = "meta_id_ix", columnNames = { "ID" } ),
		@Index(name = "meta_column_name_ix", columnNames = { "COLUMN_NAME" } ),
		@Index(name = "meta_search_name_ix", columnNames = { "SEARCH_NAME" } )
	}
)
public class Meta implements IMeta, Serializable {
	private static final long serialVersionUID = 5406778902073736926L;

	/** ID decimal(19) PRIMARY KEY NOT NULL, */
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "META_SEQ")
	@SequenceGenerator(name = "META_SEQ", sequenceName = "GLOBAL.META_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	/** COLUMN_NAME varchar2(128) NOT NULL, */
	@Column(name = "COLUMN_NAME", nullable = false, length = 128)
	@Cascade(org.hibernate.annotations.CascadeType.PERSIST)
	private String columnName;
	
	/** SEARCH_NAME varchar2(128) NOT NULL, */
	@Column(name = "SEARCH_NAME", nullable = false, length = 128)
	private String searchName;
	
	/** ENTITY_CLASS varchar2(128) NOT NULL, */
	@Column(name = "ENTITY_CLASS", nullable = false, length = 128)
	private String entityClass;
	
	/** ENTITY_GETTER varchar2(128) NOT NULL, */
	@Column(name = "ENTITY_GETTER", nullable = false, length = 64)
	private String entityGetter;
	
	/** ENTITY_SETTER varchar2(128) NOT NULL, */
	@Column(name = "ENTITY_SETTER", nullable = false, length = 64)
	private String entitySetter;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SUB_TYPE", nullable = false, length = 32)
	private SubTypes subType;
	
	/** PARENT_ID decimal(19), */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "PARENT_ID", referencedColumnName = "ID", nullable = true)
	})
	private Meta parent;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "PACKAGE_ID", referencedColumnName = "ID", nullable = true)
	})
	private Package pkg;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SUB_PACKAGE_ID", referencedColumnName = "ID", nullable = true)
	})
	private SubPackageDetail subPkg;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public String getEntityGetter() {
		return entityGetter;
	}

	public void setEntityGetter(String entityGetter) {
		this.entityGetter = entityGetter;
	}

	public String getEntitySetter() {
		return entitySetter;
	}

	public void setEntitySetter(String entitySetter) {
		this.entitySetter = entitySetter;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
		this.searchName = StringUtils.spacelessLowerCase(columnName);
	}

	public String getSearchName() {
		return searchName;
	}

	public IMeta clone() throws RuntimeException {
		throw new RuntimeException("This method must be overridden in subclass.");
	}

	public SubTypes getSubType() {
		return subType;
	}

	public void setSubType(SubTypes subType) {
		this.subType = subType;
	}

	public Meta getParent() {
		return parent;
	}

	public void setParent(IMeta parent) {
		this.parent = (Meta)parent;
	}
	
	public Package getPkg() {
		return pkg;
	}

	public void setPkg(Package pkg) {
		this.pkg = pkg;
	}

	public boolean isEmpty() {
		return false;
	}
	
	@Transient
	public String getDiscriminatorValue(){
	    DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);
	    return val == null ? null : val.value();
	}

	public SubPackageDetail getSubPkg() {
		return subPkg;
	}

	public void setSubPkg(SubPackageDetail subPkg) {
		this.subPkg = subPkg;
	}
}