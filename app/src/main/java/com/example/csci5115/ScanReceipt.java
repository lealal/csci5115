package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ScanReceipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_receipt);
    }

    public void addScanItems(View view){
        AddItems.addedItems.add("Dragon Fruit");
        AddItems.addedItems.add("Bananas");
        AddItems.addedItems.add("Chicken");
        Intent intent = new Intent(this, AddItems.class);
        startActivity(intent);
    }
}