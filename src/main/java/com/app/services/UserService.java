package com.app.services;

import java.util.List;

import com.app.dao.UserDao;
import com.app.models.User;

public class UserService {
	private final UserDao userDao = new UserDao();
	
	public List<User> listUser() {
		return userDao.listUser();
	}

	public void create(User user) {
		userDao.insertUser(user);
	}
	
	public void update(User user) {
		userDao.update(user);
	}
	
	public void delete(String id) {
		userDao.delete(id);
	}
	

	public User find(String id) {
		return userDao.detail(id);
	}
	
}
