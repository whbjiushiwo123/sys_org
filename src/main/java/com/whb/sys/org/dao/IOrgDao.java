package com.whb.sys.org.dao;

import java.util.List;

import com.whb.sys.org.basic.dao.IBaseDao;
import com.whb.sys.org.basic.dto.TreeDto;
import com.whb.sys.org.basic.model.Pager;
import com.whb.sys.org.model.Org;

/**
 * Created by WHB on 2016/7/2.
 */
public interface IOrgDao extends IBaseDao<Org> {

	public Pager<Org> findByParent(Integer pid,Integer typyId);

	/**
	 * 根据组织类型来生成这棵树，如果tid为null，就获取所有的组织
	 * 
	 * @return
	 */
	public List<TreeDto> tree();

	public int loadNumByType(Integer pid, int typeId);

	/**
	 * 添加某个组织机构可以管理的子组织 这个子组织就是所有可以管理的所有子节点id
	 * 
	 * @param orgId
	 *            组织id
	 * @param id
	 *            可管理的组织id
	 */
	public void addRule(int orgId, int id);

	/**
	 * 添加某个组织机构可以管理的一组子组织 这个子组织就是所有可以管理的所有子节点id
	 * 
	 * @param orgId
	 *            组织id
	 * @param ids
	 *            可管理的一组子组织ids
	 */
	public void addRule(int orgId, Integer ids);

	/**
	 * 删除子组织
	 * 
	 * @param orgId
	 *            父组织ID
	 * @param cid
	 *            子组织
	 */
	public void deleteRule(int orgId, int cid);

	/**
	 * 获取某个组织下面的子组织ID 需要进行判断，如果组织类型是NON_TYPE--->return null
	 * 如果组织类型是ALL_TYPE--->返回所有组织 如果组织类型是DEF_TYPE--->获取所有子组织
	 * 
	 * @param id
	 *            父组织
	 * @return 某个组织下面的所有子组织id
	 */
	public List<Integer> listChildIdsByOrg(int id);

	/**
	 * 获得所有组织机构下面所有子节点
	 * 
	 * @param id
	 *            子节点组织id
	 * @return 所有子节点
	 */
	public List<Integer> listAllChildIdsByOrg(int id);

	/**
	 * 获取某个子组织下面的节点树ID
	 * 
	 * @param id
	 *            子组织ID
	 * @return 所有节点树ID
	 */
	public List<TreeDto> listChildTreeByOrg(int id);

	/**
	 * 获取某个子组织下面的所有子组织，也要根据类型要判断
	 * 
	 * @param id
	 * @return
	 */
	public List<TreeDto> listAllChildTreeByOrg(int id);

	public int getMaxOrder(Integer pid);

	public List<Org> listAllChildByOrg(int id);

	public List<Org> listChildByOrg(int id);

	public List<TreeDto> listChildTreeByOrgs(List<Integer> ids);

	public List<TreeDto> listAllChildTreeByOrgs(List<Integer> ids);
	public int getOrgTypeNumsByType(int typeId);

	public List<Integer> listManagerRules(int id);

}
