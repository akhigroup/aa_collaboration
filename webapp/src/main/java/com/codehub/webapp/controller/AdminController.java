package com.codehub.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codehub.webapp.dao.BlogDAO;
import com.codehub.webapp.dao.ForumRequestDAO;
import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.ForumRequest;
import com.codehub.webapp.entity.User;

@RestController
public class AdminController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	BlogDAO blogDAO;
	
	
		//Method for fetching approved user list by status
		@RequestMapping(value = {"/user/manage/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<User>> fetchApprovedUsers() {
				System.out.println("fetching list of approved users");
				List<User> user = userDAO.list("APPROVED");
				return new ResponseEntity<List<User>>(user, HttpStatus.OK);
		}
		
		//Method for fetching approved blog list by status
		@RequestMapping(value = {"/blog/manage/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<Blog>> fetchApprovedBlogs() {
				System.out.println("fetching list of approved blogs");
				List<Blog> blog = blogDAO.getBlogsByStatus("APPROVED");
				return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
		}
		
		
}
