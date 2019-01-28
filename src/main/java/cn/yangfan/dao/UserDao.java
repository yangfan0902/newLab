package cn.yangfan.dao;

import java.util.List;

import pojo.User;

public interface UserDao {
	public User getUserByName(String username);
	public void addUser(User user);
	public void deleteUser(String username);
	public void updateUser(User user);
	public List getUsers();
	
}
