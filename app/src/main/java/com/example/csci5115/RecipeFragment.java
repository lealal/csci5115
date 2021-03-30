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
    private List<Recipe> recipes;
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private Map<String, List<String>> recipeIngredientDictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_fragment);

        recyclerView = findViewById(R.id.recipeRecyclerView);
        recipes = new ArrayList<>();
        recipeIngredientDictionary = new TreeMap<>();

        adapter = new RecipeAdapter(recipes);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        checked = (List<String>) getIntent().getSerializableExtra("checked");

        createMasterDictionary();
        prepareRecipes();
    }

    private void prepareRecipes() {
        Recipe recipe;

        for (Map.Entry<String, List<String>> entry : recipeIngredientDictionary.entrySet()) {
            String key = entry.getKey();            // The recipe name
            List<String> value = entry.getValue();  // List of items required for the recipe

            // Checked number of ingredients must be greater than the number of ingredients required for the recipe
            if (checked.size() >= value.size()) {

                // If our checked list contains all elements from the recipe's list of ingredients, add it to
                // our recipes list, which gets passed to the RecipeAdapter
                if (checked.containsAll(value)) {
                    recipe = new Recipe(key, value);
                    recipes.add(recipe);
                }
            }
        }
    }

    private void createMasterDictionary() {
        Date date = new Date();

        Item item1 = new Item("Bananas", date, "Pantry");

        Item item2 = new Item("Beef", date, "Freezer");

        Item item3 = new Item("Orange", date, "Pantry", "orange");

        List<String> itemList = new ArrayList<>(Arrays.asList(item1.getItemName(), item2.getItemName(), item3.getItemName()));

        recipeIngredientDictionary.put("BananasBeefOrange", itemList);

        Item item4 = new Item("Eggs", date, "Fridge");

        Item item5 = new Item("Milk", date, "Fridge");

        Item item6 = new Item("Yogurt", date, "Fridge");

        Item item7 = new Item("Spinach", date, "Fridge");

        itemList = new ArrayList<>(Arrays.asList(item1.getItemName(), item4.getItemName(), item5.getItemName()));

        recipeIngredientDictionary.put("BananaEggsMilk", itemList);

        itemList = new ArrayList<>(Arrays.asList(item2.getItemName(), item4.getItemName(), item6.getItemName()));

        recipeIngredientDictionary.put("BeefEggsYogurt", itemList);

        itemList = new ArrayList<>(Arrays.asList(item1.getItemName(), item2.getItemName(), item6.getItemName(), item7.getItemName()));

        recipeIngredientDictionary.put("BananasBeefYogurtSpinach", itemList);

        itemList = new ArrayList<>(Arrays.asList(item1.getItemName(), item2.getItemName(), item6.getItemName()));

        recipeIngredientDictionary.put("BananasBeefYogurt", itemList);

        Item WILDCARD = new Item("Avocado", date, "Fridge");

        itemList = new ArrayList<>(Arrays.asList(item1.getItemName(), WILDCARD.getItemName()));

        // The guacamole recipe doesn't show up because avocado is an item which doesn't exist in our fridge
        recipeIngredientDictionary.put("Guacamole", itemList);

        itemList = new ArrayList<>(Arrays.asList(item1.getItemName(), item3.getItemName()));

        recipeIngredientDictionary.put("BananasOrange", itemList);

        // JUST BEEF for a recipe - to check what happens in the freezer tab
        itemList = new ArrayList<>(Arrays.asList(item2.getItemName()));
        recipeIngredientDictionary.put("BEEF", itemList);
    }
}