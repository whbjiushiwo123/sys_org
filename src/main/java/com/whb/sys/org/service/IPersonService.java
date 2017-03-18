package com.whb.sys.org.service;

import com.whb.sys.org.basic.dto.TreeDto;
import com.whb.sys.org.basic.model.Pager;
import com.whb.sys.org.dto.PersonDto;
import com.whb.sys.org.model.Org;
import com.whb.sys.org.model.PersionOrgPosition;
import com.whb.sys.org.model.Person;

import java.util.List;

/**
 * Created by WHB on 2016/7/9.
 */
public interface IPersonService {
    public void add(Person person);
    public void update(Person person);
    public void delete(int id);
    public void load(int id);
    /**
     *
     * 根据组织,和岗位ID类获取人员
     * 组织ID不为空
     * 岗位id不存在，则获取这个组织的所有人员
     * @param oid 组织ID
     * @param posId 岗位id
     * @return 同一个组织下面的所有人员
     */
    public Pager<Person> findByOrg(int oid, Integer posId);

    /**
     * 获取某个人员可以管理的所有组织id
     * @param pid 人员id
     * @return 某个人员可以管理的所有组织id
     */
    public List<Integer> listAlloOrgIdByPerson(int pid);

    /**
     * 根据人员获取可以管理的所有组织
     * @param id 人员id
     * @return 可管理的所有组织
     */
    public List<Org> listAllOrgByPersonId(int id);

    /**
     * 获取某个人员的所有管理的组织树，组织树中仅仅显示某个类型的父规则类型
     * @param pid 人员ID
     * @param type 父组织类型
     * @return 某个人员的所有管理的组织树
     */
    public List<TreeDto> listOrgTreeByTypeParent(int pid, String type);

    /**
     * 根据用户获取组织树，组织树中显示某个组织类型中的所有组织
     * @param pid 用户id
     * @param type 组织类型
     * @return 某个组织类型中的所有组织
     */
    public List<TreeDto> listOrgTreeByTypet(int pid,String type);

    public void addPersonOrgPos(PersionOrgPosition pop);
    
    public List<PersonDto> listPersonAndPosByOrg(int oid,Integer posId);
}
