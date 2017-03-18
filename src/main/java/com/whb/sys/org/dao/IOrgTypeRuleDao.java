package com.whb.sys.org.dao;

import java.util.List;

import com.whb.sys.org.basic.dao.IBaseDao;
import com.whb.sys.org.model.OrgTypeRule;

public interface IOrgTypeRuleDao  extends IBaseDao<OrgTypeRule>{
	  public List<OrgTypeRule> listByRule(int pid) ;
	  public int loadOrgRuleNum(int pid,int cid);
	  
}
