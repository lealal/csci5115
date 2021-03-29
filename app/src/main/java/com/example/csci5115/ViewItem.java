package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewItem extends AppCompatActivity {
    private Item item;
    private TextView viewItem, viewDate;
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        item = (Item) getIntent().getSerializableExtra("Item");
        viewItem = findViewById(R.id.viewItem);
        viewItem.setText(item.getItemName());
        viewDate = findViewById(R.id.viewDate);
        viewDate.setText(item.getItemAddedDate().toString());
        imageView = findViewById(R.id.imageView);
        int id = getResources().getIdentifier(item.getImage(), "drawable", getPackageName());
        Drawable drawable = getResources().getDrawable(id);
        imageView.setImageDrawable(drawable);
        button = findViewById(R.id.recipeButton);
//        button.setOnClickListener(v -> {
//            Intent intent = new Intent(this, RecipeFragment.class);
//            ArrayList<String> checked = new ArrayList<>();
//            checked.add(item.getItemName());
//            intent.putExtra("checked",checked);
//            startActivity(intent);
//        });
    }
}