package com.whb.sys.org.service.impl;

import org.springframework.stereotype.Service;

import com.whb.sys.org.dao.IOrgTypeDao;
import com.whb.sys.org.dao.impl.OrgDao;
import com.whb.sys.org.dto.OrgTypeDto;
import com.whb.sys.org.model.OrgType;
import com.whb.sys.org.model.SysException;
import com.whb.sys.org.service.AbstractBasicService;
import com.whb.sys.org.service.IOrgTypeService;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by WHB on 2016/7/3.
 */
@Service("orgTypeService")
public class OrgTypeServiceImpl extends AbstractBasicService implements IOrgTypeService{
    @Inject
    private IOrgTypeDao orgTypeDao;   
    @Inject
    private OrgDao orgDao;
    public void add(OrgType orgType){
        if(orgTypeDao.loadBySn(orgType.getSn()) != null){
            throw new SysException("要添加的组织机构的sn已经存在");
        }
        orgTypeDao.add(orgType);
    }

    public void update(OrgType orgType){
        if(orgTypeDao.loadBySn(orgType.getSn()) != null){
            throw new SysException("要修改的组织机构的sn已经存在");
        }
        orgTypeDao.update(orgType);
    }
    public void delete (int id)throws SysException{
    	int c = orgDao.getOrgTypeNumsByType(id);
    	if(c>0) throw new SysException("要删除的组织机构下面有子组织");
        orgTypeDao.delete(id);
    }

    public OrgType load(String sn){
        return orgTypeDao.loadBySn(sn);
    }
    
    public OrgType loadBySn(String sn) {
        return orgTypeDao.loadBySn(sn);
    }
    
    public List<OrgType> list(){
    	return orgTypeDao.list();
    }

	@Override
	public int getOrgTypeNumsByType(int typeId) {
		// TODO Auto-generated method stub
		return orgDao.getOrgTypeNumsByType(typeId);
	}

	@Override
	public OrgType getById(int id) {
		// TODO Auto-generated method stub
		return orgTypeDao.getById(id);
	}
	
	@Override
	public OrgType loadById(int id) {
		// TODO Auto-generated method stub
		return orgTypeDao.loadById(id);
	}
	
	@Override
	public List<OrgTypeDto> listChileType(int pid){
		return orgTypeDao.listChildType(pid);
	}
	
}
