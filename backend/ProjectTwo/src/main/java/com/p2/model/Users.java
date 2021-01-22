package com.p2.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "Users")
public class Users {

	/**
	 * Identification field for Users model
	 */
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	/**
	 * Username field for Users model
	 */
	@Column(name = "username", unique = true, nullable = false)
	private String username;

	/**
	 * Password field for Users model
	 */
	@Column(name = "password", nullable = false)
	private String password;

	/**
	 * Full name field for Users model
	 */
	@Column(name = "full_name", nullable = false)
	private String fullName;

	/**
	 * Email field for Users model
	 */
	@Column(name = "email", unique = true, nullable = false)
	private String email;

	/**
	 * Account creation field for Users model
	 */
	@Column(name = "created_at", nullable = false)
	private Timestamp created;

	/**
	 * Profile Picture field for Users model
	 */
	@Column(name = "image_url")
	private String imageUrl;

	/**
	 * List of Posts field for Users model. One-to-Many relationship
	 */
	@OneToMany(mappedBy = "userPost", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Posts> posts;

	/**
	 * List of Likes field for Users model. One-to-Many relationship
	 */
	@OneToMany(mappedBy = "userLiked", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Likes> likes;

	/**
	 * No Args Constructor
	 */
	public Users() {
	}

	/**
	 * All Args Constructor (missing the posts, likes fields)
	 * 
	 * @param userId   ID associated with User instance
	 * @param username Username associated with User instance
	 * @param password Password associated with User instance
	 * @param fullName Full Name associated with User instance
	 * @param email    Email associated with User instance
	 * @param created  Date and time associated with User instance creation
	 */
	public Users(int userId, String username, String password, String fullName, String email, Timestamp created) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.created = created;
		this.posts = new ArrayList<>();
		this.likes = new ArrayList<>();
	}

	/**
	 * All Args Constructor (missing the userId field)
	 * 
	 * @param username Username associated with User instance
	 * @param password Password associated with User instance
	 * @param fullName Full name associated with User instance
	 * @param email    Email associated with User instance
	 * @param created  Date and time associated with User instance creation
	 * @param imageUrl Profile Picture URL associated with User instance
	 * @param posts    Posts associated with User instance
	 * @param likes    Likes associated with User instance
	 */
	public Users(String username, String password, String fullName, String email, Timestamp created, String imageUrl,
			List<Posts> posts, List<Likes> likes) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.created = created;
		this.imageUrl = imageUrl;
		this.posts = posts;
		this.likes = likes;
	}

	/**
	 * All Args Constructor (missing nothing)
	 * 
	 * @param userId   ID associated with User instance
	 * @param username Username associated with User instance
	 * @param password Password associated with User instance
	 * @param fullName Full Name associated with User instance
	 * @param email    Email associated with User instance
	 * @param created  Date and time associated with User instance creation
	 * @param imageUrl Profile Picture URL associated with User instance
	 * @param posts    Posts associated with User instance
	 * @param likes    Likes associated with User instance
	 */
	public Users(int userId, String username, String password, String fullName, String email, Timestamp created,
			String imageUrl, List<Posts> posts, List<Likes> likes) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.created = created;
		this.imageUrl = imageUrl;
		this.posts = posts;
		this.likes = likes;
	}

	/**
	 * Getter method for User ID
	 * 
	 * @return User ID retrieved
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Setter method for User ID
	 * 
	 * @param userId New User ID
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Getter method for Username
	 * 
	 * @return Username retrieved
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter method for Username
	 * 
	 * @param username New Username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter method for Password
	 * 
	 * @return Password retrieved
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter method for Password
	 * 
	 * @param password New Password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter method for Full Name
	 * 
	 * @return Full Name retrieved
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Setter method for Full Name
	 * 
	 * @param fullName New Full Name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Getter method for Email
	 * 
	 * @return Email retrieved
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter method for Email
	 * 
	 * @param email New Email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter method for Date and Time Created
	 * 
	 * @return Timestamp retrieved
	 */
	public Timestamp getCreated() {
		return created;
	}

	/**
	 * Setter method for Date and Time Created
	 * 
	 * @param created New Timestamp
	 */
	public void setCreated(Timestamp created) {
		this.created = created;
	}

	/**
	 * Getter method for User Posts
	 * 
	 * @return Posts retrieved
	 */
	public List<Posts> getPosts() {
		return posts;
	}

	/**
	 * Setter method for User Posts
	 * 
	 * @param posts New Posts
	 */
	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}

	/**
	 * Getter method for User Likes
	 * 
	 * @return Likes retrieved
	 */
	public List<Likes> getLikes() {
		return likes;
	}

	/**
	 * Setter method for User Likes
	 * 
	 * @param likes New Likes
	 */
	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}

	/**
	 * Getter method for User Profile Picture link
	 * 
	 * @return URL retrieved
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Setter method for User Profile Picture link
	 * 
	 * @param imageUrl New URL
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * toString() method of Users model
	 */
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", password=" + password + ", fullName="
				+ fullName + ", email=" + email + ", created=" + created + ", imageUrl=" + imageUrl + ", posts=" + posts
				+ ", likes=" + likes + "]";
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}