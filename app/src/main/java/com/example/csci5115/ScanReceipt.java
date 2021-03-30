package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ScanReceipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_receipt);
    }

    public void addScanItems(View view){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String date = df.format(c);
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