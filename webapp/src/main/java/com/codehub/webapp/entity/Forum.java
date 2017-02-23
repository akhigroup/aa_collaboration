package com.codehub.webapp.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.engine.spi.CascadeStyles;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Forum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6191895330548160363L;

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="FORUM_SEQ", allocationSize = 1)
	@Column(name="FORUM_ID")
	private int id;
	
	@Column(name="Forum_Name")
	private String name;
	
	@Column(name="Forum_Containt")
	private String description;
	
	@Column(name="Date_Created")
	private LocalDate dateCreated;
	
	@Column(name="Number_Of_Posts")
	private int noOfPosts;
	
	@Column(name="Number_Of_Members")
	private int noOfmembers;
	
	@Column(name="NUMBER_OF_REQUEST")
	private int noOfrequests;
	
	private String status;
	
	@Column(name="User_Id")
	private int userId;
	
	@Column(name="User_Name")
	private String username;

	@ManyToOne
	@JoinColumn(name= "CATEGORY_ID")
	private ForumCategory forumCategory;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="forum", fetch=FetchType.EAGER)
	private List<ForumPosts> forumPosts;
	
	@OneToMany(mappedBy="forum", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ForumRequest> forumRequest;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getNoOfPosts() {
		return noOfPosts;
	}

	public void setNoOfPosts(int noOfPosts) {
		this.noOfPosts = noOfPosts;
	}

	public int getNoOfmembers() {
		return noOfmembers;
	}

	public void setNoOfmembers(int noOfmembers) {
		this.noOfmembers = noOfmembers;
	}

	public int getNoOfrequests() {
		return noOfrequests;
	}

	public void setNoOfrequests(int noOfrequests) {
		this.noOfrequests = noOfrequests;
	}

	public ForumCategory getForumCategory() {
		return forumCategory;
	}

	public void setForumCategory(ForumCategory forumCategory) {
		this.forumCategory = forumCategory;
	}

	public List<ForumPosts> getForumPosts() {
		return forumPosts;
	}

	public void setForumPosts(List<ForumPosts> forumPosts) {
		this.forumPosts = forumPosts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	
	
}
