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

import com.p2.model.Users;
import com.p2.util.MyLoggerFactory;

@Repository("userRepo")
@Transactional
public class UserRepo {

	/**
	 * Logger used for UserRepo transaction logging
	 */
	final static Logger logger = Logger.getLogger(MyLoggerFactory.class);

	private static String url = "jdbc:h2:./h2Folder/h2Data";
	private static String username = "sa";
	private static String password = "sa";

	/**
	 * SessionFactory field for UserRepo model
	 */
	private SessionFactory sf;

	/**
	 * No Args Constructor
	 */
	public UserRepo() {
	}

	/**
	 * All Args Constructor
	 * 
	 * @param sf SessionFactory associated with UserRepo instance
	 */
	@Autowired
	public UserRepo(SessionFactory sf) {
		super();
		this.sf = sf;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		UserRepo.url = url;
	}

	/**
	 * Data Access Object layer method that inserts a Users instance into the
	 * database
	 * 
	 * @param user Users instance to be created server-side
	 */
	public void insertUser(Users user) {
		logger.info("Logger || Inserting user " + user + " into the database...");
		sf.getCurrentSession().save(user);
	}

	/**
	 * Data Access Object layer method that pulls a Users instance from the database
	 * 
	 * @param id ID to determine which Users instance to read
	 * @return Users instance retrieved from server-side
	 */
	public Users selectById(int id) {
		// System.out.println(this.url + "url!!");
		logger.info("Logger || Querying user with user id " + id + " from the database...");
		return sf.getCurrentSession().get(Users.class, id);
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Users selectByUsernamePassword(String username, String password) {
		return (Users) sf.getCurrentSession().createQuery("from Users where username=:u and password=:p")
				.setParameter("u", username).setParameter("p", password).uniqueResult();
	}

	/**
	 * Data Access Object layer method that pulls all Users instances from the
	 * database
	 * 
	 * @return Users instances retrieved from server-side
	 */
	public List<Users> selectAll() {
		logger.info("Logger || Querying list of users from the database...");
		return sf.getCurrentSession().createQuery("from Users", Users.class).list();
	}

	/**
	 * Data Access Object layer method that updates a Users instance in the database
	 * 
	 * @param user Users instance updated from server-side
	 */
	public void updateUser(Users user) {
		logger.info("Logger || Updating user " + user + " in the database...");
		sf.getCurrentSession().update(user);
	}

	/**
	 * Initialization method for H2 Database
	 */
	public void h2Init() {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "" + "CREATE TABLE users( " + "user_id INT4 NOT NULL, "
					+ "username VARCHAR(255) UNIQUE NOT NULL, " + "password VARCHAR(255) NOT NULL, "
					+ "full_name VARCHAR(255) NOT NULL, " + "email VARCHAR(255) UNIQUE NOT NULL, "
					+ "created_at TIMESTAMP NOT NULL, " + "image_url VARCHAR(255) NULL, " + "PRIMARY KEY (user_id)"
					+ ");"
					+ "INSERT INTO users VALUES(1, 'tempuser', 'temppass', 'TempName', 'burneremail@gmail.com', '1970-01-12 08:46:40', null);";
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
			String sql = "" + "DROP TABLE users; ";

			Statement state = conn.createStatement();
			state.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}