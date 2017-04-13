package com.codehub.webapp.dao;

import java.util.List;

import com.codehub.webapp.entity.Friends;
import com.codehub.webapp.entity.User;

public interface FriendsDAO {
	
	List<Friends> list();
	List<Friends> list(int id);
	List<Friends> list(String status);
	Friends getFriend(int id);
	boolean addFriend(Friends friends);
	boolean updateFriend(Friends friends);
	boolean deleteFriend(Friends friends);
	List<User> noFriends(int id);

}
