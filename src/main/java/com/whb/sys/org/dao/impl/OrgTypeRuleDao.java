package com.whb.sys.org.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.whb.sys.org.basic.dao.BaseDao;
import com.whb.sys.org.dao.IOrgTypeRuleDao;
import com.whb.sys.org.model.OrgTypeRule;

@Repository("orgTypeRuleDao")
public class OrgTypeRuleDao  extends BaseDao<OrgTypeRule> implements IOrgTypeRuleDao{
	public void addOrgTypeRule(int pid, int cid, int num) {
        OrgTypeRule otr = new OrgTypeRule();
        otr.setPid(pid);
        otr.setCid(cid);
        otr.setNum(num);
        super.addEntity(otr);
    }

    public void deleteOrgTypeRule(int pid, int cid) {
        String hql = "delete OrgTypeRule otr where otr.pid=? and otr.cid=?";
        super.updateByHql(hql,pid,cid);
    }

    public void updateOrgTypeRule(int pid, int cid, int num) {
        String hql = "update OrgTypeRule otr set num=? where otr.pid=? and otr.cid=?";
        super.updateByHql(hql,num,pid,cid);
    }

    public List<OrgTypeRule> listByRule(int pid) {
        String hql = "select otr from OrgTypeRule otr where otr.pid=?";
        return super.list(hql,pid);
    }

    public int loadOrgRuleNum(int pid, int cid) {
        String hql = "select otr.num from OrgTypeRule otr where  otr.pid=? and otr.cid=?  ";
        System.out.println(pid+","+cid);
        return (Integer) super.queryObject(hql,pid,cid);
    }
    
    
}
