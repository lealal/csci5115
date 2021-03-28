package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeFragment extends AppCompatActivity {
    private List<Item> checked;
    private List<Recipe> recipes;
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_fragment);

        recyclerView = findViewById(R.id.recipeRecyclerView);
        recipes = new ArrayList<>();

        adapter = new RecipeAdapter(recipes);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        checked = (List<Item>) getIntent().getSerializableExtra("checked");

        prepareRecipes();
    }

    private void prepareRecipes() {
        Recipe recipe = new Recipe("Recipe 1", checked);
        recipes.add(recipe);

        recipe = new Recipe("Recipe 2", checked);
        recipes.add(recipe);
    }
}