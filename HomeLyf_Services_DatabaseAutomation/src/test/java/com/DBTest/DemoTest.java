package com.DBTest;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Database.DbBaseClass;
import com.Database.DbMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DemoTest extends DbBaseClass{

	DbMethods method = new DbMethods();
	public ExtentSparkReporter  sparkReporter;
	public ExtentReports extent;
	public static Logger log = (Logger) LogManager.getLogger(DemoTest.class);
		
	@Test
	public void getSingleusers() {

		DbBaseClass base = new DbBaseClass();
		DbMethods method = new DbMethods();
	    method.find("email", "suhasinivnagur8600@gmail.com");         
        log.info("Get Signle user method executed successfully");
        
	}
	
	@Test
	public void getmultipleusers() {
		String[] emailsToSearch = {"kamal@gmail.com", "nicky111@gmail.com","raiya.doe@gmail.com", "kitty11@gmail.com","kaveri@gmail.com", "sanvi@gmail.com", "mona@gmail.com"};
		method.findAll(emailsToSearch);
		 log.info("Get Multiple Users user method executed successfully");
	}
	
	@Test
	public  void insertmultiUSer() {

		List<Document> documentsToInsert = Arrays.asList(
                new Document("name", "kaveri").append("email", "kaveri@gmail.com").append("password", "Kaveri@123"),
                new Document("name", "sanvi").append("email", "sanvi@gmail.com").append("password", "Sanvi@123"),
                new Document("name", "mona").append("email", "mona@gmail.com").append("password", "Mona@123")
                // Add more users as needed
        );

        method.insertAll(documentsToInsert);
        log.info("Insert Multiple users method execuuted successfully");
    }
	
	@Test
	public void updateMultipedata() {
		List<String> usersToUpdate = Arrays.asList("user1@gmail.com", "user2@gmail.com", "user3@gmail.com");
		method.updateAll(usersToUpdate, "active");
		 log.info("Update Multiple users method execuuted successfully");
	}
	
	
	@Test
	public void deletemultiuser() {
		List<String> usersToDelete = Arrays.asList("kaveri@gmail.com", "sanvi@gmail.com", "mona@gmail.com");
		method.deleteAll(usersToDelete);
		 log.info("Delete Multiple users method execuuted successfully");
	}
	
}
