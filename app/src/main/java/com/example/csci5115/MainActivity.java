package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Call when the user taps the Add Item button
    public void addItem(View view){
        Intent intent = new Intent(this, AddItems.class);
        startActivity(intent);
    }

    public void viewRecipes(View view){
        Intent intent = new Intent(this, RecipeFragment.class);
        startActivity(intent);
    }

    public void editItem(View view){
        Intent intent = new Intent(this, EditItems.class);
        startActivity(intent);
    }
}