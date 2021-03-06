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
    private Item itemFromViewClass;
    private List<Recipe> filteredRecipes;
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;

    // Our main recipe-ingredient dictionary, which we'll use to generate recipes for checked items/individual
    // items from the ViewItem class
    private Map<Recipe, List<String>> recipeIngredientDictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_fragment);
        setTitle("Recipes");

        recyclerView = findViewById(R.id.recipeRecyclerView);
        filteredRecipes = new ArrayList<>();
        recipeIngredientDictionary = new TreeMap<>();

        adapter = new RecipeAdapter(filteredRecipes);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        if (getIntent().getExtras() != null) {
            checked = (List<String>) getIntent().getSerializableExtra("checked");
            itemFromViewClass = (Item) getIntent().getSerializableExtra("Item");
        }

        createMasterDictionary();
        // If the checked list is null, that means we're getting an individual item from the
        // ViewItem class; else, it is the list of checked ingredients from the fridge
        prepareRecipes(checked != null);
    }

    private void prepareRecipes(boolean filter) {
        for (Map.Entry<Recipe, List<String>> entry : recipeIngredientDictionary.entrySet()) {
            // The recipe name
            Recipe key = entry.getKey();
            // List of items required for the recipe
            List<String> value = entry.getValue();

            // Checked number of ingredients must be greater than the number of ingredients required for the recipe
            if (filter) {
                if (checked.size() >= value.size()) {

                    // If our checked list contains all elements from the recipe's list of ingredients, add it to
                    // our recipes list, which gets passed to the RecipeAdapter
                    if (checked.containsAll(value)) {
                        filteredRecipes.add(key);
                    }
                }
            } else {
                // This is the case where checked is null
                // We're adding all possible recipes where this individual item (itemFromViewClass) can be used
                if (value.contains(itemFromViewClass.getItemName())) {
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

        itemList = new ArrayList<>(Arrays.asList("Onions", "Beef"));
        recipe = new Recipe("Mongolian Beef and Spring Onions", itemList, "mongolian_beef", "https://www.allrecipes.com/recipe/201849/mongolian-beef-and-spring-onions/");
        recipeIngredientDictionary.put(recipe, itemList);

        itemList = new ArrayList<>(Arrays.asList("Onions", "Beef", "Tomato Sauce", "Noodles"));
        recipe = new Recipe("Skillet Lasagna", itemList, "skillet_lasagna", "https://www.allrecipes.com/recipe/273775/skillet-lasagna/");
        recipeIngredientDictionary.put(recipe, itemList);

        itemList = new ArrayList<>(Arrays.asList("Onions", "Green Peas", "Rice"));
        recipe = new Recipe("Jasmine Rice", itemList, "jasmine_rice", "https://www.allrecipes.com/recipe/14136/jasmine-rice/");
        recipeIngredientDictionary.put(recipe, itemList);

        itemList = new ArrayList<>(Arrays.asList("Lemon", "Chicken", "Butter"));
        recipe = new Recipe("Lemon Chicken", itemList, "lemon_chicken", "https://www.allrecipes.com/recipe/280307/lemon-thyme-chicken-breasts/");
        recipeIngredientDictionary.put(recipe, itemList);

        itemList = new ArrayList<>(Arrays.asList("Beef", "Bread", "Cheese"));
        recipe = new Recipe("Cheeseburger Sliders", itemList, "cb_slider", "https://www.allrecipes.com/recipe/277748/cheeseburger-sliders/");
        recipeIngredientDictionary.put(recipe, itemList);

        itemList = new ArrayList<>(Arrays.asList("Yogurt", "Parsley", "Soy Milk"));
        recipe = new Recipe("Vegan Ranch", itemList, "vegan_ranch", "https://www.allrecipes.com/recipe/261742/vegan-ranch-dressing/");
        recipeIngredientDictionary.put(recipe, itemList);

        itemList = new ArrayList<>(Arrays.asList("Rice", "Beans", "Tomatoes"));
        recipe = new Recipe("Vegan Enchilada", itemList, "vegan_enchilada", "https://www.allrecipes.com/recipe/263660/vegan-enchilada-bake/");
        recipeIngredientDictionary.put(recipe, itemList);
    }
}