package com.cookinglab_recipes.cookinglab.api.controller;

import com.cookinglab_recipes.cookinglab.api.model.RecipeModel;
import com.cookinglab_recipes.cookinglab.api.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<?> getAllRecipes() {
        try {
            List<RecipeModel> allRecipes = recipeService.getAllRecipes();

            if (allRecipes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No recipes available.");
            }

            return ResponseEntity.ok(allRecipes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the request.");
        }
    }
}
