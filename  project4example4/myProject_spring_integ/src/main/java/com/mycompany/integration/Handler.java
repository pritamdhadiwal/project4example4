package com.mycompany.integration;

import java.io.File;

import org.apache.log4j.Logger;

public class Handler {

	private static Logger logger = Logger.getLogger(Handler.class);
	
	public File handleFile(File input) {
		logger.info("processing file: " + input.getAbsolutePath());
		return input;
	}
	
}
