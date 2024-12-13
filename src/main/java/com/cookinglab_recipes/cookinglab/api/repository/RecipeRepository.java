package com.cookinglab_recipes.cookinglab.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cookinglab_recipes.cookinglab.api.model.RecipeModel;

public interface RecipeRepository extends MongoRepository<RecipeModel, String> {
}
