package com.codehub.webapp.test;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.codehub.webapp.dao.JobAppliedDAO;
import com.codehub.webapp.dao.JobDAO;
import com.codehub.webapp.entity.Job;
import com.codehub.webapp.entity.JobApplied;

import junit.framework.Assert;

public class JobAppliedTestCase {

	@Autowired
	Job job;
	
	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	JobApplied jobApplied;
	
	@Autowired
	JobAppliedDAO jobAppliedDAO;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public JobAppliedTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.codehub.webapp");
		context.refresh();
		jobDAO = (JobDAO) context.getBean("jobDAO");
		job = (Job) context.getBean("job");
		jobAppliedDAO = (JobAppliedDAO) context.getBean("jobAppliedDAO");
		jobApplied = (JobApplied) context.getBean("jobApplied");
	}
	
//	@Test
//	public void addJobAppliedTest() {
//		jobApplied.setId(1);
//		jobApplied.setUserId(101);
//		jobApplied.setUsername("Avadhoot");
//		jobApplied.setStatus("Pending");
//		jobApplied.setAppliedDate(LocalDate.parse("2007-02-10"));
//		job = jobDAO.getJob(1);
//		jobApplied.setJob(job);
//		
//		Assert.assertEquals(true, jobAppliedDAO.addJobApplied(jobApplied));
//		
//	}
	
//	@Test
//	public void updateJobApplied() {
//	
//		jobApplied = jobAppliedDAO.getJobApplied(1);
//		jobApplied.setStatus("Approved");
//		Assert.assertEquals(true, jobAppliedDAO.updateJobApplied(jobApplied));
//	}
	
//	@Test
//	public void getAllJobsAppliedTestCase() {
//		
//		int size = jobAppliedDAO.list().size();
//		Assert.assertEquals(1, size);
//		
//	}
	
	@Test
	public void deleteJobApplied() {
		
		jobApplied = jobAppliedDAO.getJobApplied(1);
		Assert.assertEquals(true, jobAppliedDAO.deleteJobApplied(jobApplied));
		
	}
}
