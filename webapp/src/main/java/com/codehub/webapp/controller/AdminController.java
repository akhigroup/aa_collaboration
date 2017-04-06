package com.codehub.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.User;

@RestController
public class AdminController {
	
	@Autowired
	UserDAO userDAO;

		//Method for fetching pending user list by status
		@RequestMapping(value = {"/user/request/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<User>> fetchPendingUsers() {
			System.out.println("fetching list of pending users");
			List<User> user = userDAO.list("PENDING");
			return new ResponseEntity<List<User>>(user, HttpStatus.OK);
		}
		
		//Method to change user registration status
		@RequestMapping(value = {"/user/request/approval/{id}"}, method = RequestMethod.POST)
		public ResponseEntity<User> changeStatus(@PathVariable("id") int id) {
				System.out.println("changing status");
				User user = new User();
				user = userDAO.getUser(id);
				user.setStatus("APPROVED");
				userDAO.updateUser(user);
				return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
		
}
