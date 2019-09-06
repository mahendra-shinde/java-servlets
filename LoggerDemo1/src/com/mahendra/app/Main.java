package com.mahendra.app;

import java.util.logging.*;

public class Main {

	public static void main(String[] args) {
		Logger log = Logger.getLogger("Main");
			
		log.info("This is info message!");
		log.warning("I warned you!");
		log.severe("It's now a punishable offense");
		log.fine("Everything is all-right!");

	}

}
