package com.codehub.webapp.entity;

import java.io.Serializable;
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
@Table(name="FORUM_CATEGORY")
public class ForumCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2264478909075896498L;
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="forum_category_seq", allocationSize = 1)
	@Column(name="CATEGORY_ID")
	private int id;
	
	@Column(name="Category_Name")
	private String name;
	
	private String Description;
	
	private String status;
	
	@OneToMany(mappedBy="forumCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Forum> forum;
	
	@Column(name="No_Of_Topics")
	private int noOfTopics;
	
	@Column(name="No_Of_Posts")
	private int noOfPosts;

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
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Forum> getForum() {
		return forum;
	}

	public void setForum(List<Forum> forum) {
		this.forum = forum;
	}

	public int getNoOfTopics() {
		return noOfTopics;
	}

	public void setNoOfTopics(int noOfTopics) {
		this.noOfTopics = noOfTopics;
	}

	public int getNoOfPosts() {
		return noOfPosts;
	}

	public void setNoOfPosts(int noOfPosts) {
		this.noOfPosts = noOfPosts;
	}
	
	
	
}
