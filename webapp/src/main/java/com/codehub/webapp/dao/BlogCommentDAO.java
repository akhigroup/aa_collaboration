package com.codehub.webapp.dao;

import java.util.List;

import com.codehub.webapp.entity.BlogComments;

public interface BlogCommentDAO {

	List<BlogComments> list();
	BlogComments getBlogComments(int id);
	boolean addBlogComments(BlogComments blogComments);
	boolean updateBlogComments(BlogComments blogComments);
	boolean deleteBlogComments(BlogComments blogComments);
}
