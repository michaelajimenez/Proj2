package com.p2.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.p2.model.Posts;
import com.p2.util.MyLoggerFactory;

@Repository("postRepo")
@Transactional
public class PostRepo {

	/**
	 * Logger used for PostRepo transaction logging
	 */
	final static Logger logger = Logger.getLogger(MyLoggerFactory.class);

	private static String url = "jdbc:h2:./h2Folder/h2Data";
	private static String username = "sa";
	private static String password = "sa";

	/**
	 * SessionFactory field for PostRepo model
	 */
	private SessionFactory sf;

	/**
	 * No Args Constructor
	 */
	public PostRepo() {
	}

	/**
	 * All Args Constructor
	 * 
	 * @param sf SessionFactory associated with PostRepo instance
	 */
	@Autowired
	public PostRepo(SessionFactory sf) {
		super();
		this.sf = sf;
	}

	/**
	 * Data Access Object layer method that inserts a Posts instance into the
	 * database
	 * 
	 * @param post Posts instance to be created server-side
	 */
	public void insertPost(Posts post) {
		logger.info("Logger || Inserting post " + post + " into the database...");
		sf.getCurrentSession().save(post);
	}

	/**
	 * Data Access Object layer method that pulls a Posts instance from the database
	 * 
	 * @param id ID to determine which Posts instance to read
	 * @return Posts instance retrieved from server-side
	 */
	public Posts selectById(int id) {
		logger.info("Logger || Querying post with post id " + id + " from the database...");
		return sf.getCurrentSession().get(Posts.class, id);
	}

	/**
	 * Data Access Object layer method that pulls all Posts instances associated
	 * with a User from the database
	 * 
	 * @param id ID to determine which User's Posts instances to read
	 * @return Posts instances retrieved from server-side
	 */
	@SuppressWarnings("unchecked")
	public List<Posts> selectAllUserPosts(int id) {
		logger.info("Logger || Querying list of posts with user id " + id + " from the database...");
		return sf.getCurrentSession().createQuery("from Posts where user_fk=" + id).list();
	}

	/**
	 * Data Access Object layer method that pulls all Posts from the database
	 * 
	 * @return Posts retrieved from server-side
	 */
	public List<Posts> selectAll() {
		logger.info("Logger || Querying list of posts from the database...");
		return sf.getCurrentSession().createQuery("from Posts", Posts.class).list();
	}

	/**
	 * Data Access Object layer method that deletes a Posts instance from the
	 * database
	 * 
	 * @param post Posts instance to be deleted server-side
	 */
	public void deletePost(Posts post) {
		logger.info("Logger || Deleting post " + post + " from the database...");
		sf.getCurrentSession().delete(post);
	}

	/**
	 * Initialization method for H2 Database
	 */
	public void h2Init() {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "" + "CREATE TABLE posts (" + " post_id int4 NOT NULL," + " body varchar(255) NULL,"
					+ "	created timestamp NULL," + " yt_link varchar(255) NULL," + " title varchar(255) NULL,"
					+ "	user_fk int4 NULL," + "	CONSTRAINT posts_pkey PRIMARY KEY (post_id),"
					+ "	CONSTRAINT fk5374c6f2t6vw1uoin257m145w FOREIGN KEY (user_fk) REFERENCES users(user_id)" + ");"
					+ "INSERT INTO posts VALUES(1, 'Temp Body', '1970-01-12 08:46:40', 'templink.com', 'Temp Title', null);";
			System.out.println(sql);
			Statement state = conn.createStatement();
			state.execute(sql);
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	/**
	 * Tear down method for H2 Database
	 */
	public void h2Destroy() {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "" + "DROP TABLE posts; ";

			Statement state = conn.createStatement();
			state.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}