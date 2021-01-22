package com.p2.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MyLoggerFactory {

	final static Logger logger = Logger.getLogger(MyLoggerFactory.class);

	{
		logger.setLevel(Level.ALL);
	}
}