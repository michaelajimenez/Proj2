package com.example.test;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.p2.model.Likes;
import com.p2.model.Posts;
import com.p2.model.Users;
import com.p2.repository.UserRepo;
import com.p2.util.MyLoggerFactory;

@ContextConfiguration
(locations = {
	"classpath:applicationContext.xml", 
	"*/src/main/webapp/WEB-INF/dispatcher-servlet.xml", 
	"*/src/main/webapp/WEB-INF/web.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUsersDao {

	final static Logger logger = Logger.getLogger(MyLoggerFactory.class);

	@Autowired
	private UserRepo userRepo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("-----BEFORE CLASS-----");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("-----AFTER CLASS-----");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("-----BEFORE TEST CASE-----");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("-----AFTER TEST CASE-----");
	}
	
	@Test
	public void testUsersSetMethods() {
		logger.info("Logger || In Users Set Methods Test");

		Users user = new Users(9001, "testuser", "testpass", "Michael Jimenez", "testuser@gmail.com",
				Timestamp.valueOf(LocalDateTime.now()));
		
		user.setUserId(9002);
		user.setUsername("newuser");
		user.setPassword("newpass");
		user.setEmail("newemail@gmail.com");
		
		assertEquals(9002, user.getUserId());
		assertEquals("newuser", user.getUsername());
		assertEquals("newpass", user.getPassword());
		assertEquals("newemail@gmail.com", user.getEmail());
	}
	
	@Test
	@Rollback(true)
	public void testAddUser() {
		logger.info("Logger || In Users Add to Database Test");

		Users user = new Users(1, "testuser", "testpass", "Michael Jimenez", "testuser@gmail.com",
				Timestamp.valueOf(LocalDateTime.now()));

		userRepo.insertUser(user);
		List<Users> users = userRepo.selectAll();
		
		assertEquals(user.getUserId(), users.get(0).getUserId());
	}
	
	@Test
	@Rollback(true)
	public void testUpdateUser() {
		logger.info("Logger || In Users Update in Database Test");

		Users user = new Users(2, "testuser2", "testpass2", "Michael Jimenez", "testuser2@gmail.com",
				Timestamp.valueOf(LocalDateTime.now()));

		userRepo.insertUser(user);
		userRepo.selectById(2).setFullName("Test User");
		userRepo.updateUser(user);
		
		List<Users> users = userRepo.selectAll();
		assertEquals(user.getFullName(), users.get(1).getFullName());
	}
	
	@Test
	@Rollback(true)
	public void testSelectUserById() {
		logger.info("Logger || In Users Select By Id Test");

		Users user = new Users(3, "testuser3", "testpass3", "Michael Jimenez", "testuser3@gmail.com",
				Timestamp.valueOf(LocalDateTime.now()));
		
		userRepo.insertUser(user);
		
		assertEquals(user.getFullName(), userRepo.selectById(3).getFullName());
	}
}