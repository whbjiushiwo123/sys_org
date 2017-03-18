package com.whb.sys.org.service;

import com.whb.sys.org.basic.model.Pager;
import com.whb.sys.org.model.User;

public interface IUserService {
	public void add(User user);
	public void delete(User user);
	public void update(User user);
	public Pager<User> findAll();
	public User findByUserId(String userId,String password);
}
