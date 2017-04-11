package com.codehub.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.Blog;

@RestController
public class FriendController {

	@Autowired
	UserDAO userDAO;
	
	
}
