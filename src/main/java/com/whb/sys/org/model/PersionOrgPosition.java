package com.whb.sys.org.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 人员组织岗位对应关系，这里存储人员和组织和岗位对应的关系
 * @author WHB
 *
 */
@Entity
@Table(name="t_person_org_position")
public class PersionOrgPosition {
	private int id;
	private int personId;
	private int orgId;
	private int posId;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="person_id")
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	@Column(name="org_id")
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	
	@Column(name="pos_id")
	public int getPosId() {
		return posId;
	}
	public void setPosId(int posId) {
		this.posId = posId;
	}
}
