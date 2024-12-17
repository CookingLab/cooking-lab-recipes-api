package com.cookinglab_recipes.cookinglab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cookinglab_recipes.cookinglab.api.service.MongoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class CookinglabApplication implements CommandLineRunner {

    @Autowired
    private MongoService mongoService;

    public static void main(String[] args) {
        SpringApplication.run(CookinglabApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        mongoService.importJsonFiles();
    }
}
