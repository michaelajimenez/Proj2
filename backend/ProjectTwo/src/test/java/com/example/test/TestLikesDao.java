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
import com.p2.repository.LikeRepo;
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
public class TestLikesDao {

	final static Logger logger = Logger.getLogger(MyLoggerFactory.class);

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private LikeRepo likeRepo;

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
	public void testLikesSetMethods() {
		logger.info("Logger || In Likes Set Methods Test");

		Users user = new Users(9001, "testuser", "testpass", "Michael Jimenez", "testuser@gmail.com",
				Timestamp.valueOf(LocalDateTime.now()));
		Users otherUser = new Users();
		Posts post = new Posts(9001, "Test Post", "Body of the post", null, null, user, null);
		Posts otherPost = new Posts();
		
		Likes like = new Likes(9001, user, post);
		
		like.setLikeId(9002);
		like.setPost(otherPost);
		like.setUserLiked(otherUser);
		
		assertEquals(9002, like.getLikeId());
		assertEquals(otherUser, like.getUserLiked());
		assertEquals(otherPost, like.getPost());
	}
	
	@Test
	@Rollback(true)
	public void testAddLike() {
		logger.info("Logger || In Likes Add to Database Test");

		Users user = new Users(1, "testuser", "testpass", "Michael Jimenez", "testuser@gmail.com",
				Timestamp.valueOf(LocalDateTime.now()));
		Posts post = new Posts(1, "Test Post", "Body of the post", null, null, user, null);
		Likes like = new Likes(1, user, post);
		
		userRepo.insertUser(user);
		postRepo.insertPost(post);
		likeRepo.insertLike(like);
		
		List<Likes> likes = likeRepo.selectAll();
		assertEquals(like.getLikeId(), likes.get(0).getLikeId());
	}
	
	@Test
	@Rollback(true)
	public void testSelectUsersLikes() {
		logger.info("Logger || In Likes Select By User Test");

		Users user = new Users(2, "testuser2", "testpass2", "Michael Jimenez", "testuser2@gmail.com",
				Timestamp.valueOf(LocalDateTime.now()));
		Posts post = new Posts(2, "Test Post", "Body of the post", null, null, user, null);
		Likes like = new Likes(2, null, null);
		
		userRepo.insertUser(user);
		postRepo.insertPost(post);
		
		like.setPost(post);
		like.setUserLiked(user);
		likeRepo.insertLike(like);
		
		assertEquals(like.getLikeId(), likeRepo.selectAllUserLikes(user.getUserId()).get(0).getLikeId());
	}
	
	@Test
	@Rollback(true)
	public void testSelectLikePost() {
		logger.info("Logger || In Likes Select By Post Test");

		Users user = new Users(3, "testuser3", "testpass3", "Michael Jimenez", "testuser3@gmail.com",
				Timestamp.valueOf(LocalDateTime.now()));
		Posts post = new Posts(3, "Test Post", "Body of the post", null, null, user, null);
		Likes like = new Likes(3, null, null);
		
		userRepo.insertUser(user);
		postRepo.insertPost(post);
		
		like.setPost(post);
		like.setUserLiked(user);
		likeRepo.insertLike(like);
		
		assertEquals(like.getLikeId(), likeRepo.selectAllPostLikes(post.getPostId()).get(0).getLikeId());
	}
}
