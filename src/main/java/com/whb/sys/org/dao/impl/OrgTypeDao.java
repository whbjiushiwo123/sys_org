package com.whb.sys.org.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.whb.sys.org.basic.dao.BaseDao;
import com.whb.sys.org.dao.IOrgTypeDao;
import com.whb.sys.org.dto.OrgTypeDto;
import com.whb.sys.org.model.OrgType;

import java.util.List;

/**
 * Created by WHB on 2016/7/2.
 */
@Repository("orgTypeDao")
public class OrgTypeDao extends BaseDao<OrgType> implements IOrgTypeDao {
	private static final Logger logger = LoggerFactory.getLogger(OrgTypeDao.class);
    public List<OrgType> list() {
        String hql = "select ot from OrgType ot";
        return super.list(hql);
    }

    

    public OrgType loadBySn(String sn) {
        return (OrgType)super.loadBySn(sn, OrgType.class.getName());
    }
    
    public OrgType getById(int id) {  
    	return (OrgType)super.get(id);
    }
    public OrgType loadById(int id) {  
    	return (OrgType)super.load(id);
    }



	@SuppressWarnings("unchecked")
	@Override
	public List<OrgTypeDto> listChildType(int pid) {
		String hql = "select new com.whb.sys.org.dto.OrgTypeDto(ot2.id,ot2.name,otr.num) from OrgType ot,OrgTypeRule otr ,OrgType ot2 ";
		hql+=" where ot.id=? and ot.id=otr.pid and ot2.id=otr.cid";
		return (List<OrgTypeDto>) this.listObj(hql, pid);
	}
    
}
