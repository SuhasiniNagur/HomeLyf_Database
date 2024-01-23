package com.Database;
import org.bson.Document;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class DbBaseClass {
	
		String connectionString = "mongodb+srv://amitpotdukhe20:cmPUrefrPzB0BIuW@homelyfcluster0.4fzxngg.mongodb.net/?retryWrites=true&w=majority";

        // Connect to MongoDB Cloud Database
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build()
        );

        // Select the database
        MongoDatabase database = mongoClient.getDatabase("test");

        // Select the collection
        MongoCollection<Document> collection = database.getCollection("users");

	
	@AfterClass
	public void closeDbConnection() {
      
                mongoClient.close();
                System.out.println("MongoDB client closed successfully.");

    }
}