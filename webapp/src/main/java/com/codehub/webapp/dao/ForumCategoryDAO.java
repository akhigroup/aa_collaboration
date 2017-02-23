package com.codehub.webapp.dao;

import java.util.List;

import com.codehub.webapp.entity.ForumCategory;

public interface ForumCategoryDAO {

	List<ForumCategory> list();
	ForumCategory getForumCategory(int id);
	boolean addForumCategory(ForumCategory forumCategory);
	boolean updateForumCategory(ForumCategory forumCategory);
	boolean deleteForumCategory(ForumCategory forumCategory);
}
