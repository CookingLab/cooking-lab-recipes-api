package com.cookinglab_recipes.cookinglab.api.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Recipes")
public class RecipeModel {
    @Id
    private int id;
    private String label;
    private String image;
    private String url;
    private List<String> ingredientLines;
    private List<String> cusineType;
    private List<String> dietLabels;
    private List<String> mealType;
    private List<String> healthLabels;

    // Constructor
    public RecipeModel() {
    }
    
    public RecipeModel (int id, String label, String image, String url, List<String> ingredientLines, List<String> cusineType, List<String> dietLabels, List<String> mealType, List<String> healthLabels) {
        this.id = id;
        this.label = label;
        this.image = image;
        this.url = url;
        this.cusineType = cusineType;
        this.dietLabels = dietLabels;
        this.mealType = mealType;
        this.healthLabels = healthLabels;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    
    public List<String> getCusineType() {
        return cusineType;
    }
    
    public void setCusineType (List<String> cusineType) {
         this.cusineType = cusineType;
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
                ", cusineType=" + cusineType +
                ", dietLabels=" + dietLabels +
                ", mealType=" + mealType +
                ", healthLabels=" + healthLabels +
                '}';
    }
}
