package com.codehub.webapp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codehub.webapp.dao.BlogCommentDAO;
import com.codehub.webapp.dao.BlogDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.BlogComments;
import com.codehub.webapp.entity.User;

@RestController
public class BlogCommentController {

	@Autowired
	Blog blog;
	
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	BlogCommentDAO blogCommentsDAO;
	
	@Autowired
	User user;
	
	//Method for creating a new blog comment
	
		@RequestMapping(value = {"/blog/comment/new/{id}"}, method = RequestMethod.POST)
		public ResponseEntity<BlogComments> addBlogComment(@PathVariable("id") int id, @RequestBody BlogComments blogComments) {
			System.out.println("Adding blog comment now");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now(); 
			blogComments.setCommentDate(LocalDate.parse(dtf.format(now)));
			blog = blogDAO.getBlog(id);
			int comments = blog.getNoOfComments();
			blog.setNoOfComments(comments + 1);
			blogDAO.updateBlog(blog);
			blogComments.setBlog(blog);
			blogCommentsDAO.addBlogComments(blogComments);
			
			return new ResponseEntity<BlogComments>(blogComments, HttpStatus.OK);	
		}
		
		//Method for fetching blog comment list
		@RequestMapping(value = {"/blog/comment/list/{id}"}, method = RequestMethod.GET)
		public ResponseEntity<List<BlogComments>> fetchBlogComments(@PathVariable("id") int id) {
			System.out.println("fetching list of blog comments");
			List<BlogComments> blogComments = blogCommentsDAO.list(id);
			return new ResponseEntity<List<BlogComments>>(blogComments, HttpStatus.OK);
		}
	
	
}
