package com.codehub.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.User;

@RestController
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value = {"/register"}, method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User currentUser) {
		System.out.println("Registering now...................");
			currentUser.setStatus("PENDING");
			currentUser.setEnabled(true);
			currentUser.setOnline(false);
			currentUser.setProfile("noDp.png");
			currentUser.setRole("User");
//			System.out.println(currentUser.getBirthDate());
			userDAO.addUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public ResponseEntity<User> validateUser(@RequestBody User user) {
	
		if(user.getUsername() != null && user.getPassword() != null) {
			if(userDAO.validateUser(user) == null) {
				user = new User();
				user.setErrCode("204");
				user.setErrMessage("Invalid Credentials");
				return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
			} else {
				user = userDAO.getByUserName(user.getUsername());
				user.setOnline(true);
				user.setErrCode("200");
				user.setErrMessage("Login Successful!");
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
			
		} else {
			user = new User();
			return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
		}
		
		
	}
	
	@RequestMapping(value = {"/checkuser"}, method = RequestMethod.POST)
	public ResponseEntity<Void> checkUsername(@RequestBody String userName) {
		
		User existingUser = userDAO.getByUserName(userName);
		if(existingUser == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
	public ResponseEntity<Void> toLogout(@RequestBody User user) {
		
		user.setOnline(false);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//Method for fetching users
	@RequestMapping(value = {"/users/list"}, method = RequestMethod.GET)
	public ResponseEntity<List<User>> fetchUsers() {
			System.out.println("fetching users");
			List<User> user = userDAO.list("APPROVED");
			System.out.println(user);
			return new ResponseEntity<List<User>>(user, HttpStatus.OK);
		}


}
