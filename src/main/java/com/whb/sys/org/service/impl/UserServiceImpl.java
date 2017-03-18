package com.whb.sys.org.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.whb.sys.org.basic.model.Pager;
import com.whb.sys.org.dao.IUserDao;
import com.whb.sys.org.model.SysException;
import com.whb.sys.org.model.User;
import com.whb.sys.org.service.AbstractBasicService;
import com.whb.sys.org.service.IUserService;

@Service("userService")
public class UserServiceImpl extends AbstractBasicService  implements IUserService{

	@Inject
	private IUserDao userDao;
	@Override
	public void add(User user) {
		if(userDao.findByUserId(user.getUserId(),user.getPassword())!=null) throw new SysException("添加的用户已经存在");
		userDao.add(user);
		
	}

	@Override
	public void delete(User user) {
		userDao.delete(user.getId());
		
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public Pager<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findByUserId(String userId,String password) {
		return userDao.findByUserId(userId,password);
	}

}
