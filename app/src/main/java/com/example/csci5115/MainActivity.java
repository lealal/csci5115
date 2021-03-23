package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter iAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        iAdapter = new ItemAdapter(itemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(iAdapter);

        prepareItemData();
    }

    private void prepareItemData(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate= formatter.format(date);
        System.out.println(strDate);
        Item item = new Item("Bananas", date, "Fridge");
        itemList.add(item);

        item = new Item("Beef", date, "Fridge");
        itemList.add(item);

        item = new Item("Orange", date, "Fridge");
        itemList.add(item);

        item = new Item("Beef", date, "Fridge");
        itemList.add(item);

        item = new Item("Orange", date, "Fridge");
        itemList.add(item);

        item = new Item("Beef", date, "Fridge");
        itemList.add(item);

        item = new Item("Orange", date, "Fridge");
        itemList.add(item);

        item = new Item("Beef", date, "Fridge");
        itemList.add(item);

        item = new Item("Orange", date, "Fridge");
        itemList.add(item);

        item = new Item("Orange", date, "Fridge");
        itemList.add(item);

        item = new Item("Beef", date, "Fridge");
        itemList.add(item);

        item = new Item("Orange", date, "Fridge");
        itemList.add(item);

        item = new Item("Orange", date, "Fridge");
        itemList.add(item);

        item = new Item("Beef", date, "Fridge");
        itemList.add(item);

        item = new Item("Orange", date, "Fridge");
        itemList.add(item);
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

    public void viewItem(View view){
        Intent intent = new Intent(this, ViewItem.class);
        startActivity(intent);
    }
}