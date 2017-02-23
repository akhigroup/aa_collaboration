package com.codehub.webapp.test;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.codehub.webapp.dao.ForumCategoryDAO;
import com.codehub.webapp.dao.ForumDAO;
import com.codehub.webapp.dao.ForumPostDAO;
import com.codehub.webapp.dao.ForumRequestDAO;
import com.codehub.webapp.entity.Forum;
import com.codehub.webapp.entity.ForumCategory;
import com.codehub.webapp.entity.ForumPosts;
import com.codehub.webapp.entity.ForumRequest;

import junit.framework.Assert;

public class ForumTestCase {
	
	@Autowired
	Forum forum;
	
	@Autowired
	ForumDAO forumDAO;
	
	@Autowired
	ForumCategory forumCategory;
	
	@Autowired
	ForumCategoryDAO forumCategoryDAO;
	
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
	
	public ForumTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.codehub.webapp");
		context.refresh();
		forumDAO = (ForumDAO) context.getBean("forumDAO");
		forum = (Forum) context.getBean("forum");
		forumCategoryDAO = (ForumCategoryDAO) context.getBean("forumCategoryDAO");
		forumCategory = (ForumCategory) context.getBean("forumCategory");
		forumPostDAO = (ForumPostDAO) context.getBean("forumPostDAO");
		forumPosts = (ForumPosts) context.getBean("forumPosts");
		forumRequestDAO = (ForumRequestDAO) context.getBean("forumRequestDAO");
		forumRequest = (ForumRequest) context.getBean("forumRequest");
	}
	
	@Test
	public void addForum() {
		forum.setId(1);
		forum.setName("Forum");
		forum.setDescription("This is a forum");
		forum.setDateCreated(LocalDate.parse("2007-02-10"));
		forum.setNoOfPosts(25);
		forum.setNoOfmembers(50);
		forum.setNoOfrequests(30);
		forum.setStatus("PENDING");
		forumCategory = forumCategoryDAO.getForumCategory(2);
		forum.setForumCategory(forumCategory);
		forum.setUserId(101);
		forum.setUsername("Avadhoot");
		
		Assert.assertEquals(true, forumDAO.addForum(forum));
	}
//	
//	@Test
//	public void updateForum() {
//		forum = forumDAO.getForum(2);
//		forumCategory.setStatus("Approved");
//		Assert.assertEquals(true, forumDAO.updateForum(forum));
//	}
	
//	@Test
//	public void getAllForum() {
//		
//		int size = forumDAO.list().size();
//		Assert.assertEquals(1, size);
//	}
//	
//	@Test
//	public void deleteForum() {
//		forum = forumDAO.getForum(2);
//		Assert.assertEquals(true, forumDAO.deleteForum(forum));
//	}

}
