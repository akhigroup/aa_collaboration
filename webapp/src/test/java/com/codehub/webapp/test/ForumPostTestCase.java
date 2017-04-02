package com.codehub.webapp.test;

import java.time.LocalDate;

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

public class ForumPostTestCase {

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
	
	public ForumPostTestCase() {
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
	
//	@Test
//	public void addForumPost() {
//		forumPosts.setId(1);
//		forumPosts.setUsername("Avadhoot");
//		forumPosts.setUserId(101);
//		forumPosts.setDescription("This is a forum post");
//		forum = forumDAO.getForum(5);
//		forumPosts.setForum(forum);
//		forumPosts.setPostDate(LocalDate.parse("2007-02-10"));
//		
//		
//		Assert.assertEquals(true, forumPostDAO.addForumPosts(forumPosts));
//	}
	
//	@Test
//	public void updateForumPost() {
//		forumPosts = forumPostDAO.getForumPosts(3);
//		forumPosts.setUserId(102);
//		Assert.assertEquals(true, forumPostDAO.updateForumPosts(forumPosts));
//	}
//	
//	@Test
//	public void getAllForumPost() {
//		
//		int size = forumPostDAO.list().size();
//		Assert.assertEquals(1, size);
//	}
//	
	@Test
	public void deleteForumPost() {
		forumPosts = forumPostDAO.getForumPosts(3);
		Assert.assertEquals(true, forumPostDAO.deleteForumPosts(forumPosts));
	}
}
