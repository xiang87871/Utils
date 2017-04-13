package com;

public class Log4jMain {
	public static void main(String[] args) throws InterruptedException {
//		Logger logger = Logger.getLogger(Log4jMain.class);
//		Logger logger = Logger.getLogger("testConsole");
		
		RuntimeException e = new RuntimeException();
		AawantLogger.WEB_LOGGER.error("hello world", e);
	}
}
