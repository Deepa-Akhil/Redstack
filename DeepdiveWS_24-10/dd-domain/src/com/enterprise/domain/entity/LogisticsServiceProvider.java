package com.enterprise.domain.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "LSP", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"CLIENT_CD"})
)
@org.hibernate.annotations.Table(
	appliesTo = "LSP", 
	indexes = { 
		@Index(name = "lsp_id_ix", columnNames = { "ID" } )
	}
)
public class LogisticsServiceProvider implements IEntity, Serializable {

	private static final long serialVersionUID = -7478284062931632599L;

	/** ID decimal(19) PRIMARY KEY NOT NULL, */
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "LSP_SEQ")
	@SequenceGenerator(name = "LSP_SEQ", sequenceName = "GLOBAL.LSP_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	/** CLIENT_CD varchar2(3) NOT NULL, */
	@Column(name = "CLIENT_CD", nullable = false, length = 3)
	private String clientCd;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientCd() {
		return clientCd;
	}

	public void setClientCd(String clientCd) {
		if (StringUtils.isEmpty(clientCd)
				|| clientCd.length() > 3)
			throw new RuntimeException("Value [" + clientCd + "] as the logistics service provider client code is invalid. " + 
					"The system expects a valid client code no longer than 3 characters.");
		this.clientCd = clientCd;
	}

	public boolean isEmpty() {
		return false;
	}
}