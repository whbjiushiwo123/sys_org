package com.whb.sys.org.model;

public class Constant {
	//默认管理类型，表示直线型管理，即只能管理它下面所有的子组织
	public final static int DEFAULT_TYPE=0;
	//可以管理所有组织机构中的信息
	public final static int ALL_TYPE=1;
	//自定义的管理类型，如果是该管理类型，需要到managerOrg这个字段中获取可以管理的所有子组织
	public final static int DEF_TYPE=2;
	//不具备管理能力
	public final static int NO_TYPE=-1;
}
