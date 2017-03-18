package com.whb.sys.org.dao.impl;

import org.springframework.stereotype.Repository;

import com.whb.sys.org.basic.dao.BaseDao;
import com.whb.sys.org.basic.dto.TreeDto;
import com.whb.sys.org.basic.model.Pager;
import com.whb.sys.org.dao.IPersonDao;
import com.whb.sys.org.dto.PersonDto;
import com.whb.sys.org.model.Org;
import com.whb.sys.org.model.PersionOrgPosition;
import com.whb.sys.org.model.Person;

import java.util.List;

/**
 * Created by WHB on 2016/7/3.
 */
@Repository("personDao")
public class PersonDao extends BaseDao<Person> implements IPersonDao {
    public Pager<Person> findByOrg(int oid, Integer posId) {
        String hql = "select p form Person p,PersonOrgPos pop where p.id=pop.personId and pop.orgId=?";
        if(posId!=null){
            hql += " and pop.posId = "+posId;
        }
        return super.find(hql,oid);
    }

    public List<Integer> listAlloOrgIdByPerson(int pid) {
        return null;
    }

    public List<Org> listAllOrgByPersonId(int id) {
        return null;
    }

    public List<TreeDto> listOrgTreeByTypeParent(int pid, String type) {
        return null;
    }

    public List<TreeDto> listOrgTreeByTypet(int pid, String type) {
        return null;
    }
    public void addPersonOrgPos(PersionOrgPosition pop){
        super.addEntity(pop);
    }
    
    @SuppressWarnings("unchecked")
	public List<PersonDto> listPersonAndPosByOrg(int oid,Integer posId){
		String hql = "select new com.whb.sys.org.dto.PersonDto(p.id,p.name,p.sfzh,p.sex,p.phone,pos.name,pos.id) " +
				"from Person p,PersionOrgPosition pop,Position pos where p.id=pop.personId and pos.id=pop.posId and pop.orgId=? ";
		if(posId!=null) {
			hql+=" and pop.posId="+posId;
		}
		return (List<PersonDto>) super.listObj(hql, oid);
    }


}
