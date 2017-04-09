package com.codehub.webapp.dao;

import java.util.List;

import com.codehub.webapp.entity.ForumPosts;

public interface ForumPostDAO {

	List<ForumPosts> list();
	List<ForumPosts> list(int id);
	ForumPosts getForumPosts(int id);
	boolean addForumPosts(ForumPosts forumPosts);
	boolean updateForumPosts(ForumPosts forumPosts);
	boolean deleteForumPosts(ForumPosts forumPosts);
}
