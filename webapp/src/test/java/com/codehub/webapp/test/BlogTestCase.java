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

public class BlogTestCase {

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
	
	public BlogTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.codehub.webapp");
		context.refresh();
		blogDAO = (BlogDAO) context.getBean("blogDAO");
		blog = (Blog) context.getBean("blog");
		blogCommentDAO = (BlogCommentDAO) context.getBean("blogCommentDAO");
		blogComments = (BlogComments) context.getBean("blogComments");
	}
	
	@Test
	public void addBlogTest() {
		
		blog.setId(101);
		blog.setName("This is test");
		blog.setStatus("PENDING");
		blog.setDescription("Test");
		blog.setPostDate(LocalDate.parse("2007-02-10"));
		blog.setNoOfLikes(100);
		blog.setNoOfComments(50);
		blog.setUserId(101);
		blog.setUserName("Avadhoot");
		
		Assert.assertEquals(true, blogDAO.addBlog(blog));
	}
	
	@Test
	public void updateBlog() {
		blog = blogDAO.getBlog(1);
		blog.setStatus("APPROVED");
		Assert.assertEquals(true, blogDAO.updateBlog(blog));
	}
	
	@Test
	public void getAllBlogsTestCase() {
		
		int size = blogDAO.list().size();
		Assert.assertEquals(1, size);
	}
	
	@Test
	public void deleteBlog() {
		blog = blogDAO.getBlog(1);
		Assert.assertEquals(true, blogDAO.deleteBlog(blog));
	}
}
