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
import javax.persistence.Transient;

import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;

@Entity
@Table(name = "MENU_FUNCTION", schema = "GLOBAL")
@org.hibernate.annotations.Table(
	appliesTo = "MENU_FUNCTION", 
	indexes = { 
		@Index(name = "menufunction_id_ix", columnNames = { "ID" } )
	}
)
public class MenuFunction implements IEntity, Serializable  {

	private static final long serialVersionUID = 3510735197070554499L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MENU_FUNCTION_SEQ")
	@SequenceGenerator(name = "MENU_FUNCTION_SEQ", sequenceName = "GLOBAL.MENU_FUNCTION_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name="MENU_LEVEL", columnDefinition = "TINYINT", length=4)
    private int  menuLevel= 1;
	
	@Column(name = "MENU_NAME")
	private String menuName;
	
	@Column(name = "MENU_URL")
	private String menuURL;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = true, updatable=false)
	})
	private User user; 
	
	@Transient
	private String tokenID;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public int getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuURL() {
		return menuURL;
	}

	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}

	public boolean isEmpty() {
		return false;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setTokenID(String tokenID) {
		this.tokenID = tokenID;
	}

	public String getTokenID() {
		return tokenID;
	}
}
