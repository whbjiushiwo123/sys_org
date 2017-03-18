package com.whb.sys.org.dao;

import java.util.List;

import com.whb.sys.org.basic.dao.IBaseDao;
import com.whb.sys.org.basic.model.Pager;
import com.whb.sys.org.model.User;

public interface IUserDao extends IBaseDao<User>{
	public Pager<User> findAll();
	public User findByUserId(String userId,String password);
}
