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

import com.codehub.webapp.dao.BlogDAO;
import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.BlogListModel;
import com.codehub.webapp.entity.Forum;
import com.codehub.webapp.entity.User;

@RestController
public class BlogController {
	
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	UserDAO userDAO;
	
	
	//Method for creating a new blog
	
	@RequestMapping(value = {"/blog/new"}, method = RequestMethod.POST)
	public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) {
		System.out.println("Adding blog now");
		int id = blog.getUserId();
		User user = userDAO.getUser(id);
		if( user.getRole().equals("Super_Admin") || user.getRole().equals("Admin") ) {
			blog.setStatus("APPROVED");
		} else {
			blog.setStatus("PENDING");
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now(); 
		blog.setPostDate(LocalDate.parse(dtf.format(now)));
		blog.setNoOfLikes(0);
		blog.setNoOfViews(0);
		blog.setNoOfComments(0);
		
		blogDAO.addBlog(blog);
		
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);	
	}
	
	//Method for viewing single blog using blog id as a parameter
	
	@RequestMapping(value = {"/blog/{id}"}, method = RequestMethod.GET)
	public ResponseEntity<BlogListModel> viewBlog(@PathVariable("id") int id) {
		System.out.println("Calling method");
		BlogListModel blogListModel = new BlogListModel();
		Blog blog = new Blog();
		blog = blogDAO.getBlog(id);
		blogListModel.setBlog(blog);
		if(blog == null) {
			blog = new Blog();
			blog.setErrCode("404");
			blog.setErrMessage("Blog not found!");
		}
		return new ResponseEntity<BlogListModel>(blogListModel, HttpStatus.OK);
		
	}
	
	//Method for fetching bloglist
	@RequestMapping(value = {"/blog/list"}, method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> fetchBlogs() {
		System.out.println("fetching list of blogs");
		List<Blog> blog = blogDAO.list();
		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
	}
	
	//Method for fetching blog list by status
	@RequestMapping(value = {"/blog/list/status"}, method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> fetchApprovedBlogs(String status) {
		System.out.println("fetching list of blogs by status");
		List<Blog> blog = blogDAO.getBlogsByStatus("APPROVED");
		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
	}

	//Method for fetching user's blogs
	@RequestMapping(value = {"/user/blogs/list"}, method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> fetchUsersBlogs() {
		System.out.println("Fetching users blogs");
		int id = 29;
		List<Blog> blog = blogDAO.getUsersBlogs(id);
		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
	}
}

