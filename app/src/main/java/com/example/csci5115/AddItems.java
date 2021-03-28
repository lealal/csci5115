package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
    }

    public void scanReceipt(View view){
        Intent intent = new Intent(this, ScanReceipt.class);
        startActivity(intent);
    }

    public void itemCategories(View view){
        Intent intent = new Intent(this, ItemCategories.class);
        startActivity(intent);
    }
}