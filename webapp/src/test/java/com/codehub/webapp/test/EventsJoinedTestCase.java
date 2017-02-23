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

public class EventsJoinedTestCase {
	
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
	
	public EventsJoinedTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.codehub.webapp");
		context.refresh();
		eventsDAO =  (EventsDAO) context.getBean("eventsDAO");
		events = (Events) context.getBean("events");
		eventsJoinedDAO = (EventsJoinedDAO) context.getBean("eventsJoinedDAO");
		eventJoined = (EventJoined) context.getBean("eventJoined");
	}
	
//	@Test
//	public void addEventJoined() {
//		
//		eventJoined.setId(101);
//		eventJoined.setJoinedDate(LocalDate.parse("2007-02-10"));
//		events = eventsDAO.getEvent(4);
//		eventJoined.setEvents(events);
//		eventJoined.setStatus("PENDING");
//		eventJoined.setUserId(101);
//		eventJoined.setUsername("Avadhoot");
//		Assert.assertEquals(true, eventsJoinedDAO.addEventJoined(eventJoined));
//		
//	}
	
//	@Test
//	public void updateEventsJoined() {
//		eventJoined = eventsJoinedDAO.getEventJoined(1);
//		eventJoined.setStatus("Approved");
//		Assert.assertEquals(true, eventsJoinedDAO.updateEventJoined(eventJoined));
//	}
//	
//	@Test
//	public void getAllEventsJoinedTestCase() {
//		
//		int size = eventsJoinedDAO.list().size();
//		Assert.assertEquals(3, size);
//	}
//	
	@Test
	public void deleteEventsJoined() {
		eventJoined = eventsJoinedDAO.getEventJoined(1);
		Assert.assertEquals(true, eventsJoinedDAO.deleteEventJoined(eventJoined));
	}

}
