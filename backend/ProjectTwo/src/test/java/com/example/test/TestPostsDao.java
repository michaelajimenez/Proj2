package com.example.test;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import com.p2.repository.PostRepo;
import com.p2.repository.UserRepo;
import com.p2.util.MyLoggerFactory;

@ContextConfiguration
(locations = {
	"classpath:applicationContext.xml", 
	"*/src/main/webapp/WEB-INF/dispatcher-servlet.xml", 
	"*/src/main/webapp/WEB-INF/web.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPostsDao {

	final static Logger logger = Logger.getLogger(MyLoggerFactory.class);

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostRepo postRepo;
	
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
	public void testPostsSetMethods() {
		logger.info("Logger || In Posts Set Methods Test");
		
		Users user = new Users(9001, "testuser", "testpass", "Michael Jimenez", "testuser@gmail.com",
				Timestamp.valueOf(LocalDateTime.now()));
		Users otherUser = new Users();
		Posts post = new Posts(9001, "Test Post", "Body of the post", null, null, user, null);
		
		post.setTitle("New Title");
		post.setBody("New Body");
		post.setUser(otherUser);
		
		assertEquals("New Title", post.getTitle());
		assertEquals("New Body", post.getBody());
		assertEquals(otherUser, post.getUser());
	}
	
	@Test
	@Rollback(true)
	public void testAddPost() {
		logger.info("Logger || In Posts Add to Database Test");

		Users user = new Users(1, "testuser", "testpass", "Michael Jimenez", "testuser@gmail.com",
				Timestamp.valueOf(LocalDateTime.now()));
		Posts post = new Posts(1, "Test Post", "Body of the post", null, null, user, null);
		
		userRepo.insertUser(user);
		postRepo.insertPost(post);
		
		List<Posts> posts = postRepo.selectAll();
		assertEquals(post.getPostId(), posts.get(0).getPostId());		
	}
	
	@Test
	@Rollback(true)
	public void testSelectPostById() {
		logger.info("Logger || In Posts Select By Post Id Test");
		
		Users user = new Users(2, "testuser2", "testpass2", "Michael Jimenez", "testuser2@gmail.com",
				Timestamp.valueOf(LocalDateTime.now()));
		Posts post = new Posts(2, "Test Post", "Body of the post", null, null, user, null);
		
		userRepo.insertUser(user);
		postRepo.insertPost(post);
		
		assertEquals(post.getBody(), postRepo.selectById(post.getPostId()).getBody());
	}
	
	@Test
	@Rollback(true)
	public void testSelectPostByUserId() {
		logger.info("Logger || In Posts Select By User Id Test");

		Users user = new Users(3, "testuser3", "testpass3", "Michael Jimenez", "testuser3@gmail.com",
				Timestamp.valueOf(LocalDateTime.now()));
		List<Likes> likes = new ArrayList<Likes>();
		Posts post = new Posts(3, "Test Post", "Body of the post", "youtube.com", Timestamp.valueOf(LocalDateTime.now()), user, likes);
		user.getPosts().add(post);
		
		userRepo.insertUser(user);
		postRepo.insertPost(post);
		
		assertEquals(post.getBody(), postRepo.selectAllUserPosts(user.getUserId()).get(0).getBody());
	}
}