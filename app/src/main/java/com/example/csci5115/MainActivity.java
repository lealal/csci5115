package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface {
    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter iAdapter;
    private TabLayout tabLayout;

    @Override
    public void onListItemClick(int position) {
        // This is where we will create intents and pass the itemList.get(position) object onto the next activity
        Item item = itemList.get(position);
        Intent intent = new Intent(this, ViewItem.class);
        intent.putExtra("Item", item);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        iAdapter = new ItemAdapter(itemList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(iAdapter);

        prepareItemData();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if (pos == 0) {
                    // all
                    iAdapter.setItemList(itemList);
                    recyclerView.setAdapter(iAdapter);
                } else if (pos == 1) {
                    // pantry
                    List<Item> pantList = new ArrayList<Item>();
                    for (Item i : itemList) {
                        if (i.getLocation().equals("Pantry")) {
                            pantList.add(i);
                        }
                    }
                    iAdapter.setItemList(pantList);
                    recyclerView.setAdapter(iAdapter);
                } else if (pos == 2) {
                    // fridge
                    List<Item> fridList = new ArrayList<Item>();
                    for (Item i : itemList) {
                        if (i.getLocation().equals("Fridge")) {
                            fridList.add(i);
                        }
                    }
                    iAdapter.setItemList(fridList);
                    recyclerView.setAdapter(iAdapter);
                } else if (pos == 3) {
                    // freezer
                    List<Item> freeList = new ArrayList<Item>();
                    for (Item i : itemList) {
                        if (i.getLocation().equals("Freezer")) {
                            freeList.add(i);
                        }
                    }
                    iAdapter.setItemList(freeList);
                    recyclerView.setAdapter(iAdapter);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void prepareItemData() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        System.out.println(strDate);
        Item item = new Item("Bananas", date, "Pantry");
        itemList.add(item);

        item = new Item("Beef", date, "Freezer");
        itemList.add(item);

        item = new Item("Orange", date, "Pantry");
        itemList.add(item);

        item = new Item("Eggs", date, "Fridge");
        itemList.add(item);

        item = new Item("Milk", date, "Fridge");
        itemList.add(item);

        item = new Item("Yogurt", date, "Fridge");
        itemList.add(item);

        item = new Item("Spinach", date, "Fridge");
        itemList.add(item);

        item = new Item("Green Onions", date, "Fridge");
        itemList.add(item);

        item = new Item("Cheddar", date, "Fridge");
        itemList.add(item);

        item = new Item("Apples", date, "Pantry");
        itemList.add(item);

        item = new Item("Bread", date, "Pantry");
        itemList.add(item);

        item = new Item("Mozzarella", date, "Fridge");
        itemList.add(item);

        item = new Item("Tofu", date, "Fridge");
        itemList.add(item);

        item = new Item("Raspberries", date, "Fridge");
        itemList.add(item);

        item = new Item("Butter", date, "Fridge");
        itemList.add(item);
    }

    // Call when the user taps the Add Item button
    public void addItem(View view) {
        Intent intent = new Intent(this, AddItems.class);
        startActivity(intent);
    }

    public void viewRecipes(View view) {
        Intent intent = new Intent(this, RecipeFragment.class);
        startActivity(intent);
    }

    public void editItem(View view) {
        Intent intent = new Intent(this, EditItems.class);
        startActivity(intent);
    }

    public void viewItem(View view) {
        Intent intent = new Intent(this, ViewItem.class);
        startActivity(intent);
    }
}