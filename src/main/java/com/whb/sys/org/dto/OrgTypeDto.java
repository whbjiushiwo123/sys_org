package com.whb.sys.org.dto;

/**
 * 组织机构类型的DTO对象，通过该对象获取
 * @author WHB
 *
 */
public class OrgTypeDto {

	private int cid;
	private String cname;
	private int num;

	public String getCname() {
		return cname;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String orgTypeDao() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "OrgTypeDto [cid=" + cid + ", cname=" + cname + ", num=" + num
				+ "]";
	}
	public OrgTypeDto( int cid, String cname, int num) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.num = num;
	}
	public OrgTypeDto() {

	}
}
