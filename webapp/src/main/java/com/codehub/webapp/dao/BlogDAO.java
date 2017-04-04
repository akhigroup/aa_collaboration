package com.codehub.webapp.dao;

import java.util.List;

import com.codehub.webapp.entity.Blog;

public interface BlogDAO {

	List<Blog> list();
	List<Blog> getBlogsByStatus(String status);
	List<Blog> getUsersBlogs(int id);
	Blog getBlog(int id);
	boolean addBlog(Blog blog);
	boolean updateBlog(Blog blog);
	boolean deleteBlog(Blog blog);
}
