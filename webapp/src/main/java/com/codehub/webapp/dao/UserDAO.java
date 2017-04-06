package com.codehub.webapp.dao;

import java.util.List;

import com.codehub.webapp.entity.User;

public interface UserDAO {
	
	List<User> list();
	List<User> list(String status);
	User getUser(int id);
	User getByUserName(String username);
	User validateUser(User user);
	boolean addUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(User user);

}
