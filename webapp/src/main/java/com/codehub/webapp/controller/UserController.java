package com.codehub.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codehub.webapp.dao.BlogDAO;
import com.codehub.webapp.dao.EventsDAO;
import com.codehub.webapp.dao.EventsJoinedDAO;
import com.codehub.webapp.dao.ForumDAO;
import com.codehub.webapp.dao.FriendsDAO;
import com.codehub.webapp.dao.JobAppliedDAO;
import com.codehub.webapp.dao.JobDAO;
import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.ContainModel;
import com.codehub.webapp.entity.EventJoined;
import com.codehub.webapp.entity.Events;
import com.codehub.webapp.entity.Forum;
import com.codehub.webapp.entity.Friends;
import com.codehub.webapp.entity.Job;
import com.codehub.webapp.entity.JobApplied;
import com.codehub.webapp.entity.User;
import com.codehub.webapp.entity.UserModel;

import oracle.net.aso.b;

@RestController
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	EventsDAO eventsDAO;
	
	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	EventsJoinedDAO eventJoinedDAO;
	
	@Autowired
	JobAppliedDAO jobAppliedDAO;
	
	@Autowired
	ForumDAO forumDAO;
	
	@Autowired
	FriendsDAO friendsDAO;
	
	@RequestMapping(value = {"/register"}, method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User currentUser) {
		System.out.println("Registering now...................");
			currentUser.setStatus("PENDING");
			currentUser.setEnabled(true);
			currentUser.setOnline(false);
			currentUser.setProfile("noDp.png");
			currentUser.setRole("User");
//			System.out.println(currentUser.getBirthDate());
			userDAO.addUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public ResponseEntity<User> validateUser(@RequestBody User user) {
	
		if(user.getUsername() != null && user.getPassword() != null) {
			if(userDAO.validateUser(user) == null) {
				user = new User();
				user.setErrCode("204");
				user.setErrMessage("Invalid Credentials");
				return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
			} else {
				user = userDAO.getByUserName(user.getUsername());
				Boolean status = Boolean.valueOf("true");
				user.setOnline(status);
				user.setErrCode("200");
				user.setErrMessage("Login Successful!");
				userDAO.updateUser(user);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
			
		} else {
			user = new User();
			return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
		}
		
		
	}
	
	@RequestMapping(value = {"/checkuser"}, method = RequestMethod.POST)
	public ResponseEntity<Void> checkUsername(@RequestBody String userName) {
		
		User existingUser = userDAO.getByUserName(userName);
		if(existingUser == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
	public ResponseEntity<Void> toLogout(@RequestBody User user) {
		
		user.setOnline(false);
		userDAO.updateUser(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//Method for fetching users
	@RequestMapping(value = {"/users/list"}, method = RequestMethod.GET)
	public ResponseEntity<List<User>> fetchUsers() {
			System.out.println("fetching users");
			List<User> user = userDAO.list("APPROVED");
			System.out.println(user);
			return new ResponseEntity<List<User>>(user, HttpStatus.OK);
		}
	
	 //function to fetch user and user detail
	@RequestMapping(value = {"/user/{id}"}, method =  RequestMethod.GET)
	public ResponseEntity<UserModel> fetchUser(@PathVariable("id") int id) {
		
		//Setting user inside model
		UserModel userModel = new UserModel();
		User user = userDAO.getUser(id);
		userModel.setUser(user);
		
		//Setting list of events created by user inside model
		List<Events> events = eventsDAO.getUserEvents(id);
		userModel.setEvents(events);
		
		
		//Setting list of jobs created by user inside model
		List<Job> job = jobDAO.getUserJobs(id);
		userModel.setJob(job);
		
		
		//Settling list of blogs created by user inside model
		List<Blog> blog = blogDAO.getUsersBlogs(id);
		userModel.setBlog(blog);
		
		//Settling list of events user has joined inside model
		List<EventJoined> eventJoined = eventJoinedDAO.list(id);
		List<Events> joinedEvents = new ArrayList<>();
		for (EventJoined ej : eventJoined) {
			joinedEvents.add(ej.getEvents());
		}
		userModel.setJoinedEvents(joinedEvents);
		
		//Settling list of jobs user has applied for inside model
		List<JobApplied> jobApplieds = jobAppliedDAO.list(id);
		List<Job> appliedJobList = new ArrayList<>();
		for (JobApplied ja : jobApplieds) {
			appliedJobList.add(ja.getJob());
		}
		userModel.setAppliedJobList(appliedJobList);
		
		//Setting list of user's friends
		
		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
	}
	
	
	  	//function to fetch main page contain
		@RequestMapping(value = {"/main/contain"}, method =  RequestMethod.GET)
		public ResponseEntity<ContainModel> fetchContain() {
			System.out.println("Fetching blogs");
			ContainModel containModel = new ContainModel();
			List<Blog> top5Blogs = blogDAO.mainList();
			containModel.setTop5Blogs(top5Blogs);
			
			List<Forum> top3Forums = forumDAO.mainList();
			containModel.setTop3Forums(top3Forums);
			
			List<Job> top3Jobs = jobDAO.mainList();
			containModel.setTop3Jobs(top3Jobs);
			
			List<Events> top3Events = eventsDAO.mainList();
			containModel.setTop3Events(top3Events);
			return new ResponseEntity<ContainModel>(containModel, HttpStatus.OK);
		}
		
		//function to fetch my online friends
		@RequestMapping(value = {"/my/online/friends/{id}"}, method =  RequestMethod.GET)
		public ResponseEntity<List<User>> fetchOnlineFriends(@PathVariable ("id") int userId) {
			System.out.println("Fetching online friends");
			
			List<User> users =  userDAO.fetchOnlineFriends(userId);
			List<User> onlineFriends = new ArrayList<>();
			for(User user1 : users) {
				if(user1.getId() != userId) {
					onlineFriends.add(user1);
				}
			}
			
			return new ResponseEntity<List<User>>(onlineFriends, HttpStatus.OK);
		}

}
