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
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;
import com.enterprise.domain.util.EntityUtils;

@Entity
@Table(name = "CONTAINER_TYPE_ALIAS", schema = "GLOBAL")
@org.hibernate.annotations.Table(
	appliesTo = "CONTAINER_TYPE_ALIAS", 
	indexes = { 
		@Index(name = "container_type_alias_id_ix", columnNames = { "ID" } )
	}
)
public class ContainerTypeAlias  implements IEntity, Serializable {

	private static final long serialVersionUID = 4126189716908370195L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CONTAINER_TYPE_ALIAS_SEQ")
	@SequenceGenerator(name = "CONTAINER_TYPE_ALIAS_SEQ", sequenceName = "GLOBAL.CONTAINER_TYPE_ALIAS_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name = "ALIAS_TYPE_NAME")
	private String aliasTypeName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CONTAINER_TYPE_ID", referencedColumnName = "ID", nullable = true)
	})
	private ContainerType containerType;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getAliasTypeName() {
		return aliasTypeName;
	}

	public void setAliasTypeName(String aliasTypeName) {
		this.aliasTypeName = aliasTypeName;
	}

	public ContainerType getContainerType() {
		return EntityUtils.initializeAndUnproxy(containerType);
	}

	public void setContainerType(ContainerType containerType) {
		this.containerType = containerType;
	}

	public boolean isEmpty() {
		return false;
	}
}
