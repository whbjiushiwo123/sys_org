package com.whb.sys.org.service.impl;

import org.springframework.stereotype.Service;

import com.whb.sys.org.basic.dto.TreeDto;
import com.whb.sys.org.basic.model.Pager;
import com.whb.sys.org.dao.IPersonDao;
import com.whb.sys.org.dto.PersonDto;
import com.whb.sys.org.model.Org;
import com.whb.sys.org.model.PersionOrgPosition;
import com.whb.sys.org.model.Person;
import com.whb.sys.org.service.AbstractBasicService;
import com.whb.sys.org.service.IPersonService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by WHB on 2016/7/9.
 */
@Service("personService")
public class PersonServiceImpl extends AbstractBasicService implements IPersonService {
    @Inject
    private IPersonDao iPersonDao;
    public void add(Person person) {
        iPersonDao.add(person);
    }

    public void update(Person person) {
        iPersonDao.update(person);
    }

    public void delete(int id) {
        iPersonDao.delete(id);
    }

    public void load(int id) {
        iPersonDao.load(id);
    }

    public Pager<Person> findByOrg(int oid, Integer posId) {
        return iPersonDao.findByOrg(oid,posId);
    }

    public List<Integer> listAlloOrgIdByPerson(int pid) {
        return iPersonDao.listAlloOrgIdByPerson(pid);
    }

    public List<Org> listAllOrgByPersonId(int pid) {
        return iPersonDao.listAllOrgByPersonId(pid);
    }

    public List<TreeDto> listOrgTreeByTypeParent(int pid, String type) {
        return null;
    }

    public List<TreeDto> listOrgTreeByTypet(int pid, String type) {
        return null;
    }
    public void addPersonOrgPos(PersionOrgPosition pop){
        iPersonDao.addPersonOrgPos(pop);
    }

	@Override
	public List<PersonDto> listPersonAndPosByOrg(int oid, Integer posId) {
		// TODO Auto-generated method stub
		return iPersonDao.listPersonAndPosByOrg(oid,posId);
	}
}
