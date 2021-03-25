package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewItem extends AppCompatActivity {
    private Item item;
    private TextView viewItem, viewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        item = (Item) getIntent().getSerializableExtra("Item");
        viewItem = findViewById(R.id.viewItem);
        viewItem.setText(item.getItemName());
        viewDate = findViewById(R.id.viewDate);
        viewDate.setText(item.getItemAddedDate().toString());
    }
}