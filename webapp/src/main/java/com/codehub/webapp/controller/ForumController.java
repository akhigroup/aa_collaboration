package com.codehub.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codehub.webapp.dao.ForumCategoryDAO;
import com.codehub.webapp.entity.ForumCategory;

@RestController
public class ForumController {

	@Autowired
	ForumCategoryDAO forumCategoryDAO;
	
	//Method for creating new forum category
	@RequestMapping(value = {"/forum/category/new"}, method = RequestMethod.POST)
	public ResponseEntity<ForumCategory> addForumCategory(@RequestBody ForumCategory forumCategory) {
		
		forumCategory.setStatus("APPROVED");
		forumCategory.setNoOfPosts(0);
		forumCategory.setNoOfTopics(0);
		
		forumCategoryDAO.addForumCategory(forumCategory);
		return new ResponseEntity<ForumCategory>(forumCategory, HttpStatus.OK);
	}
	
	//Method for fetching list of all forum categories
	@RequestMapping(value = {"/forum/categories/list"}, method = RequestMethod.GET)
	public ResponseEntity<List<ForumCategory>> fetchForumCategories() {
		System.out.println("Method called");
		List<ForumCategory> forumCategories = forumCategoryDAO.list();	
		return new ResponseEntity<List<ForumCategory>>(forumCategories, HttpStatus.OK);
	}
}
