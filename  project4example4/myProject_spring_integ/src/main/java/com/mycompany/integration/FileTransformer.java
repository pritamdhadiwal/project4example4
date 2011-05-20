package com.mycompany.integration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.mycompany.dto.Customer;


public class FileTransformer {
	
	private static Logger logger = Logger.getLogger(FileTransformer.class);
	
	public List<Customer> transform(File file) throws IOException{
		
		logger.info("Ttransform ...]");
		List<String> lines = FileUtils.readLines(file);
		lines.remove(0);
		
		List<Customer> customers = new ArrayList<Customer>();
		
		for (String line : lines) {
			String felds[] = StringUtils.split(line, '\t');
			Customer customer = new Customer();
			customer.setCustomerId(Long.parseLong(felds[0]));
			customer.setTaxId(Integer.parseInt(felds[1]));
			customer.setName(felds[2]);
			customer.setAdresse(felds[3]);
			customer.setCity(felds[4]);
			customer.setState(felds[5]);
			customer.setPhone(felds[6]);
			customer.setZip(felds[7]);
			customers.add(customer);
		}
		
		return customers;
	}

	

	

}
