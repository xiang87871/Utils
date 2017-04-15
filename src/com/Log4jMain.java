package com;

import org.apache.log4j.Logger;

public class Log4jMain {
	public static void main(String[] args) throws InterruptedException {
		Logger logger = Logger.getLogger(Log4jMain.class);
		RuntimeException e = new RuntimeException();
		while(true) {
			Thread.sleep(1000);
			logger.error("hello world", e);
		}
	}
}
