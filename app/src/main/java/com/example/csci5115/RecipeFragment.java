package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RecipeFragment extends AppCompatActivity {
    private List<String> checked;
    private List<Recipe> filteredRecipes;
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private Map<Recipe, List<String>> recipeIngredientDictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_fragment);

        recyclerView = findViewById(R.id.recipeRecyclerView);
        filteredRecipes = new ArrayList<>();
        recipeIngredientDictionary = new TreeMap<>();

        adapter = new RecipeAdapter(filteredRecipes);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        checked = (List<String>) getIntent().getSerializableExtra("checked");

        createMasterDictionary();
        prepareRecipes();
    }

    private void prepareRecipes() {
        for (Map.Entry<Recipe, List<String>> entry : recipeIngredientDictionary.entrySet()) {
            Recipe key = entry.getKey();            // The recipe name
            List<String> value = entry.getValue();  // List of items required for the recipe

            // Checked number of ingredients must be greater than the number of ingredients required for the recipe
            if (checked.size() >= value.size()) {

                // If our checked list contains all elements from the recipe's list of ingredients, add it to
                // our recipes list, which gets passed to the RecipeAdapter
                if (checked.containsAll(value)) {
                    filteredRecipes.add(key);
                }
            }
        }
    }

    private void createMasterDictionary() {
        List<String> itemList = new ArrayList<>(Arrays.asList("Cheese", "Bread", "Butter"));
        Recipe recipe = new Recipe("Grilled Cheese", itemList, "grilled_cheese", "https://www.allrecipes.com/recipe/23891/grilled-cheese-sandwich/");
        recipeIngredientDictionary.put(recipe, itemList);

        itemList = new ArrayList<>(Arrays.asList("Pasta", "Tomato sauce", "Beef"));
        recipe = new Recipe("Pasta and meat sauce", itemList, "pasta_meat", "https://www.allrecipes.com/recipe/19343/marius-spaghetti-with-meat-sauce/");
        recipeIngredientDictionary.put(recipe, itemList);

        itemList = new ArrayList<>(Arrays.asList("Potato"));
        recipe = new Recipe("French fries", itemList, "french_fries", "https://www.allrecipes.com/recipe/35963/french-fried-potatoes/");
        recipeIngredientDictionary.put(recipe, itemList);

        itemList = new ArrayList<>(Arrays.asList("Eggs", "Yogurt", "Butter"));
        recipe = new Recipe("Yogurt Cake", itemList, "yogurt_cake", "https://www.allrecipes.com/recipe/17476/yogurt-cake/");
        recipeIngredientDictionary.put(recipe, itemList);

        itemList = new ArrayList<>(Arrays.asList("Green onions", "Beef"));
        recipe = new Recipe("Mongolian Beef and Spring Onions", itemList, "mongolian_beef", "https://www.allrecipes.com/recipe/201849/mongolian-beef-and-spring-onions/");
        recipeIngredientDictionary.put(recipe, itemList);
    }
}