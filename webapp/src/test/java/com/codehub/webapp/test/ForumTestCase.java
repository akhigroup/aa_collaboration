package com.codehub.webapp.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.codehub.webapp.dao.ForumDAO;
import com.codehub.webapp.dao.ForumDAO;
import com.codehub.webapp.dao.ForumPostDAO;
import com.codehub.webapp.dao.ForumRequestDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.Forum;
import com.codehub.webapp.entity.Forum;
import com.codehub.webapp.entity.ForumPosts;
import com.codehub.webapp.entity.ForumRequest;

import junit.framework.Assert;

public class ForumTestCase {
	
	@Autowired
	Forum forum;
	
	@Autowired
	ForumDAO forumDAO;
	
//	@Autowired
//	ForumPosts forumPosts;
//	
//	@Autowired
//	ForumPostDAO forumPostDAO;
//	
//	@Autowired
//	ForumRequest forumRequest;
//	
//	@Autowired
//	ForumRequestDAO forumRequestDAO;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public ForumTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.codehub.webapp");
		context.refresh();
		forumDAO = (ForumDAO) context.getBean("forumDAO");
		forum = (Forum) context.getBean("forum");
//		forumPostDAO = (ForumPostDAO) context.getBean("forumPostDAO");
//		forumPosts = (ForumPosts) context.getBean("forumPosts");
//		forumRequestDAO = (ForumRequestDAO) context.getBean("forumRequestDAO");
//		forumRequest = (ForumRequest) context.getBean("forumRequest");
	}
	
//	@Test
//	public void addForumCategory() {
//		forumCategory.setId(1);
//		forumCategory.setName("Java");
//		forumCategory.setDescription("Category for java");
//		forumCategory.setStatus("PENDING");
//				
//		Assert.assertEquals(true, forumCategoryDAO.addForum(forumCategory));
//	}
	
//	@Test
//	public void updateForumCategory() {
//		forumCategory = forumCategoryDAO.getForumCategory(1);
//		forumCategory.setStatus("Approved");
//		Assert.assertEquals(true, forumCategoryDAO.updateForumCategory(forumCategory));
//	}
//	
//	@Test
//	public void getAllForumCategoryCategory() {
//		
//		int size = forumCategoryDAO.list().size();
//		Assert.assertEquals(1, size);
//	}
//	
//	@Test
//	public void deleteForumCategory() {
//		forumCategory = forumCategoryDAO.getForumCategory(1);
//		Assert.assertEquals(true, forumCategoryDAO.deleteForumCategory(forumCategory));
//	}
	
	@Test
	public void fetchForumList() {
		List<Forum> forumlist = forumDAO.mainList();
		for(Forum f1 : forumlist ) {
			System.out.println(f1.getPostDate());
		}
		Assert.assertEquals(3, forumDAO.mainList().size());
	}

}
