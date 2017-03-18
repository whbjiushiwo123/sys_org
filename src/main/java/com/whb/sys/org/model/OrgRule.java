package com.whb.sys.org.model;

import java.io.Serializable;

import javax.persistence.Column;

public class OrgRule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2786242045975580504L;
	private int id;
	//能够管理的组织，用“,”隔开
	private String managerOrg;
	private int orgId;
	
	@Column(name="org_id")
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManagerOrg() {
		return managerOrg;
	}
	public void setManagerOrg(String managerOrg) {
		this.managerOrg = managerOrg;
	}
}
