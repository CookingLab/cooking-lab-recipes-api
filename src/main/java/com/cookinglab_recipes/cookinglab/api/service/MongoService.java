package com.cookinglab_recipes.cookinglab.api.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

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

@Service
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void importJsonFiles() {
        try {
            MongoDatabase database = mongoTemplate.getDb();
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

        } catch (MongoException | IOException e) {
            e.printStackTrace();
        }
    }
}
