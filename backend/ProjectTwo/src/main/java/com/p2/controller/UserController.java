package com.p2.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.p2.model.Users;
import com.p2.repository.UserRepo;
import com.p2.service.EmailService;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

	/**
	 * An object of type UserRepo, this object holds the Data Access Object
	 * operations for the Users model
	 */
	private UserRepo userRepo;

	
	@Autowired
	private EmailService emailServ;
	
	/**
	 * No Args Constructor
	 */
	public UserController() {
	}

	/**
	 * All Args Constructor
	 * 
	 * @param ur The controller's instance of DAO Implementation of Users model
	 */
	@Autowired
	public UserController(UserRepo ur) {
		this.userRepo = ur;
	}

	/**
	 * Calls the Repo layer READ all method of CRUD implementation
	 * 
	 * @return List of all Users for client-side
	 */
	@GetMapping(value = "/getAllUsers")
	@ResponseBody
	public List<Users> getAllUsers() {
		return userRepo.selectAll();
	}

	/**
	 * Calls the Repo layer READ method of CRUD implementation from a given ID
	 * 
	 * @param id ID to determine the User instance to retrieve
	 * @return User instance retrieved for client-side
	 */
	@PostMapping(value = "/getUserById")
	@ResponseBody
	public Users getUserById(@RequestParam("id") int id) {
		return userRepo.selectById(id);
	}

	/**
	 * Calls the Repo layer CREATE method of CRUD implementation
	 * 
	 * @param newUser User from client-side to be inserted into the database
	 * @return String to inform the client that a User was added
	 */
	@PostMapping(value = "/createUser")
	@ResponseBody
	public String createUser(@RequestBody Users newUser) {		
		String encrypted = base64Encrypt(newUser.getPassword());
		System.out.println(encrypted);
		System.out.println(base64Decrypt(encrypted));
		newUser.setPassword(encrypted);
		newUser.setCreated(Timestamp.valueOf(LocalDateTime.now()));

		userRepo.insertUser(newUser);
		System.out.println("Inserted user");
		return "Added user";
	}

	/**
	 * Calls the Repo layer UPDATE method of CRUD implementation
	 * 
	 * @param ses          Session to check if used is logged in
	 * @param modifiedUser User from client-side to be updated in the database
	 * @return String to inform the client that a User was updated
	 */
	@PostMapping(value = "/editUser")
	@ResponseBody
	public String modifyUser(HttpSession ses, @RequestBody Users modifiedUser) {
		//Users loggedUser = (Users) ses.getAttribute("loggedIn");
		
		Users loggedUser = userRepo.selectById(modifiedUser.getUserId());
		
		System.out.println("BEFORE-------------------------------");
		System.out.println("Session user:\n" + loggedUser);
		System.out.println("JSON brought back: \n" + modifiedUser);

		if (!modifiedUser.getUsername().equals("") && !loggedUser.getUsername().equals(modifiedUser.getUsername())) {
			System.out.println("Username changed");
			loggedUser.setUsername(modifiedUser.getUsername());
		}
		if (!modifiedUser.getPassword().equals("") && !loggedUser.getPassword().equals(modifiedUser.getPassword())) {
			System.out.println("Password changed");
			loggedUser.setPassword(modifiedUser.getPassword());
		}
		if (!modifiedUser.getFullName().equals("") && !loggedUser.getFullName().equals(modifiedUser.getFullName())) {
			System.out.println("Full name changed");
			loggedUser.setFullName(modifiedUser.getFullName());
		}
		if (!modifiedUser.getEmail().equals("") && !loggedUser.getEmail().equals(modifiedUser.getEmail())) {
			System.out.println("Email changed");
			loggedUser.setEmail(modifiedUser.getEmail());
		}
		if (!modifiedUser.getImageUrl().equals("") && !loggedUser.getImageUrl().equals(modifiedUser.getImageUrl())) {
			System.out.println("Profile picture changed");
			loggedUser.setImageUrl(modifiedUser.getImageUrl());
		}
		System.out.println("\n\nAFTER-------------------------------");
		System.out.println("Session user:\n" + loggedUser);

		userRepo.updateUser(loggedUser);
		return "Modified user";
	}

	/**
	 * Retrieves the session attribute corresponding to the logged in User (getter
	 * method)
	 * 
	 * @param ses HttpSession that holds logged in User information
	 * @return User that is currently logged in client-side
	 */
	@GetMapping(value = "/getUser")
	@ResponseBody
	public Users getLoggedUser(HttpSession ses) {
		System.out.println("Getting logged in user");
		Users cur = (Users) ses.getAttribute("loggedIn");
		System.out.println(cur);
		return cur;
	}

	/**
	 * Establishes the session attribute corresponding to the logged in User (setter
	 * method)
	 * 
	 * @param ses HttpSession that holds logged in User information
	 * @param cur User to now be currently logged in client-side
	 * @return String to inform the client that a User was logged in
	 */
	@PostMapping(value = "/login")
	@ResponseBody
	public Users login(HttpSession ses, @RequestBody Users cur) {
		System.out.println("Logging in...");
		Users validate = validateLogin(cur);
		if (validate == null) {
			System.out.println("Invalid login, wrong credentials");
			System.out.println(cur.getUsername());
			System.out.println(cur.getPassword());
			return validate;
		} else {
			ses.setAttribute("loggedIn", validate);
			System.out.println("Username: " + validate.getUsername());
			System.out.println("Password: " + validate.getPassword());
			return validate;
		}

	}

	public Users validateLogin(Users validating) {
		String username = validating.getUsername();
		String password = validating.getPassword();
		System.out.println("Username from frontend: " + username);
		System.out.println("Password from frontend: " + password);
		password = base64Encrypt(password);

		Users validated = userRepo.selectByUsernamePassword(username, password);
		System.out.println(validated);
		return validated;
	}

	/**
	 * Invalidates the session corresponding to the logged in User (cleanup method)
	 * 
	 * @param ses HttpSession that will be invalidated
	 * @return String to inform the client that a Used was logged out
	 */
	@GetMapping(value = "/logout")
	@ResponseBody
	public String logout(HttpSession ses) {
		System.out.println("Logging out...");
		ses.invalidate();
		return "Succesfully logged out";
	}
	
	public String base64Encrypt(String str) {
		byte[] bytesEncoded = Base64.encodeBase64(str.getBytes());
		System.out.println("Encoded value is " + new String(bytesEncoded));
		return new String(bytesEncoded);
	}
	
	public String base64Decrypt(String str) {
		byte[] bytesDecoded = Base64.decodeBase64(str.getBytes());
		System.out.println("Decoded value is " + new String(bytesDecoded));
		return new String(bytesDecoded);
	}

	@GetMapping(value="/resetPassword")
    @ResponseBody
    public String resetPassword(@RequestBody Users lostUser, HttpServletRequest req) {
        
        System.out.println(lostUser);
        String to = lostUser.getEmail();
        System.out.println("Email: "+to);
        System.out.println("Servlet Scheme: " + req.getScheme());
        System.out.println("Server Name: " + req.getServerName());
        
        emailServ.sendMail("diegoaviles1998@gmail.com", "Test Subject", "Testing body");
        
        return "Reset password";
    }	
}