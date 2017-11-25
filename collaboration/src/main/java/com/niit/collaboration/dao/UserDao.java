package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.User;

public interface UserDao {
	public List<User>getAllUsers();
	public User saveUser(User user);
	public boolean deleteUser(User user);
	public User updateUser(User user);
	public User getUserByUserId(String userId);
	public User authenticateUser(User user);
	public List<User> getAllActiveUser();
	
	
	

}
