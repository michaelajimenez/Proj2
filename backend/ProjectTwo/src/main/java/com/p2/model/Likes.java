package com.p2.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.p2.repository.PostRepo;
import com.p2.repository.UserRepo;

@Entity
@Table(name = "Likes")
public class Likes {

	/**
	 * Identification field for Likes model
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "like_id")
	private int likeId;

	/**
	 * User field for Likes model. Many-to-One relationship
	 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk")
	private Users userLiked;

	/**
	 * Post field for Likes model. Many-To-One relationship
	 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "post_fk")
	private Posts post;

	/**
	 * No Args Constructor
	 */
	public Likes() {
	}

	/**
	 * All Args Constructor (missing likeId)
	 * 
	 * @param userLiked User associated with Like instance
	 * @param post      Post associated with Like instance
	 */
	public Likes(Users userLiked, Posts post) {
		super();
		this.userLiked = userLiked;
		this.post = post;
	}

//	public Likes(int userId, int postId) {
//		super();
//		this.userLiked = userRepo.selectById(userId);
//		this.post = postRepo.selectById(postId);
//	}

	/**
	 * All Args Constructor (missing nothing)
	 * 
	 * @param likeId    ID associated with Like instance
	 * @param userLiked User associated with Like instance
	 * @param post      Post associated with Like instance
	 */
	public Likes(int likeId, Users userLiked, Posts post) {
		super();
		this.likeId = likeId;
		this.userLiked = userLiked;
		this.post = post;
	}

	/**
	 * Getter method for Like ID
	 * 
	 * @return Like ID retrieved
	 */
	public int getLikeId() {
		return likeId;
	}

	/**
	 * Setter method for Like ID
	 * 
	 * @param likeId New ID
	 */
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	/**
	 * Getter method for Like's User
	 * 
	 * @return User retrieved
	 */
	@JsonIgnore
	public Users getUserLiked() {
		return userLiked;
	}

	/**
	 * Setter method for Like's User
	 * 
	 * @param userLiked New User
	 */
	@JsonProperty
	public void setUserLiked(Users userLiked) {
		this.userLiked = userLiked;
	}

	/**
	 * Getter method for Like's Post
	 * 
	 * @return Post retrieved
	 */
	@JsonIgnore
	public Posts getPost() {
		return post;
	}

	/**
	 * Setter method for Like's Post
	 * 
	 * @param post New Post
	 */
	@JsonProperty
	public void setPost(Posts post) {
		this.post = post;
	}

	/**
	 * toString() method of Likes model
	 */
	@Override
	public String toString() {
		return "Likes [likeId=" + likeId + ", userLiked=" + userLiked.getUserId() + ", post=" + post.getPostId() + "]";
	}
}