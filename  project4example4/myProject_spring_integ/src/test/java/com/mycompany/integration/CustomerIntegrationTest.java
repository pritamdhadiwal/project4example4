package com.mycompany.integration;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.dao.ICustomerDao;
import com.mycompany.entity.Customer;

@ContextConfiguration(locations = { "/application-context.xml", "/order-integ-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerIntegrationTest {

	private static final String FLAT_XML_DATASET = "FlatXmlDataSet.xml";
	private static Logger logger = LoggerFactory.getLogger(CustomerIntegrationTest.class);
	
	@Value("${customer.file.input.path}")
	private String inputDirectoryPath;
	
	@Autowired
    @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("customerDao")
	private ICustomerDao iCustomerDao;

	@Before
	public void setUp() throws Exception {		
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
	}
	
	@Test
	public void testIntegrateCustomer() throws Exception {
		
		Collection<Customer> listCustomers = iCustomerDao.getAll();
		logger.info("customers number : {}", listCustomers.size());
		
		logger.info("wait .... ");
		Thread.sleep(2000);
		
		URL defaultImage = this.getClass().getResource("/input/customer.txt");
		File testFile = new File(defaultImage.toURI());
		Assert.assertTrue(testFile.exists());
		
		
		FileUtils.copyFileToDirectory(testFile, new File(inputDirectoryPath));
		logger.info("test file {} coped to {}", testFile.getName(), inputDirectoryPath);
		
		Thread.sleep(4000);
		
		
		listCustomers = iCustomerDao.getAll();
		logger.info("new customers number : {}", listCustomers.size());
		
		
	}

	@SuppressWarnings("deprecation")
	private IDataSet getDataSet() throws Exception {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FLAT_XML_DATASET);
		IDataSet dataset = new FlatXmlDataSet(inputStream);
		return dataset;
	}

	private IDatabaseConnection getConnection() throws Exception {
		Connection jdbcConnection = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
		return connection;
	}
	
	@After
	public void tearDown () throws IOException {
		FileUtils.deleteDirectory(new File(inputDirectoryPath));
	}
	
}
