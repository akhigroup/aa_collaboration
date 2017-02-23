package com.codehub.webapp.test;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.User;

import junit.framework.Assert;

public class UserTestCase {

	@Autowired
	User user;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	
	public UserTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.codehub.webapp");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
		user = (User) context.getBean("user");
	}
	
	@Test
	public void addUser() {
		user.setId(101);
		user.setUsername("Avadhoot");
		user.setFirstname("Avadhoot");
		user.setLastname("Athalye");
		user.setPassword("1234");
		user.setEmailId("ava@gmail.com");
		user.setBirthDate(LocalDate.parse("2007-02-10")); //2007-02-10
		user.setGender('M');
		user.setRole("Admin");
		user.setStatus("This is a test");
		user.setEnabled(true);
		user.setOnline(false);
		
		Assert.assertEquals(true, userDAO.addUser(user));
		
	}
	
	
	@Test
	public void updateUser() {
		user = userDAO.getUser(101);
		user.setEmailId("athalye.49@gmail.com");
		Assert.assertEquals(true, userDAO.updateUser(user));
	}
//	
	
	@Test
	public void deleteUser() {
		user = userDAO.getUser(101);
		Assert.assertEquals(true, userDAO.deleteUser(user));
	}
	
	@Test
	public void getAllUserTestCase() {
		
		int size = userDAO.list().size();
		Assert.assertEquals(1, size);
	}
}
