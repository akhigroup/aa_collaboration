package com.codehub.webapp.dao;

import java.util.List;

import com.codehub.webapp.entity.Forum;

public interface ForumDAO {

	List<Forum> list();
	Forum getForum(int id);
	boolean addForum(Forum forum);
	boolean updateForum(Forum forum);
	boolean deleteForum(Forum forum);
}
