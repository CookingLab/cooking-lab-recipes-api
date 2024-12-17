package com.cookinglab_recipes.cookinglab.api.service;

import com.cookinglab_recipes.cookinglab.api.model.RecipeModel;
import com.cookinglab_recipes.cookinglab.api.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<RecipeModel> getAllRecipes() {
        return recipeRepository.findAll();
    }
}