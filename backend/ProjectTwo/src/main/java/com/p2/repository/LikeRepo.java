package com.p2.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.p2.model.Likes;
import com.p2.util.MyLoggerFactory;

@Repository("likeRepo")
@Transactional
public class LikeRepo {

	/**
	 * Logger used for LikeRepo transaction logging
	 */
	final static Logger logger = Logger.getLogger(MyLoggerFactory.class);

	/**
	 * SessionFactory field for LikeRepo model
	 */
	private SessionFactory sf;

	/**
	 * No Args Constructor
	 */
	public LikeRepo() {
	}

	/**
	 * All Args Constructor
	 * 
	 * @param sf SessionFactory associated with LikeRepo instance
	 */
	@Autowired
	public LikeRepo(SessionFactory sf) {
		super();
		this.sf = sf;
	}

	/**
	 * Data Access Object layer method that inserts a Likes instance into the
	 * database
	 * 
	 * @param like Likes instance to be created
	 */
	public void insertLike(Likes like) {
		logger.info("Logger || Inserting like " + like + " into the database...");
		sf.getCurrentSession().save(like);
	}

	/**
	 * Data Access Object layer method that pulls a Likes instance from the database
	 * 
	 * @param id ID to determine which Post's Likes instances to read
	 * @return Likes instances retrieved from server-side
	 */
	@SuppressWarnings("unchecked")
	public List<Likes> selectAllPostLikes(int id) {
		logger.info("Logger || Querying list of likes with post id " + id + " from the database...");
		return sf.getCurrentSession().createQuery("from Likes where post_fk=" + id).list();
	}

	/**
	 * Data Access Object layer method that pulls a Likes instance from the database
	 * 
	 * @param id ID to determine which User's Likes instances to read
	 * @return Likes instances retrieved from server-side
	 */
	@SuppressWarnings("unchecked")
	public List<Likes> selectAllUserLikes(int id) {
		logger.info("Logger || Querying list of likes with user id " + id + " from the database...");
		return sf.getCurrentSession().createQuery("from Likes where user_fk=" + id).list();
	}

	/**
	 * Data Access Object layer method that pulls all Likes from the database
	 * 
	 * @return Likes retrieved from server-side
	 */
	public List<Likes> selectAll() {
		logger.info("Logger || Querying list of likes from the database...");
		return sf.getCurrentSession().createQuery("from Likes", Likes.class).list();
	}

	/**
	 * Data Access Object layer method that deletes a Posts instance from the
	 * database
	 * 
	 * @param like Likes instance to be deleted server-side
	 */
	public void deleteLike(Likes like) {
		logger.info("Logger || Deleting like " + like + " from the database...");
		sf.getCurrentSession().delete(like);
	}

	public void h2Init() {
	}

	public void h2Destroy() {
	}
}