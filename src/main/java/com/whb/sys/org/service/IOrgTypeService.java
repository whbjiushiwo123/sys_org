package com.whb.sys.org.service;

import java.util.List;

import com.whb.sys.org.dto.OrgTypeDto;
import com.whb.sys.org.model.OrgType;

/**
 * Created by WHB on 2016/7/3.
 */
public interface IOrgTypeService {
    public void add(OrgType orgType);
    public void update(OrgType orgType);
    public void delete (int id);
    public OrgType load(String sn);

    public OrgType loadBySn(String sn);
    public List<OrgType> list();
    /**
     * 根据类型ID获取组织结构数目
     * @param typeId
     * @return
     */
    public int getOrgTypeNumsByType(int typeId);
    
    public OrgType getById(int id);
    public OrgType loadById(int id);
    
    public List<OrgTypeDto> listChileType(int pid);
}
