package com.cookinglab_recipes.cookinglab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.bulk.BulkWriteResult;
import org.bson.Document;
import io.github.cdimascio.dotenv.Dotenv;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class CookinglabApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookinglabApplication.class, args);

        Dotenv dotenv = Dotenv.load();
        String mongoUser = dotenv.get("MONGO_USER");
        String mongoPassword = dotenv.get("MONGO_PASSWORD");
        String host = dotenv.get("MONGO_HOST");
        String connectionString = "mongodb+srv://" + mongoUser + ":" + mongoPassword + "@" + host;

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("CookingLab");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");

                // Import JSON file into MongoDB
                MongoCollection<Document> coll = database.getCollection("Recipes");

                // Drop previous import
                coll.drop();

                // Get all JSON files from the resources folder
                List<Path> jsonFiles;
                try (Stream<Path> paths = Files.walk(Paths.get("src/main/resources"))) {
                    jsonFiles = paths
                            .filter(Files::isRegularFile)
                            .filter(path -> path.toString().endsWith(".json"))
                            .collect(Collectors.toList());
                }

                // Process each JSON file
                ObjectMapper objectMapper = new ObjectMapper();
                for (Path jsonFile : jsonFiles) {
                    List<Document> documents;
                    try (BufferedReader br = new BufferedReader(new FileReader(jsonFile.toFile()))) {
                        documents = objectMapper.readValue(br, new TypeReference<List<Document>>() {});
                    }

                    // Insert documents in bulk
                    List<InsertOneModel<Document>> docs = new ArrayList<>();
                    for (Document doc : documents) {
                        docs.add(new InsertOneModel<>(doc));
                    }
                    if (!docs.isEmpty()) {
                        BulkWriteResult bulkWriteResult = coll.bulkWrite(docs, new BulkWriteOptions().ordered(false));
                        System.out.println("Inserted " + bulkWriteResult.getInsertedCount() + " documents from " + jsonFile.getFileName());
                    }
                }

            } catch (MongoException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
