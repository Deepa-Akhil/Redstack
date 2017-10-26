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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.annotations.Index;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.CollectionUtils;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "CONTAINER", schema = "GLOBAL"/*, uniqueConstraints =
    @UniqueConstraint(columnNames = {"CONTAINER_NUMBER", "TYPE"})*/
)
@org.hibernate.annotations.Table(
	appliesTo = "CONTAINER", 
	indexes = { 
		@Index(name = "container_id_ix", columnNames = { "ID" })
		/*@Index(name = "container_nr_ix", columnNames = { "CONTAINER_NUMBER", "TYPE" } ),
		 * @Index(name = "container_type_ix", columnNames = { "TYPE" } ),
		@Index(name = "container_col1_ix", columnNames = { "CONTAINER_NUMBER", "TYPE" } )*/
	}
)
public class Container implements IEntity, Serializable {

	private static final long serialVersionUID = -3951936257177815318L;

	/** ID decimal(19) PRIMARY KEY NOT NULL, */
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CONTAINER_SEQ")
	@SequenceGenerator(name = "CONTAINER_SEQ", sequenceName = "GLOBAL.CONTAINER_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name = "CONTAINER_NUMBER", nullable = false)
	private String containerNumber;
	
	/*@Column(name="TYPE", nullable = false, length = 4)
	private String type;*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CONTAINER_TYPE_ID", referencedColumnName = "ID", nullable = true)
	})
	private ContainerType containerType;
	
	@OneToMany(mappedBy = "container", fetch = FetchType.LAZY)
	private List<ShipmentContainer> shipmentContainers = new ArrayList<ShipmentContainer>();
	
	@OneToMany(mappedBy = "container", fetch = FetchType.LAZY)
	private List<OrderExpedLine> orderExpedLines = new ArrayList<OrderExpedLine>();
	
	@Column(name="CONTAINER_DESC", nullable = true)
	private String containerDesc;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContainerNumber() {
		return containerNumber;
	}

	public void setContainerNumber(String containerNumber) {
		/*if (!StringUtils.isEmpty(containerNumber)){
			String containerNumberArr[] =  containerNumber.split(",");
			for (String string : containerNumberArr) {
				if(string.trim().length()!=11){
					throw new RuntimeException("Value [" + containerNumber + "] as the container number is invalid. " + 
							"The system expects a valid number that contains 11 characters(4Alpha+7Numeric).");
				}
			}
		}*/
		this.containerNumber = containerNumber;
	}
	
	/*public ContainerTypes getEnumType() {
		return ContainerTypes.valueOfByTypeCode(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (!StringUtils.isEmpty(type))
			this.type = type;
	}*/

	public List<ShipmentContainer> getShipmentContainers() {
		return shipmentContainers;
	}

	public void setShipmentContainers(List<ShipmentContainer> shipmentContainers) {
		this.shipmentContainers = shipmentContainers;
	}
	
	public boolean isEmpty() {
		if (StringUtils.isEmpty(containerNumber)
				&& CollectionUtils.isEmpty(this.shipmentContainers))
			return true;
		return false;
	}
	
	public String getContainerDesc() {
		return containerDesc;
	}

	public void setContainerDesc(String containerDesc) {
		this.containerDesc = containerDesc;
	}

	public List<OrderExpedLine> getOrderExpedLines() {
		return orderExpedLines;
	}

	public void setOrderExpedLines(List<OrderExpedLine> orderExpedLines) {
		this.orderExpedLines = orderExpedLines;
	}

	public ContainerType getContainerType() {
		return containerType;
	}

	public void setContainerType(ContainerType containerType) {
		this.containerType = containerType;
	}
	
	@SuppressWarnings("deprecation")
	public static String getContainerType(Session session, long id) {
		Criteria criteria = session.createCriteria(Container.class)
			.add(Restrictions.eq("id", id));
		criteria.setFetchMode("containerType", FetchMode.EAGER); 
		Container container = (Container)criteria.uniqueResult();
		if(container != null && container.getContainerType()!=null) {
			return container.getContainerType().getType();
		}
		return null;
	}
}