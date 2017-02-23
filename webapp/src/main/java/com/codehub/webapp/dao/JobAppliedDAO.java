package com.codehub.webapp.dao;

import java.util.List;

import com.codehub.webapp.entity.JobApplied;

public interface JobAppliedDAO {
	
	List<JobApplied> list();
	JobApplied getJobApplied(int id);
	boolean addJobApplied(JobApplied jobApplied);
	boolean updateJobApplied(JobApplied jobApplied);
	boolean deleteJobApplied(JobApplied jobApplied);

}
