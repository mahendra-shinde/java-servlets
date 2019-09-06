package com.mahendra.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

	public static void main(String[] args) {
		
		Logger log = LogManager.getLogger(Main.class);
			
		log.info("This is info message!");
		log.warn("I warned you!");
		log.fatal("It's now a punishable offense");
		log.debug("Everything looks good!");

	}

}
