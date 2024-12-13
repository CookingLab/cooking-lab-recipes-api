package com.cookinglab_recipes.cookinglab.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cookinglab_recipes.cookinglab.api.model.RecipeModel;
import com.cookinglab_recipes.cookinglab.api.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/import")
    public String importRecipes(@RequestParam("file") MultipartFile file) {
        try {
            String content = new String(file.getBytes());
            List<RecipeModel> recipes = Arrays.asList(new ObjectMapper().readValue(content, RecipeModel[].class));
            recipeService.saveRecipes(recipes);
            return "Recipes imported successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to import recipes.";
        }
    }
    
    @GetMapping("/import-from-resources")
    public String importRecipesFromResources() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Load the file from the resources folder
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("recipes.json");
            if (inputStream != null) {
                List<RecipeModel> recipes = objectMapper.readValue(inputStream, new TypeReference<List<RecipeModel>>() {});
                recipeService.saveRecipes(recipes);
                return "Recipes imported successfully from resources!";
            } else {
                throw new IOException("recipes.json not found in resources.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to import recipes from resources: " + e.getMessage();
        }
    }
}