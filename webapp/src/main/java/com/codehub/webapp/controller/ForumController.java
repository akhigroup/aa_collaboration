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

import com.codehub.webapp.dao.ForumDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.Forum;

@RestController
public class ForumController {

	@Autowired
	ForumDAO forumDAO;
	
	//Method for creating new forum category
	@RequestMapping(value = {"/forum/new"}, method = RequestMethod.POST)
	public ResponseEntity<Forum> addForumCategory(@RequestBody Forum forum) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now(); 
		forum.setPostDate(LocalDate.parse(dtf.format(now)));
		forum.setStatus("APPROVED");
		forum.setNoOfPosts(0);
		
		forumDAO.addForum(forum);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	//Method for fetching list of all forum categories
	@RequestMapping(value = {"/forum/list"}, method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> fetchForums() {
		System.out.println("Method called");
		List<Forum> forums = forumDAO.list();
		return new ResponseEntity<List<Forum>>(forums, HttpStatus.OK);
	}
	
	
	//Method for viewing single blog using blog id as a parameter
	
	@RequestMapping(value = {"/forum/{id}"}, method = RequestMethod.GET)
	public ResponseEntity<Forum> viewForum(@PathVariable("id") int id) {
		System.out.println("Calling method");
		Forum forum = new Forum();
		forum = forumDAO.getForum(id);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
			
		}
}
