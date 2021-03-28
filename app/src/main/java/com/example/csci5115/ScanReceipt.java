package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Date;

public class ScanReceipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_receipt);
    }

    public void addScanItems(View view){
        Date date = new Date();
        Item item = new Item("Dragon Fruit", date, "Fridge");
        AddItems.addedItems.add(item);

        item = new Item("Bananas", date, "Fridge");
        AddItems.addedItems.add(item);

        item = new Item("Chicken", date, "Fridge");
        AddItems.addedItems.add(item);
        Intent intent = new Intent(this, AddItems.class);
        startActivity(intent);
    }
}