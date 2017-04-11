package com.codehub.webapp.dao;

import java.util.List;

import com.codehub.webapp.entity.EventJoined;
import com.codehub.webapp.entity.Events;

public interface EventsJoinedDAO {
	
	List<EventJoined> list();
	List<EventJoined> list(int id);
	EventJoined getEventJoined(int id);
	boolean addEventJoined(EventJoined eventJoined);
	boolean updateEventJoined(EventJoined eventJoined);
	boolean deleteEventJoined(EventJoined eventJoined);

}
