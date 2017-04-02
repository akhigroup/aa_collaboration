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
	
	@Test
	public void addJobTest() {
		
		job.setId(1);
		job.setCompanyName("JP Morgan");
		job.setSubTitle("Post for programmer");
		job.setAbout("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
				+ "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis "
				+ "natoque penatibus et magnis dis parturient montes, nascetur "
				+ "ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, "
				+ "pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, "
				+ "fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, "
				+ "imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. "
				+ "Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate "
				+ "eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, "
				+ "enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus "
				+ "viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam "
				+ "ultricies nisi vel augue. Curabitur ullamcorper ultricies");
		job.setJobProfile("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. "
				+ "Aenean massa. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. "
				+ "Aenean massa.");
		job.setQualification("Xth : 90% + XIIth with science: 75% Graduated in Computer Engineering with 60%+ 2 years of experience at least");
		job.setContactInfo("Send your resume to following address: Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet, "
				+ "Contact Number : 9898989898 email id : xynz@gmail.com");
		job.setPostDate(LocalDate.parse("2007-02-10"));
		job.setUserId(101);
		job.setUsername("Avadhoot");
		
		Assert.assertEquals(true, jobDAO.addJob(job));
	}
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
	
//	@Test
//	public void deleteJob() {
//		job = jobDAO.getJob(1);
//		
//		Assert.assertEquals(true, jobDAO.deleteJob(job));
//	}

}
