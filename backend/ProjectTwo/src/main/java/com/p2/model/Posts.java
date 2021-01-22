package com.p2.model;

import java.sql.Timestamp;
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
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Posts")
public class Posts {

	/**
	 * Identification field for Posts model
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_id")
	private int postId;

	/**
	 * Title field for Posts model
	 */
	@Column(name = "title")
	private String title;

	/**
	 * Body field for Posts model
	 */
	@Column(name = "body")
	private String body;

	/**
	 * YouTube Link field for Posts model (optional)
	 */
	@Column(name = "post_image_url")
	private String postImageUrl;

	/**
	 * Post creation field for Posts model
	 */
	@Column(name = "created")
	private Timestamp createdAt;

	/**
	 * User field for Posts model. Many-to-One relationship
	 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk")
	private Users userPost;

	/**
	 * List of Likes field for Posts model. One-to-Many relationship
	 */
	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Likes> likes;

	/**
	 * No Args Constructor
	 */
	public Posts() {
	}

	/**
	 * All Args Constructor (missing the postId field)
	 * 
	 * @param title     Title associated with Post instance
	 * @param body      Body associated with Post instance
	 * @param link      Link associated with Post instance
	 * @param createdAt Date and time associated with Post instance creation
	 * @param userPost  User associated with Post instance
	 * @param likes     Likes associated with Post instance
	 */
	public Posts(String title, String body, String postImageUrl, Timestamp createdAt, Users userPost,
			List<Likes> likes) {
		super();
		this.title = title;
		this.body = body;
		this.postImageUrl = postImageUrl;
		this.createdAt = createdAt;
		this.userPost = userPost;
		this.likes = likes;
	}

	/**
	 * All Args Constructor (missing no fields)
	 * 
	 * @param postId    ID associated with Post instance
	 * @param title     Title associated with Post instance
	 * @param body      Body associated with Post instance
	 * @param link      Link associated with Post instance
	 * @param createdAt Date and time associated with Post instance creation
	 * @param userPost  User associated with Post instance
	 * @param likes     Likes associated with Post instance
	 */
	public Posts(int postId, String title, String body, String postImageUrl, Timestamp createdAt, Users userPost,
			List<Likes> likes) {
		super();
		this.postId = postId;
		this.title = title;
		this.body = body;
		this.postImageUrl = postImageUrl;
		this.createdAt = createdAt;
		this.userPost = userPost;
		this.likes = likes;
	}

	/**
	 * Getter method for Post ID
	 * 
	 * @return Post ID retrieved
	 */
	public int getPostId() {
		return postId;
	}

	/**
	 * Setter method for Post ID
	 * 
	 * @param postId New Post ID
	 */
	public void setPostId(int postId) {
		this.postId = postId;
	}

	/**
	 * Getter method for Post Title
	 * 
	 * @return Title retrieved
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter method for Post Title
	 * 
	 * @param title New Title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter method for Post Body
	 * 
	 * @return Body retrieved
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Setter method for Post Body
	 * 
	 * @param body New Body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Getter method for Post Image Link
	 * 
	 * @return Link retrieved
	 */
	public String getPostImageUrl() {
		return postImageUrl;
	}

	/**
	 * Setter method for Post Image Link
	 * 
	 * @param link New Link
	 */
	public void setPostImageUrl(String postImageUrl) {
		this.postImageUrl = postImageUrl;
	}



	/**
	 * Getter method for Date and Time Created
	 * 
	 * @return Timestamp retrieved
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	/**
	 * Setter method for Date and Time created
	 * 
	 * @param createdAt New Timestamp
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Getter method for Post's User
	 * 
	 * @return User retrieved
	 */
	@JsonIgnore
	public Users getUser() {
		return userPost;
	}

	/**
	 * Setter method for Post's User
	 * 
	 * @param user New User
	 */
	@JsonProperty
	public void setUser(Users user) {
		this.userPost = user;
	}

	/**
	 * Getter method for Post's Likes
	 * 
	 * @return List of Likes retrieved
	 */
	public List<Likes> getLikes() {
		return likes;
	}

	/**
	 * Setter method for Post's Likes
	 * 
	 * @param likes New List of Likes
	 */
	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}

	/**
	 * toString() method of Posts model
	 */
	@Override
	public String toString() {
		return "Posts [postId=" + postId + ", title=" + title + ", body=" + body + ", postImageUrl=" + postImageUrl
				+ ", createdAt=" + createdAt + ", userPost=" + userPost.getUsername() + ", likes=" + likes + "]";
	}
}