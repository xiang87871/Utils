package com;

import org.apache.log4j.Logger;

public class AawantLogger {
	
	/**
	 * data_web的logger
	 */
	public static final Logger DATA_WEB_LOGGER = Logger.getLogger("data_web_logger");

	/**
	 * dialog的logger
	 */
	public static final Logger DIALOG_LOGGER = Logger.getLogger("dialog_logger");

	/**
	 * web 的logger
	 */
	public static final Logger WEB_LOGGER = Logger.getLogger("web_logger");

	/**
	 * 智核的logger
	 */
	public static final Logger CORE_LOGGER = Logger.getLogger("intellect_core_logger");
	
	/**
	 * qtj的logger
	 */
	public static final Logger QTJ_LOGGER = Logger.getLogger("qtj_logger");
	
	public static void main(String[] args) {
		QTJ_LOGGER.error("message");
	}
}
