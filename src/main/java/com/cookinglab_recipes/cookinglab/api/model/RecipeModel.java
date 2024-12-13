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

    // Constructor
    public RecipeModel() {
    }
    
    public RecipeModel(int id, String label, String image, String url, List<String> ingredientLines) {
        this.id = id;
        this.label = label;
        this.image = image;
        this.url = url;
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

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                ", ingredients=" + ingredientLines +
                '}';
    }
}
