package com.p2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.p2.model.Likes;
import com.p2.repository.LikeRepo;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LikeController {

	/**
	 * An object of type LikeRepo, this object holds the Data Access Object
	 * operations for the Likes model
	 */
	private LikeRepo likeRepo;

	/**
	 * No Args Constructor
	 */
	public LikeController() {
	}

	/**
	 * All Args Constructor
	 * 
	 * @param LikeRepo The controller's instance of DAO Implementation of Likes
	 *                 model
	 */
	@Autowired
	public LikeController(LikeRepo LikeRepo) {
		this.likeRepo = LikeRepo;
	}

	/**
	 * Calls the Repo layer READ all method of CRUD implementation
	 * 
	 * @return List of all Likes for client-side
	 */
	@GetMapping(value = "/getAllLikes")
	@ResponseBody
	public List<Likes> getAllLikes() {
		return likeRepo.selectAll();
	}

	/**
	 * Calls the Repo layer READ all method of CRUD implementation from a given User
	 * instance
	 * 
	 * @param id ID to determine the User instance to be compared against
	 *           client-side IDs
	 * @return List of all the found User's Likes for client-side
	 */
	@GetMapping(value = "/getAllUserLikes")
	@ResponseBody
	public List<Likes> getAllUserLikes(@RequestParam("id") int id) {
		return likeRepo.selectAllUserLikes(id);
	}

	/**
	 * Calls the Repo layer READ all method of CRUD implementation from given a Post
	 * instance
	 * 
	 * @param id ID to determine the Post instance to be compared against
	 *           client-side IDs
	 * @return List of all the found Post's Likes for client-side
	 */
	public List<Likes> getAllPostLikes(@RequestParam("id") int id) {
		return likeRepo.selectAllPostLikes(id);
	}

	/**
	 * Calls the Repo layer CREATE method of CRUD implementation
	 * 
	 * @param newLike Like from client-side to be inserted into the database
	 */
	@GetMapping("/createLike")
    public void createLike(@RequestBody Likes newLike) {
        likeRepo.insertLike(newLike);
    }

	/**
	 * Calls the Repo layer DELETE method of CRUD implementation
	 * 
	 * @param deleteLike Like instance to be compared against server-side for
	 *                   deletion
	 */
	@GetMapping("/deleteLike")
	public void deleteLike(@RequestBody Likes deleteLike) {
		likeRepo.deleteLike(deleteLike);
	}
}