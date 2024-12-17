package com.cookinglab_recipes.cookinglab.api.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Recipes")
public class RecipeModel {
    @Id
    private String id;
    private String label;
    private String image;
    private String url;
    private List<String> ingredientLines;
    private List<String> cuisineType;
    private List<String> dietLabels;
    private List<String> mealType;
    private List<String> healthLabels;

    // Constructor
    public RecipeModel() {
    }
    
    public RecipeModel (String id, String label, String image, String url, List<String> ingredientLines, List<String> cuisineType, List<String> dietLabels, List<String> mealType, List<String> healthLabels) {
        this.id = id;
        this.label = label;
        this.image = image;
        this.url = url;
        this.cuisineType = cuisineType;
        this.dietLabels = dietLabels;
        this.mealType = mealType;
        this.healthLabels = healthLabels;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredients(List<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getImage() {
        return image;
    }
    
    public List<String> getCuisineType() {
        return cuisineType;
    }
    
    public void setCuisineType (List<String> cuisineType) {
         this.cuisineType = cuisineType;
    }
    
    public List<String> getDietLabels() {
        return dietLabels;
    }
    
    public void setDietLabels (List<String> dietLabels) {
         this.dietLabels = dietLabels;
    }
    
    public List<String> getMealType() {
        return mealType;
    }
    
    public void setMealType (List<String> mealType) {
         this.mealType = mealType;
    }
    
    public List<String> getHealthLabels() {
        return healthLabels;
    }
    
    public void setHealthLabels (List<String> healthLabels) {
         this.healthLabels = healthLabels;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                ", ingredients=" + ingredientLines +
                ", cuisineType=" + cuisineType +
                ", dietLabels=" + dietLabels +
                ", mealType=" + mealType +
                ", healthLabels=" + healthLabels +
                '}';
    }
}
