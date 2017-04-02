package com.codehub.webapp.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.codehub.webapp.dao.ForumDAO;
import com.codehub.webapp.dao.ForumDAO;
import com.codehub.webapp.dao.ForumPostDAO;
import com.codehub.webapp.dao.ForumRequestDAO;
import com.codehub.webapp.entity.Forum;
import com.codehub.webapp.entity.Forum;
import com.codehub.webapp.entity.ForumPosts;
import com.codehub.webapp.entity.ForumRequest;

import junit.framework.Assert;

public class ForumRequestTestCase {

	@Autowired
	Forum forum;
	
	@Autowired
	ForumDAO forumDAO;
	
	@Autowired
	Forum forumCategory;
	
	@Autowired
	ForumDAO forumCategoryDAO;
	
	@Autowired
	ForumPosts forumPosts;
	
	@Autowired
	ForumPostDAO forumPostDAO;
	
	@Autowired
	ForumRequest forumRequest;
	
	@Autowired
	ForumRequestDAO forumRequestDAO;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public ForumRequestTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.codehub.webapp");
		context.refresh();
		forumDAO = (ForumDAO) context.getBean("forumDAO");
		forum = (Forum) context.getBean("forum");
		forumCategoryDAO = (ForumDAO) context.getBean("forumCategoryDAO");
		forumCategory = (Forum) context.getBean("forumCategory");
		forumPostDAO = (ForumPostDAO) context.getBean("forumPostDAO");
		forumPosts = (ForumPosts) context.getBean("forumPosts");
		forumRequestDAO = (ForumRequestDAO) context.getBean("forumRequestDAO");
		forumRequest = (ForumRequest) context.getBean("forumRequest");
	}
	
	@Test
	public void addForumRequest() {
		forumRequest.setId(1);
		forumRequest.setUserId(101);
		forumRequest.setUsername("Avadhoot");
		forumRequest.setStatus("Pending");
		forum = forumDAO.getForum(5);
		forumRequest.setForum(forum);
		
		
		Assert.assertEquals(true, forumRequestDAO.addForumRequest(forumRequest));
	}
	
//	@Test
//	public void updateForumRequest() {
//		forumRequest = forumRequestDAO.getForumRequest(1);
//		forumRequest.setStatus("Approved");
//		Assert.assertEquals(true, forumRequestDAO.updateForumRequest(forumRequest));
//	}
//	
//	@Test
//	public void getAllForumRequestTestCase() {
//		
//		int size = forumRequestDAO.list().size();
//		Assert.assertEquals(1, size);
//	}
//	
//	@Test
//	public void deleteForumRequest() {
//		forumRequest = forumRequestDAO.getForumRequest(1);
//		Assert.assertEquals(true, forumRequestDAO.deleteForumRequest(forumRequest));
//	}
}
