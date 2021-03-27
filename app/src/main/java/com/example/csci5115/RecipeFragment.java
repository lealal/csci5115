package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeFragment extends AppCompatActivity {
    private List<Item> checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_fragment);

        checked = (List<Item>) getIntent().getSerializableExtra("checked");

        TextView toTest = findViewById(R.id.textViewRecipe);

        String toTestString = "";
        for(Item i : checked){
            toTestString += i.getItemName();
        }
        toTest.setText(toTestString);
    }
}