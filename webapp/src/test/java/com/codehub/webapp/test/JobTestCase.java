package com.codehub.webapp.test;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.codehub.webapp.dao.JobAppliedDAO;
import com.codehub.webapp.dao.JobDAO;
import com.codehub.webapp.entity.Job;
import com.codehub.webapp.entity.JobApplied;

import junit.framework.Assert;

public class JobTestCase {
	
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
	
	public JobTestCase() {
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
//	public void addJobTest() {
//		
//		job.setId(1);
//		job.setDescription("This is a job");
//		job.setNoOfApplicants(50);
//		job.setPostDate(LocalDate.parse("2007-02-10"));
//		job.setProfile("Student");
//		job.setQualification("B.Sc");
//		job.setUserId(101);
//		job.setUsername("Avadhoot");
//		
//		Assert.assertEquals(true, jobDAO.addJob(job));
//	}
//	
//	@Test
//	public void updateJob() {
//		job = jobDAO.getJob(1);
//		job.setNoOfApplicants(100);
//		
//		Assert.assertEquals(true, jobDAO.updateJob(job));
//	}
	
//	@Test
//	public void getAllJobsTestCase() {
//		int size = jobDAO.list().size();
//		
//		Assert.assertEquals(1, size);
//		
//	}
	
	@Test
	public void deleteJob() {
		job = jobDAO.getJob(1);
		
		Assert.assertEquals(true, jobDAO.deleteJob(job));
	}

}
