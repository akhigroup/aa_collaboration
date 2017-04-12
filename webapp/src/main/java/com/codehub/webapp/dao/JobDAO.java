package com.codehub.webapp.dao;

import java.util.List;

import com.codehub.webapp.entity.Job;

public interface JobDAO {

	List<Job> list();
	List<Job> mainList();
	List<Job> list(String status);
	List<Job> getJobsByStatus(String status);
	List<Job> getUserJobs(int id);
	Job getJob(int id);
	boolean addJob(Job job);
	boolean updateJob(Job job);
	boolean deleteJob(Job job);
}
