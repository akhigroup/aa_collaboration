package com.codehub.webapp.dao;

import java.util.List;

import com.codehub.webapp.entity.Events;

public interface EventsDAO {
	
	List<Events> list();
	Events getEvent(int id);
	boolean addEvent(Events event);
	boolean updateEvent(Events event);
	boolean deleteEvent(Events event);

}
