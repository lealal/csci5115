package com.example.csci5115;

import java.util.List;

public class Recipe {
<<<<<<< HEAD
    private String recipeName, recipeURL, recipeImage;
    private List<String> recipeIngredients;
    private String recipeLink;

    public Recipe(String recipeName, List<String> recipeIngredients, String recipeURL) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeURL = recipeURL;
    }

    public Recipe(String recipeName, List<String> recipeIngredients, String recipeImage, String recipeURL) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeImage = recipeImage;
        this.recipeURL = recipeURL;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<String> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<String> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }
}
