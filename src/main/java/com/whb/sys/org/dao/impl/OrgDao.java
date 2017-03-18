package com.whb.sys.org.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.whb.sys.org.basic.dao.BaseDao;
import com.whb.sys.org.basic.dto.TreeDto;
import com.whb.sys.org.basic.model.Pager;
import com.whb.sys.org.basic.util.BasicSysKit;
import com.whb.sys.org.dao.IOrgDao;
import com.whb.sys.org.model.Constant;
import com.whb.sys.org.model.Org;
import com.whb.sys.org.model.OrgRule;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WHB on 2016/7/3.
 */
@Repository("orgDao")
public class OrgDao extends BaseDao<Org> implements IOrgDao {
    private static Logger logger = LoggerFactory.getLogger(OrgDao.class);
    public Pager<Org> findByParent(Integer pid,Integer  typeId) {

        String hql = "select org from Org org where 1=1 ";
        if(pid == null){
            hql += " and org.parent is null ";
        }else{
            hql += " and org.parent.id=" + pid;
        }
        if(typeId!=null&&typeId>0){
        	hql+= " and org.typeId="+typeId;
        }
        return super.find(hql);
    }

    public List<TreeDto> tree() {
        String hql = "select id,name,pid from t_org";
        List<TreeDto> tds = super.listBySql(hql,TreeDto.class,false);
        return tds;
    }

    public int loadNumByType(Integer pid, int  typeId) {
        String hql = "select count(*) from Org where typeId=?";
        if(pid == null){
            hql += "and parent is null";
        }else{
            hql += "and parent.id =" + pid;
        }
        return ((Long) super.queryObject(hql,typeId)).intValue();
    }

    public void addRule(int orgId, int cid) {
        logger.info("orgId:{},cid:{}",orgId,cid);
        OrgRule oru = this.loadManagerOrg(orgId);
        String managerOrg = null;
        if(BasicSysKit.isEmpty(oru)){
            managerOrg = "";
            managerOrg += "("+cid+")";
            oru = new OrgRule();
            oru.setOrgId(orgId);
            oru.setManagerOrg(managerOrg);
            super.addEntity(oru);
            return;
        }else{
            managerOrg = oru.getManagerOrg();
        }
        if(BasicSysKit.isEmpty(managerOrg)){
            managerOrg = "";
        }
        if(managerOrg.indexOf("("+cid+")")>0){
            return ;
        }
        managerOrg += "("+cid+")";
        oru.setManagerOrg(managerOrg);
        super.updateEntity(oru);
    }

    public void addRule(int orgId, Integer ids) {

}

    public void deleteRule(int orgId, int cid) {
        OrgRule oru = this.loadManagerOrg(orgId);
        String mangeerOrg = oru.getManagerOrg();
        mangeerOrg = mangeerOrg.replace("("+cid+")","");
        oru.setManagerOrg(mangeerOrg);
        super.updateEntity(oru);
    }

    /**
     * 获得某一个组织机构下面所有子节点
     * @param id 子节点组织id
     * @return 所有子节点
     */
    public List<Integer> listChildIdsByOrg(int id) {
        /**
         * 获取子节点通常使用递归，但效率低
         * 可以使用map来代替反复查询递归
         */
        //1、取出所有子节点
        List<Org> orgs = listAllOrg();
        //2、格式化为一个MAP
        Map<Integer,List<Org>> corgs = org2map(orgs);
        //3、通过map获取所有子节点数据
        List<Org> rorgs = new ArrayList<Org>();
        rorgs.add(this.load(id));
        getOrgByMap(corgs,id,rorgs);
        List<Integer> orgsIds = orgs2OrgIds(rorgs);
        return orgsIds;
    }

    /**
     * 取出所有子节点
     * @return
     */
    private List<Org> listAllOrg(){
        String hql = "SELECT org FROM Org org LEFT JOIN FETCH org.parent";
        List<Org> orgs = super.list(hql);
        return orgs;
    }

    /**
     * 获得所有组织机构下面所有子节点
     * @param id 子节点组织id
     * @return 所有子节点
     */
    public List<Integer> listAllChildIdsByOrg(int id){
        //首先判断这个节点类型，如果是直线型，就直接返回listChildByOrg
        Org org = super.load(id);
        List<Integer> ids = new ArrayList<Integer>();
        switch (org.getManagerType()){
            case  Constant.NO_TYPE:
                break;
            case Constant.DEFAULT_TYPE:
                ids = listChildIdsByOrg(id);
                break;
            case Constant.ALL_TYPE:
                ids = listAllOrgId();
                break;
            case Constant.DEF_TYPE:
                //自己所管理的部门的id
                List<Integer> manageIds = listManagerRuleIds(id);
                //获取这一组下面所有子节点
                List<Org> orgs = listChildOrgByIds(manageIds);
                ids = orgs2OrgIds(orgs);
                break;
        }
        return ids;
    }

    /**
     * orgs转换成
     */
    private List<Integer> orgs2OrgIds(List<Org> orgs){
        List<Integer> orgIds = new ArrayList<Integer>();
        for(Org o:orgs){
            orgIds.add(o.getId());
        }
        return orgIds;
    }
    /**
     * 获取一组id下的所有子节点
     * @param orgIds
     * @return
     */
    private List<Org> listChildOrgByIds(List<Integer> orgIds){
        //取出所有的org
        List<Org> orgs = listAllOrg();
        Map<Integer,List<Org>> corgs = org2map(orgs);
        List<Org> rorgs = new ArrayList<Org>();
        for(Integer id:orgIds){
            getOrgByMap(corgs,id,rorgs);
        }
        return rorgs;
    }

    private void getOrgByMap(Map<Integer,List<Org>> orgs,int id,List<Org> corgs){
        //如果不包含，则直接返回
        if(!orgs.containsKey(id)){return;}
        List<Org> torgs = orgs.get(id);
        for(Org o:torgs){
            corgs.add(o);
            //org的子节点中还是orgs的key，就说明该子节点还是父节点,则递归
            if(orgs.containsKey(o.getId())){
                getOrgByMap(orgs,o.getId(),corgs);
            };
        }
    }

    /**
     * 将list中的Org对象转换成map对象
     * 键是id，值为id下的所有子id
     * @param orgs Org对象
     * @return 转换成map对象
     */
    private Map<Integer,List<Org>> org2map(List<Org> orgs) {
        Map<Integer,List<Org>> maps = new HashMap<Integer, List<Org>>();
        List<Org> os = null;
        for(Org o:orgs){
            //如果为空，则表示没有父组织
            if(o.getParent() == null){
                os = new ArrayList<Org>();
                maps.put(o.getId(),os);
            }else{
                //如果包含了，添加到此父节点下面
                if(maps.containsKey(o.getParent().getId())){
                    maps.get(o.getParent().getId()).add(o);
                }else{
                    os = new ArrayList<Org>();
                    //如果没有包含则添加，所以这个要覆盖equals方法了,使用id来判断是否相等
                    if(!os.contains(o))
                    os.add(o);
                    maps.put(o.getParent().getId(),os);
                }
            }
        }
        return maps;
    }

    @SuppressWarnings("unchecked")
	private List<Integer> listAllOrgId(){
        String hql = "select org.id form Org org order by org.id";
        return (List<Integer>) super.listObj(hql);
    }
    /**
     * 获得某一个节点下的所有组织子节点树
     * @param orgId 子节点组织id
     * @return
     */
    public List<TreeDto> listChildTreeByOrg(int orgId) {
        List<Org> orgs = listChildByOrg(orgId);
        return orgs2Trees(orgs);
    }

    public List<TreeDto> listAllChildTreeByOrg(int id) {
        //首先判断这个节点类型，如果是直线型，就直接返回listChildByOrg
        Org org = super.load(id);
        List<TreeDto> tds = new ArrayList<TreeDto>();
        switch (org.getManagerType()){
            case  Constant.NO_TYPE:
                break;
            case Constant.DEFAULT_TYPE:
                tds = listChildTreeByOrg(id);
                break;
            case Constant.ALL_TYPE:
                tds = tree();
                break;
            case Constant.DEF_TYPE:
                //自己所管理的部门的id
                List<Integer> manageIds = listManagerRuleIds(id);
                tds = listChildTreeByOrgs(manageIds);
                break;
        }
        return tds;
    }

    private List<TreeDto> orgs2Trees(List<Org> orgs){
        List<TreeDto> tds = new ArrayList<TreeDto>();
        TreeDto td = null;
        for(Org org:orgs){
            if(!BasicSysKit.isEmpty(org.getParent())){
                td = new TreeDto(org.getId(),org.getName(),org.getParent().getId());
            }else{
                td = new TreeDto(org.getId(),org.getName(),-1);
            }
            tds.add(td);
        }
        return tds;
    } 

    private OrgRule loadManagerOrg(Integer orgId){
        //1,2,3,4
        String hql = "select oru from com.whb.sys.org.model.OrgRule oru where oru.orgId=:orgId";
        //(22)33)
        OrgRule oru = (OrgRule)super.queryObject(hql,orgId);
        return oru;
    }
    private List<Integer> listManagerRuleIds(int orgId){
        String managerOrg = loadManagerOrg(orgId).getManagerOrg();
        List<Integer> ids = BasicSysKit.braceStr2List(managerOrg);
        return ids;
    }

    public int getMaxOrder(Integer pid){
        return super.getMaxOrder(pid,"Org");
    }

    public List<Org> listAllChildByOrg(int id) {
        //1、取出所有子节点
        List<Org> orgs = listAllOrg();
        //2、格式化为一个MAP
        Map<Integer,List<Org>> corgs = org2map(orgs);
        //3、通过map获取所有子节点数据
        List<Org> rorgs = new ArrayList<Org>();
        return rorgs;
    }

    public List<Org> listChildByOrg(int id) {
        /**
         * 获取子节点通常使用递归，但效率低
         * 可以使用map来代替反复查询递归
         */
        //1、取出所有子节点
        List<Org> orgs = listAllOrg();
        //2、格式化为一个MAP
        Map<Integer,List<Org>> corgs = org2map(orgs);
        //3、通过map获取所有子节点数据
        List<Org> rorgs = new ArrayList<Org>();
        rorgs.add(this.load(id));
        getOrgByMap(corgs,id,rorgs);
        return rorgs;
    }

    /**
     * 根据一组id获取树对象
     * @param ids
     * @return
     */
    public List<TreeDto> listChildTreeByOrgs(List<Integer> ids){
        List<Org> orgs = this.listChildOrgByIds(ids);
        return orgs2Trees(orgs);
    }
    public List<TreeDto> listAllChildTreeByOrgs(List<Integer> ids){
        return null;
    }
    
	public int getOrgTypeNumsByType(int typeId) {
		String hql = "select count(*) from Org o where o.typeId=? ";
		Long l = (Long) super.queryObject(hql, typeId);
		return l.intValue();
	}

	@Override
	public List<Integer> listManagerRules(int id) {
		String managerOrg = loadManagerOrg(id).getManagerOrg();
		List<Integer> ids = BasicSysKit.braceStr2List(managerOrg);
		return ids;
	}

}
