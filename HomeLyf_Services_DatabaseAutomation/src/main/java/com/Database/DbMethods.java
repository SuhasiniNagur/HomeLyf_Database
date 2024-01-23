package com.Database;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DbMethods  extends DbBaseClass {


	MongoCollection<Document> collection = database.getCollection("users");

	public void selectDB() {
		
		  // Select the database
	    database = mongoClient.getDatabase("test");

	    // Select the collection
	    collection = database.getCollection("users");
	}
	public void findAll(String[] emailValues) {

//		List<String> emailValues = Arrays.asList("kamal@gmail.com", "john@gmail.com", "jane@gmail.com");
//        Document emailQuery = new Document("email", new Document("$in", emailValues));
//        FindIterable<Document> matchingDocuments = collection.find(emailQuery);
//
//        for (Document document : matchingDocuments) {
//            System.out.println(document.toJson());
//        }
		
		    List<String> emailList = Arrays.asList(emailValues);
		    Document emailQuery = new Document("email", new Document("$in", emailList));
		    FindIterable<Document> matchingDocuments = collection.find(emailQuery);

		    for (Document document : matchingDocuments) {
		        System.out.println(document.toJson());
		    }
		
	}

	public void find(String email, String emailaddress) {
	    // Find documents
		Document query = new Document(email, emailaddress);
		FindIterable<Document> documents = collection.find(query);

		for (Document document : documents) {
		    System.out.println(document.toJson());
		}
	    
	
	}

	public void insertAll(List<Document> documentsToInsert) {

//		List<Document> documentsToInsert = Arrays.asList(
//                new Document("name", "user1").append("email", "user1@gmail.com").append("password", "password1"),
//                new Document("name", "user2").append("email", "user2@gmail.com").append("password", "password2"),
//                new Document("name", "user3").append("email", "user3@gmail.com").append("password", "password3")
//                // Add more users as needed
//        );
//
//        collection.insertMany(documentsToInsert);
//        System.out.println("Multiple documents inserted successfully.");
		
	        if (documentsToInsert != null && !documentsToInsert.isEmpty()) {
	            collection.insertMany(documentsToInsert);
	            System.out.println("Multiple documents inserted successfully.");
	        } else {
	            System.out.println("No documents to insert.");
	        }
	    
	}

	public void insert(String key, String value) {

		 // Insert document
	    Document documentToInsert = new Document(key, value).append(key, value).append(key, value);
	    collection.insertOne(documentToInsert);
	    System.out.println("Document inserted successfully.");
	}

	public void updateAll(List<String> userEmails, String newStatus) {

		 // Update multiple documents
//        List<String> usersToUpdate = Arrays.asList("user1@gmail.com", "user2@gmail.com", "user3@gmail.com");
//        for (String userEmail : usersToUpdate) {
//            Document filter = new Document("email", userEmail);
//            Document update = new Document("$set", new Document("status", "active"));
//            collection.updateOne(filter, update);
//        }
//        System.out.println("Multiple documents updated successfully.");
		
		for (String userEmail : userEmails) {
	        Document filter = new Document("email", userEmail);
	        Document update = new Document("$set", new Document("status", newStatus));
	        collection.updateOne(filter, update);
	    }
	    System.out.println("Multiple documents updated successfully.");

	}

	public void update() {

		// Update document
	    Document filter = new Document("key", "value");
	    Document update = new Document("$set", new Document("key", "updatedValue"));
	    collection.updateOne(filter, update);
	    System.out.println("Document updated successfully.");
	}

	public void deleteAll(List<String> userEmails) {

//		 // Delete multiple documents
//        List<String> usersToDelete = Arrays.asList("user1@gmail.com", "user2@gmail.com", "user3@gmail.com");
//        for (String userEmail : usersToDelete) {
//            Document documentToDelete = new Document("email", userEmail);
//            collection.deleteOne(documentToDelete);
//        }
//        System.out.println("Multiple documents deleted successfully.");

		for (String userEmail : userEmails) {
	        Document documentToDelete = new Document("email", userEmail);
	        collection.deleteOne(documentToDelete);
	    }
	    System.out.println("Multiple documents deleted successfully.");
	}

	public void delete() {

		// Delete document
	    Document documentToDelete = new Document("key", "updatedValue");
	    collection.deleteOne(documentToDelete);
	    System.out.println("Document deleted successfully.");
	}


}