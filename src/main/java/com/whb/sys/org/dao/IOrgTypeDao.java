package com.whb.sys.org.dao;



import java.util.List;

import com.whb.sys.org.basic.dao.IBaseDao;
import com.whb.sys.org.dto.OrgTypeDto;
import com.whb.sys.org.model.OrgType;

/**
 * Created by WHB on 2016/7/2.
 */
public interface IOrgTypeDao extends IBaseDao<OrgType> {
	public List<OrgType> list();

    public OrgType loadBySn(String sn);
    
    public OrgType getById(int id) ;
    public OrgType loadById(int id) ;

	public List<OrgTypeDto> listChildType(int pid);
    
}

