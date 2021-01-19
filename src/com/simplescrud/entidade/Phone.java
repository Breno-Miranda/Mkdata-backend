package com.simplescrud.entidade;

import java.io.Serializable;

public class Phone implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String  userId;
	private String  areacode;
	private String  phone;
	private String  created_at;
	private Boolean ismain;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public Boolean getIsmain() {
		return ismain;
	}
	public void setIsmain(Boolean ismain) {
		this.ismain = ismain;
	}
}
