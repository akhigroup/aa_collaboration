package com.codehub.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.User;

@RestController
public class UserController {

	@Autowired
	User user;
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value = "/user/list")
	public ResponseEntity<List<User>> listAllUsers() {
		System.out.println("Method called");
		List<User> users = userDAO.list();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/get/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("userId") int userId) {
		System.out.println("Fetching User");
		User user = userDAO.getUser(userId);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/register"}, method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User currentUser) {
		System.out.println("Registering now...................");
			currentUser.setStatus("PENDING");
			currentUser.setEnabled(true);
			currentUser.setOnline(false);
			currentUser.setProfile("noDp.png");
			currentUser.setRole("User");
			userDAO.addUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public ResponseEntity<User> validateUser(@RequestBody User currentUser) {
	
		user.setUsername(currentUser.getUsername());
		user.setPassword(currentUser.getPassword());
		user = userDAO.validateUser(user);
		user = userDAO.getByUserName(currentUser.getUsername());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/checkuser"})
	public ResponseEntity<Void> checkUsername(@RequestBody String userName) {
		
		user = userDAO.getByUserName(userName);
		if(user == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = "/user/get/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("userId") int userId, @RequestBody User updatedUser) {
		user = userDAO.getUser(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	


}
