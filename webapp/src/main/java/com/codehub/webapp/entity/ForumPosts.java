package com.codehub.webapp.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="FORUM_POSTS")
public class ForumPosts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 276838396705725922L;
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="FORUM_POST_SEQ", allocationSize = 1)
	@Column(name="POST_ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="Forum_Id")
	private Forum forum;
	
	@Column(name="User_Id")
	private int userId;
	
	@Column(name="User_Name")
	private String username;
	
	@Column(name="Post_Containt")
	private String Description;
	
	@Column(name="Post_Date")
	private LocalDate postDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public LocalDate getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
	}
	
	

}
