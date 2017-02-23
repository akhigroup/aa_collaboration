package com.codehub.webapp.test;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.codehub.webapp.dao.EventsDAO;
import com.codehub.webapp.dao.EventsJoinedDAO;
import com.codehub.webapp.entity.EventJoined;
import com.codehub.webapp.entity.Events;

import junit.framework.Assert;

public class EventTestCase {

	@Autowired
	Events events;
	
	@Autowired
	EventsDAO eventsDAO;
	
	@Autowired
	EventJoined eventJoined;
	
	@Autowired
	EventsJoinedDAO eventsJoinedDAO;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public EventTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.codehub.webapp");
		context.refresh();
		eventsDAO =  (EventsDAO) context.getBean("eventsDAO");
		events = (Events) context.getBean("events");
		eventsJoinedDAO = (EventsJoinedDAO) context.getBean("eventsJoinedDAO");
		eventJoined = (EventJoined) context.getBean("eventJoined");
	}
	
	@Test
	public void addEvent() {
		events.setId(101);
		events.setName("Test");
		events.setStartDate(LocalDate.parse("2007-02-10"));
		events.setEndDate(LocalDate.parse("2007-02-10"));
		events.setPostDate(LocalDate.parse("2007-02-10"));
		events.setDescription("This is a event");
		events.setUserId(101);
		events.setUsername("Avadhoot");
		events.setVenue("Mumbai");
		Assert.assertEquals(true, eventsDAO.addEvent(events));
		
	}
	
//	@Test
//	public void updateEvents() {
//		events = eventsDAO.getEvent(3);
//		events.setVenue("Pune");
//		Assert.assertEquals(true, eventsDAO.updateEvent(events));
//	}
//	
//	@Test
//	public void getAllEventsTestCase() {
//		
//		int size = eventsDAO.list().size();
//		Assert.assertEquals(1, size);
//	}
//	
//	@Test
//	public void deleteEvents() {
//		events = eventsDAO.getEvent(3);
//		Assert.assertEquals(true, eventsDAO.deleteEvent(events));
//	}
	
}
