package com.codehub.webapp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codehub.webapp.dao.BlogDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.User;

@RestController
public class BlogController {
	
	@Autowired
	BlogDAO blogDAO;
	
	@RequestMapping(value = {"/blog/new"}, method = RequestMethod.POST)
	public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) {
		System.out.println("Adding blog now");
		blog.setStatus("APPROVED");
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now(); 
		blog.setPostDate(LocalDate.parse(dtf.format(now)));
		blog.setNoOfLikes(0);
		blog.setNoOfViews(0);
		blog.setNoOfComments(0);
		
		blogDAO.addBlog(blog);
		
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);	
	}

}
