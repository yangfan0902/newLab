package cn.yangfan.dao;

import pojo.User;

public interface UserDao {
	public User getUserByName(String username);
	public void addUser(User user);
	public void deleteUser(String username);
	public void updateUser(User user);
	
}
