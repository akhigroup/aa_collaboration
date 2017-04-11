package com.codehub.webapp.test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.codehub.webapp.dao.BlogCommentDAO;
import com.codehub.webapp.dao.BlogDAO;
import com.codehub.webapp.dao.FriendsDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.BlogComments;
import com.codehub.webapp.entity.Friends;

import junit.framework.Assert;

public class FriendsTestCase {

	@Autowired
	Friends friends;
	
	@Autowired
	FriendsDAO friendsDAO;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public FriendsTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.codehub.webapp");
		context.refresh();
		friendsDAO = (FriendsDAO) context.getBean("friendsDAO");
		friends = (Friends) context.getBean("friends");
	}
	
//	@Test
//	public void addFriend() {
//		
//		friends.setId(01);
//		friends.setInitiatorId(05);
//		friends.setFriendId(10);
//		friends.setStatus("PENDING");
//		
//		Assert.assertEquals(true, friendsDAO.addFriend(friends));
//	}
	
//	@Test
//	public void updateFriend() {
//		friends = friendsDAO.getFriend(1);
//		friends.setStatus("APPROVED");
//		Assert.assertEquals(true, friendsDAO.updateFriend(friends));
//	}

//	@Test
//	public void getAllFriends() {
//		
//		int size = friendsDAO.list().size();
//		Assert.assertEquals(1, size);
//	}
	
	@Test
	public void deleteFriend() {
		friends = friendsDAO.getFriend(1);
		Assert.assertEquals(true, friendsDAO.deleteFriend(friends));
	}
}
