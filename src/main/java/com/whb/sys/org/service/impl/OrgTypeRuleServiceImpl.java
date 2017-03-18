package com.whb.sys.org.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.whb.sys.org.basic.dto.InitOrgTypeRuleDto;
import com.whb.sys.org.dao.impl.OrgTypeDao;
import com.whb.sys.org.dao.impl.OrgTypeRuleDao;
import com.whb.sys.org.dto.OrgTypeDto;
import com.whb.sys.org.dto.OrgTypeRuleDto;
import com.whb.sys.org.model.OrgType;
import com.whb.sys.org.model.OrgTypeRule;
import com.whb.sys.org.service.AbstractBasicService;
import com.whb.sys.org.service.IOrgTypeRuleService;

@Service("orgTypeRuleService")
public class OrgTypeRuleServiceImpl extends AbstractBasicService implements IOrgTypeRuleService{
	private final static Logger logger =  LoggerFactory.getLogger(OrgTypeRuleServiceImpl.class);
	@Inject
	private OrgTypeRuleDao orgTypeRuleDao;
	
	@Inject
	private OrgTypeDao orgTypeDao;
	
	public void addOrgTypeRule(int pid,int cid,int num){
		orgTypeRuleDao.addOrgTypeRule(pid,cid,num);
    }
	public void addOrgTypeRule(Map<String, Object> body) throws Exception{
		try {
			int pid=Integer.parseInt(body.get("pid").toString());
			int cid=Integer.parseInt(body.get("cid").toString());
			int num=Integer.parseInt(body.get("num").toString());
			orgTypeRuleDao.addOrgTypeRule(pid,cid,num);
		} catch (Exception e) {
			throw e;
		}
    }
    public void addOrgTypeRule(InitOrgTypeRuleDto initOrgTypeRuleDto){
    	orgTypeRuleDao.addOrgTypeRule(orgTypeDao.loadBySn(initOrgTypeRuleDto.getPsn()).getId(),
                orgTypeDao.loadBySn(initOrgTypeRuleDto.getCsn()).getId(),initOrgTypeRuleDto.getNum());
    }
    public void updateOrgTypeRule(Map<String, Object> body){
		int cid = Integer.parseInt(body.get("cid").toString());
		int pid = Integer.parseInt(body.get("pid").toString());
		int num = Integer.parseInt(body.get("num").toString());
    	orgTypeRuleDao.updateOrgTypeRule(pid,cid,num);
    }
    public void deleteOrgTypeRule(int pid,int cid){
    	orgTypeRuleDao.deleteOrgTypeRule(pid,cid);
    }
    public List<OrgTypeRule> listByRule(int pid){
    	return orgTypeRuleDao.listByRule(pid);
    }
    public int loadOrgRuleNum(int pid,int cid){
    	return orgTypeRuleDao.loadOrgRuleNum(pid,cid);
    }

	@Override
	public List<OrgTypeRuleDto> listOrgTypeRuleByOrg(int id) {
		List<OrgTypeDto> otds = orgTypeDao.listChildType(id);
		List<OrgTypeRuleDto> otrds = new ArrayList<OrgTypeRuleDto>();
		List<Integer> aids = new ArrayList<Integer>();
		OrgTypeRuleDto otrd=null;
		//将存在的添加进来
		for(OrgTypeDto otd:otds){
			otrd = new OrgTypeRuleDto();
			otrd.setCid(otd.getCid());
			otrd.setCname(otd.getCname());
			otrd.setExists(true);
			otrd.setNum(otd.getNum());
			otrds.add(otrd);
			aids.add(otd.getCid());
		}
		List<OrgType> ots = orgTypeDao.list();
		for(OrgType ot:ots){
			if(ot.getId()==id)continue;
			if(aids.contains(ot.getId()))continue;
			otrd=new OrgTypeRuleDto();
			otrd.setCid(ot.getId());
			otrd.setCname(ot.getName());
			otrd.setExists(false);
			otrd.setNum(0);
			otrds.add(otrd);
		}
		return otrds;
	}
	
}
