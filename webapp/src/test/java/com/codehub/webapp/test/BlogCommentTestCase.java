package com.codehub.webapp.test;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.codehub.webapp.dao.BlogCommentDAO;
import com.codehub.webapp.dao.BlogDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.BlogComments;

import junit.framework.Assert;

public class BlogCommentTestCase {
	
	@Autowired
	Blog blog;
	
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	BlogComments blogComments;
	
	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public BlogCommentTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.codehub.webapp");
		context.refresh();
		blogDAO = (BlogDAO) context.getBean("blogDAO");
		blog = (Blog) context.getBean("blog");
		blogCommentDAO = (BlogCommentDAO) context.getBean("blogCommentDAO");
		blogComments = (BlogComments) context.getBean("blogComments");
	}
	
//	@Test
//	public void addBlogCommentTest() {
//		
//		blogComments.setId(10);
//		blogComments.setBlogComment("This is a comment");
//		blogComments.setNoOfLikes(25);
//		blogComments.setUserId(101);
//		blogComments.setUsername("Avadhoot");
//		blogComments.setCommentDate(LocalDate.parse("2007-02-10"));
//		blog = blogDAO.getBlog(2);
//		blogComments.setBlog(blog);
//		Assert.assertEquals(true, blogCommentDAO.addBlogComments(blogComments));
//	}
	
//	@Test
//	public void updateBlogCommentTestCase() {
//		blogComments = blogCommentDAO.getBlogComments(6);
//		blogComments.setNoOfLikes(50);
//		Assert.assertEquals(true, blogCommentDAO.updateBlogComments(blogComments));
//	}
	
	@Test
	public void getAllBlogCommentTestCase() {
		
		int size = blogCommentDAO.list().size();
		Assert.assertEquals(1, size);
	}
//	
//	@Test
//	public void deleteBlogComment() {
//		blogComments = blogCommentDAO.getBlogComments(7);
//		Assert.assertEquals(true, blogCommentDAO.deleteBlogComments(blogComments));
//	}
}
