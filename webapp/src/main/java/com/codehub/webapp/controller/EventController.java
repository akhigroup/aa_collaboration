package com.codehub.webapp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codehub.webapp.dao.EventsDAO;
import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.Events;
import com.codehub.webapp.entity.Job;
import com.codehub.webapp.entity.User;

import antlr.debug.Event;

@RestController
public class EventController {

	@Autowired
	EventsDAO eventsDAO;
	
	@Autowired
	UserDAO userDAO;
	
	
	//Method for creating a new event
	
	@RequestMapping(value = {"/events/new"}, method = RequestMethod.POST)
	public ResponseEntity<Events> addEvents(@RequestBody Events events) {
		System.out.println("Adding events now");
		int id = events.getUserId();
		User user = userDAO.getUser(id);
		if( user.getRole().equals("Super_Admin") || user.getRole().equals("Admin") ) {
			events.setStatus("APPROVED");
		} else {
			events.setStatus("PENDING");
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now(); 
		events.setPostDate(LocalDate.parse(dtf.format(now)));
		eventsDAO.addEvent(events);
		
		return new ResponseEntity<Events>(events, HttpStatus.OK);		
	}
	
	//Method for fetching job list by status
	@RequestMapping(value = {"/events/list/status"}, method = RequestMethod.GET)
	public ResponseEntity<List<Events>> fetchApprovedEvents(String status) {
		System.out.println("fetching list of events by status");
		List<Events> events = eventsDAO.getEventsByStatus("APPROVED");
		return new ResponseEntity<List<Events>>(events, HttpStatus.OK);
	}
	
	//Method for fetching user's jobs
	@RequestMapping(value = {"/user/events/list/{id}"}, method = RequestMethod.GET)
	public ResponseEntity<List<Events>> fetchUserEvents(@PathVariable("id") int id) {
		System.out.println("Fetching users events");
		List<Events> events = eventsDAO.getUserEvents(id);
		return new ResponseEntity<List<Events>>(events, HttpStatus.OK);
		}
}
