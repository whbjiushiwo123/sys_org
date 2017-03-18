package com.whb.sys.org.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.whb.sys.org.basic.dto.TreeDto;
import com.whb.sys.org.basic.model.Pager;
import com.whb.sys.org.dao.IOrgDao;
import com.whb.sys.org.dao.IOrgTypeDao;
import com.whb.sys.org.dao.IOrgTypeRuleDao;
import com.whb.sys.org.model.Org;
import com.whb.sys.org.model.SysException;
import com.whb.sys.org.service.AbstractBasicService;
import com.whb.sys.org.service.IOrgService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by WHB on 2016/7/9.
 */
@Service("orgService")
public class OrgServiceImpl extends AbstractBasicService implements IOrgService {
	private static Logger logger=  LoggerFactory.getLogger(OrgServiceImpl.class);

    @Inject
    IOrgDao iOrgDao;

    @Inject
    IOrgTypeDao iOrgTypeDao;

    @Inject
    IOrgTypeRuleDao iOrgTypeRuleDao;

    /**
     * 判断当前的组织机构数量够不够
     * @param cOrg 子组织机构
     * @param pOrg 父组织机构
     */
    private void checkChildOrgNum(Org cOrg,Org pOrg){
        if(pOrg == null) return;
        //已经存在的组织机构ID
        int rnum = iOrgTypeRuleDao.loadOrgRuleNum(pOrg.getTypeId(),cOrg.getTypeId());
        if(rnum < 0) return;
        int hnum = iOrgDao.loadNumByType(pOrg.getId(),cOrg.getTypeId());
        if(hnum > rnum){
            throw new SysException(pOrg.getName()+"下的"+cOrg.getName()+"的数量已经达到最大值");
        }
    }

    /**
     * parent 父组织已经存在的添加
     * @param org
     */
    public void add(Org org) {
        checkChildOrgNum(org,org.getParent());
        if(org.getParent() == null){
            org.setOrderNum(iOrgDao.getMaxOrder(null));
        }else{
            org.setOrderNum(iOrgDao.getMaxOrder(org.getParent().getId()));
        }
        iOrgDao.add(org);
    }

    public void add(Org org,Integer pid) {
        if(pid != null){
            Org p = iOrgDao.get(pid);
            if(p == null) throw new SysException("要添加的父组织机构不存在");
            checkChildOrgNum(org,p);
            org.setParent(p);
        }
        org.setOrderNum(iOrgDao.getMaxOrder(pid));
        iOrgDao.add(org);
    }
    
    /**
     * 检查父组织机构是否存在
     * @param pid 父组织机构ID
     * @return
     */
    private boolean checkParentOrg(int pid){
    	if(iOrgDao.get(pid) != null) return true;
    	return false;
    }

    /**
     * 更新组织机构
     */
    public void update(Org org,int id,int pid) throws Exception{
    	Org parent = new Org();
    	if(checkParentOrg(pid)){
    		parent = iOrgDao.load(pid);
    	}
		Org to = new Org();
		to.setId(id);
		to.setAddress(org.getAddress());
		to.setAtt1(org.getAtt1());
		to.setAtt2(org.getAtt2());
		to.setAtt3(org.getAtt3());
		to.setManagerType(org.getManagerType());
		to.setName(org.getName());
		to.setOrderNum(org.getOrderNum());
		to.setTypeId(org.getTypeId());
		to.setTypeName(org.getTypeName());
		to.setParent(parent);
    	try{
        	iOrgDao.update(org);
    	}catch (Exception e) {
			logger.error("更新组织结果出错,{},{}",e.getMessage(),e.getCause());
			throw e;
		}
    }

    public void delete(int id) throws SysException{
    	try{
			logger.error("删除组织结构,id:{}",id);
        	iOrgDao.delete(id);
    	}catch (SysException e) {
			logger.error("删除组织结构出错,id:{}",id);
			throw e;
		}
    }

    public Org load(int id) {
    	return (Org)iOrgDao.load(id);
    }
    public Org get(int id){
    	return iOrgDao.get(id);
    }

    public Pager<Org> findByParent(Integer pid,Integer typeId) {
    	return iOrgDao.findByParent(pid,typeId);
    }

    public List<TreeDto> tree(Integer tid) {
        return null;
    }

    public int loadNumByType(Integer pid, int typeId) {
        return 0;
    }

    public void addRule(int orgId, int cid) {
        iOrgDao.addRule(orgId,cid);
    }

    public void addRule(int orgId, Integer ids) {

    }

    public void deleteRule(int orgId, int cid) {
        iOrgDao.deleteRule(orgId, cid);
    }

    public List<Integer> listChildIdsByOrg(int id) {
        return iOrgDao.listChildIdsByOrg(id);
    }

    public List<Integer> listAllChildIdsByOrg(int id) {
        return iOrgDao.listAllChildIdsByOrg(id);
    }

    public List<TreeDto> listChildTreeByOrg(int id) {
        return iOrgDao.listChildTreeByOrg(id);
    }

    public List<Org> listChildByOrg(int id) {
        return iOrgDao.listChildByOrg(id);
    }

    public List<Org> listAllChildByOrg(int id) {
        return iOrgDao.listAllChildByOrg(id);
    }
    public List<TreeDto> listAllChildTreeByOrg(int id){
        return iOrgDao.listAllChildTreeByOrg(id);
    }

	@Override
	public List<TreeDto> tree() {
		return iOrgDao.tree();
	}
	@Override
	public List<Integer> listManagerRules(int id){
		return iOrgDao.listManagerRules(id);
		
	}
}
