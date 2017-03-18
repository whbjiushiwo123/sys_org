package com.whb.sys.org.service;

import java.util.List;
import java.util.Map;

import com.whb.sys.org.dto.OrgTypeRuleDto;
import com.whb.sys.org.model.OrgTypeRule;

public interface IOrgTypeRuleService {
	/**
     * 添加组织类型规则
     * @param pid 父组织id
     * @param cid 子组织id
     * @param num 组织之间的关系数量
     */
    public void addOrgTypeRule(int pid,int cid,int num);
    public void addOrgTypeRule(Map<String, Object> body) throws Exception;
    
    /**
     * 删除组织类型
     * @param pid 父组织id
     * @param cid 子组织id
     */
    public void deleteOrgTypeRule(int pid,int cid);

    /**
     * 更新组织类型规则，更新组织之间的关系数量
     * @param pid 父组织ID
     * @param cid 子组织ID
     * @param num 组织之间的关系数量
     */
    public void updateOrgTypeRule(Map<String, Object> map);

    /**
     * 根据父亲ID获取该组织的所有可以管理的子节点
     * @param pid 父id
     * @return 所有可以管理的子节点
     */
    public List<OrgTypeRule> listByRule(int pid);

    /**
     * 根据父id和子id获取规则两个自检的数量
     * @param pid 父组织类型id
     * @param cid 子组织类型id
     * @return 父 子组织之间的数量
     */
    public int loadOrgRuleNum(int pid,int cid);
    
    /**
     * 根据组织获得该组织所有可以添加的自组织机构
     * @param id
     * @return
     */
    public List<OrgTypeRuleDto> listOrgTypeRuleByOrg(int id); 
}
