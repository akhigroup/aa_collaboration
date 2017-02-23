package com.codehub.webapp.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="JOBS_DETAIL")
public class Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1088965074662946119L;

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="JOB_SEQ", allocationSize = 1)
	@Column(name="JOB_ID")
	private int id;
	
	private String profile;
	
	private String Description;
	
	@Column(name="User_Id")
	private int userId;
	
	@Column(name="User_Name")
	private String username;
	
	private String qualification;
	
	@Column(name="Post_Date")
	private LocalDate postDate;
	
	@Column(name="no_Of_Applicants")
	private int noOfApplicants;
	
	@OneToMany(mappedBy="job", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<JobApplied> jobApplied;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public LocalDate getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
	}

	public int getNoOfApplicants() {
		return noOfApplicants;
	}

	public void setNoOfApplicants(int noOfApplicants) {
		this.noOfApplicants = noOfApplicants;
	}

	public List<JobApplied> getJobApplied() {
		return jobApplied;
	}

	public void setJobApplied(List<JobApplied> jobApplied) {
		this.jobApplied = jobApplied;
	}
	
	
	
}
