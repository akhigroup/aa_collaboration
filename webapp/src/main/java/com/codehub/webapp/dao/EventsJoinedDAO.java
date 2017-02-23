package com.codehub.webapp.dao;

import java.util.List;

import com.codehub.webapp.entity.EventJoined;

public interface EventsJoinedDAO {
	
	List<EventJoined> list();
	EventJoined getEventJoined(int id);
	boolean addEventJoined(EventJoined eventJoined);
	boolean updateEventJoined(EventJoined eventJoined);
	boolean deleteEventJoined(EventJoined eventJoined);

}
