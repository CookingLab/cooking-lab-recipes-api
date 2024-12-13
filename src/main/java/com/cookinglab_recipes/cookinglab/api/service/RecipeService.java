package com.cookinglab_recipes.cookinglab.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookinglab_recipes.cookinglab.api.model.RecipeModel;
import com.cookinglab_recipes.cookinglab.api.repository.RecipeRepository;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public void saveRecipes(List<RecipeModel> recipes) {
        recipeRepository.saveAll(recipes);
    }
}
