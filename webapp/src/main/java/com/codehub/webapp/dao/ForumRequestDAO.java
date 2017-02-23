package com.codehub.webapp.dao;

import java.util.List;

import com.codehub.webapp.entity.ForumRequest;

public interface ForumRequestDAO {
	
	List<ForumRequest> list();
	ForumRequest getForumRequest(int id);
	boolean addForumRequest(ForumRequest forumRequest);
	boolean updateForumRequest(ForumRequest forumRequest);
	boolean deleteForumRequest(ForumRequest forumRequest);

}
