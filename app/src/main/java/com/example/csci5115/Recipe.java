package com.example.csci5115;

import java.util.List;

public class Recipe {
    private String recipeName;
    private List<Item> recipeIngredients;

    public Recipe(String recipeName, List<Item> recipeIngredients) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
    }

    public Recipe(String recipeName, List<Item> recipeIngredients, String recipeImage) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeImage = recipeImage;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<Item> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<Item> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    private String recipeImage;
}
