package com.Database;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import com.mongodb.client.MongoClient;

public class MongoDBToExcelExample {
	public static void main(String[] args) {
        // MongoDB Cloud Database connection string
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

        // Fetch data from MongoDB
        Iterator<Document> iterator = collection.find().iterator();

        // Create Excel workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("MongoDB Data");

        // Add headers to Excel sheet
        Row headerRow = sheet.createRow(0);
      //  headerRow.createCell(0).setCellValue("_id");
        headerRow.createCell(1).setCellValue("name");
        headerRow.createCell(2).setCellValue("email");
        headerRow.createCell(3).setCellValue("password");
        // Add more headers as needed

        // Add data to Excel sheet
        int rowIndex = 1;
        while (iterator.hasNext()) {
            Document document = iterator.next();
            Row dataRow = sheet.createRow(rowIndex++);
          //  dataRow.createCell(0).setCellValue(document.getString("_id"));
            dataRow.createCell(1).setCellValue(document.getString("name"));
            dataRow.createCell(2).setCellValue(document.getString("email"));
            dataRow.createCell(3).setCellValue(document.getString("password"));
            // Add more fields as needed
        }

        // Write Excel workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir")+ "//Database_data/excel.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Excel file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close MongoDB client and workbook
            mongoClient.close();
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
