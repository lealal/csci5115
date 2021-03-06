package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ItemCategories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_categories);
        setTitle("Categories");
    }

    public void categoryFruit(View view){
        Intent intent = new Intent(this, CategoryFruit.class);
        startActivity(intent);
    }

    public void categoryVegetable(View view){
        Intent intent = new Intent(this, CategoryVegetable.class);
        startActivity(intent);
    }

    public void categoryMeat(View view){
        Intent intent = new Intent(this, CategoryMeat.class);
        startActivity(intent);
    }
}