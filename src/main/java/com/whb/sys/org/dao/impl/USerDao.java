package com.whb.sys.org.dao.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.whb.sys.org.basic.dao.BaseDao;
import com.whb.sys.org.basic.model.Pager;
import com.whb.sys.org.dao.IUserDao;
import com.whb.sys.org.model.User;

@Repository("userDao")
public class USerDao extends BaseDao<User> implements IUserDao {
    private final static Logger logger = LoggerFactory.getLogger(USerDao.class);

	@Override
	public Pager<User> findAll() {
        String hql = "select u form User u";
        return super.find(hql);
	}

	public User findByUserId(String userId,String password) {
		String hql = "select distinct u from User u where u.userId=? and u.password=?";
        return (User) super.queryObject(hql, userId,password);
	}
	

}
