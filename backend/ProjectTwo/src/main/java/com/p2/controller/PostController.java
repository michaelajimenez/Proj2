package com.p2.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.p2.model.Posts;
import com.p2.model.Users;
import com.p2.repository.PostRepo;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PostController {

	/**
	 * An object of type PostRepo, this object holds the Data Access Object
	 * operations for the Posts model
	 */
	private PostRepo postRepo;

	/**
	 * No Args Constructor
	 */
	public PostController() {
	}

	/**
	 * All Args Constructor
	 * 
	 * @param postRepo The controller's instance of DAO Implementation of Posts
	 *                 model
	 */
	@Autowired
	public PostController(PostRepo postRepo) {
		this.postRepo = postRepo;
	}

	/**
	 * Calls the Repo layer READ all method of CRUD implementation
	 * 
	 * @return List of all Posts for client-side
	 */
	@GetMapping(value = "/getAllPosts")
	@ResponseBody
	public List<Posts> getAllPosts() {
		return postRepo.selectAll();
	}

	/**
	 * Calls the Repo layer READ all method of CRUD implementation from a given User
	 * instance
	 * 
	 * @param id ID to determine the User instance to be compared against
	 *           client-side IDs
	 * @return List of all the found User's Likes for client-side
	 */
	@PostMapping(value = "/getAllUserPosts")
	@ResponseBody
	public List<Posts> getAllUserPosts(@RequestParam("id") int id) {
		return postRepo.selectAllUserPosts(id);
	}

	/**
	 * Calls the Repo layer READ method of CRUD implementation from a given ID
	 * 
	 * @param id ID to determine the Post instance to retrieve
	 * @return Post instance retrieved for client-side
	 */
	@PostMapping(value = "/getPostById")
	@ResponseBody
	public Posts getPostById(@RequestParam("id") int id) {
		return postRepo.selectById(id);
	}

	/**
	 * Calls the Repo layer CREATE method of CRUD implementation
	 * 
	 * @param newPost Post from client-side to be inserted into the database
	 * @param ses     Session to validate web login
	 * @return Verification message
	 */
	@PostMapping("/createPost")
	public String createPost(@RequestBody Posts newPost, HttpSession ses) {
		Users user = (Users) ses.getAttribute("loggedIn");
		// System.out.println("\nCreate Post user: \n" + user);
		newPost.setUser(user);
		// System.out.println("\nSetting post with session user: \n" + newPost);
		// System.out.println(newPost.getUser());
		postRepo.insertPost(newPost);
		return "Added post";
	}

	/**
	 * Calls the Repo layer DELETE method of CRUD implementation
	 * 
	 * @param deletePost Post instance to be compared against server-side for
	 *                   deletion
	 */
	@GetMapping("/deletePost")
	public String deletePost(@RequestBody Posts deletePost) {
		postRepo.deletePost(deletePost);
		return "Deleted post";
	}
}