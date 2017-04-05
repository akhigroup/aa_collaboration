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

import com.codehub.webapp.dao.JobDAO;
import com.codehub.webapp.dao.UserDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.Job;
import com.codehub.webapp.entity.User;

@RestController
public class JobController {

	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	UserDAO userDAO;
	
	//Method for creating a new blog
	
		@RequestMapping(value = {"/job/new"}, method = RequestMethod.POST)
		public ResponseEntity<Job> addJob(@RequestBody Job job) {
			System.out.println("Adding job now");
			int id = job.getUserId();
			User user = userDAO.getUser(id);
			if( user.getRole().equals("Super_Admin") || user.getRole().equals("Admin") ) {
				job.setStatus("APPROVED");
			} else {
				job.setStatus("PENDING");
			}
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now(); 
			job.setPostDate(LocalDate.parse(dtf.format(now)));
			jobDAO.addJob(job);
			
			return new ResponseEntity<Job>(job, HttpStatus.OK);	
		}
		
		//Method for fetching job list by status
		@RequestMapping(value = {"/job/list/status"}, method = RequestMethod.GET)
		public ResponseEntity<List<Job>> fetchApprovedJobs(String status) {
			System.out.println("fetching list of jobs by status");
			List<Job> job = jobDAO.getJobsByStatus("APPROVED");
			return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
		}
		
		//Method for fetching user's jobs
		@RequestMapping(value = {"/user/jobs/list/{id}"}, method = RequestMethod.GET)
		public ResponseEntity<List<Job>> fetchUsersJobs(@PathVariable("id") int id) {
			System.out.println("Fetching users jobs");
			List<Job> job = jobDAO.getUserJobs(id);
			return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
		}
}
