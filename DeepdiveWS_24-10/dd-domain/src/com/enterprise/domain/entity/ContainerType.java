package com.enterprise.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "CONTAINER_TYPE", schema = "GLOBAL")
@org.hibernate.annotations.Table(
	appliesTo = "CONTAINER_TYPE", 
	indexes = { 
		@Index(name = "container_type_id_ix", columnNames = { "ID" } ),
	}
)
public class ContainerType implements IEntity, Serializable {

	private static final long serialVersionUID = 3974915165872263360L;
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CONTAINER_TYPE_SEQ")
	@SequenceGenerator(name = "CONTAINER_TYPE_SEQ", sequenceName = "GLOBAL.CONTAINER_TYPE_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name="ISO_TYPE", nullable = false, length = 4)
	private String type;
	
	@Column(name="ISO_DESCRIPTION", nullable = true, length = 255)
	private String containerDesc;
	
	@OneToMany(mappedBy = "containerType", fetch = FetchType.LAZY)
	private List<ContainerTypeAlias> containerTypeAlias = new ArrayList<ContainerTypeAlias>();
	
	@OneToMany(mappedBy = "containerType", fetch = FetchType.LAZY)
	private List<Container> containeres = new ArrayList<Container>();

	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getType() {
		if(!StringUtils.isEmpty(type)){
			this.type = type.split(",")[0];
		}
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getContainerDesc() {
		return containerDesc;
	}

	public void setContainerDesc(String containerDesc) {
		this.containerDesc = containerDesc;
	}
	
	public List<ContainerTypeAlias> getContainerTypeAlias() {
		return containerTypeAlias;
	}

	public void setContainerTypeAlias(List<ContainerTypeAlias> containerTypeAlias) {
		this.containerTypeAlias = containerTypeAlias;
	}
	

	public List<Container> getContaineres() {
		return containeres;
	}

	public void setContaineres(List<Container> containeres) {
		this.containeres = containeres;
	}
	
	public boolean isEmpty() {
		if (StringUtils.isEmpty(this.type))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((containerDesc == null) ? 0 : containerDesc.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContainerType other = (ContainerType) obj;
		if (containerDesc == null) {
			if (other.containerDesc != null)
				return false;
		} else if (!containerDesc.equals(other.containerDesc))
			return false;
		if (id != other.id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
