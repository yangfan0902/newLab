package cn.yangfan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yangfan.dao.UserDao;
import pojo.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void addUser(User user){
		userDao.addUser(user);
	}
	
	public void deleteUser(String username){
		userDao.deleteUser(username);
	}
	
	public void updateUser(User user){
		userDao.updateUser(user);
	}
	
	public User getUserByName(String username){
		return userDao.getUserByName(username);
	}
	
	
}
