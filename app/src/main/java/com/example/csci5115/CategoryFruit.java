package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.android.material.snackbar.Snackbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.csci5115.AddItems;

import android.widget.EditText;

public class CategoryFruit extends AppCompatActivity implements RecyclerViewClickInterface {
    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter iAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onListItemClick(int position) {
        AddItems.addedItems.add(itemList.get(position).getItemName());
//        Snackbar mySnackbar = Snackbar.make(R.layout.activity_category_fruit, "hello", Snackbar.LENGTH_SHORT);
        Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Added Item: " + itemList.get(position).getItemName(),
                Snackbar.LENGTH_SHORT).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_fruit);
        setTitle("Fruits");

        recyclerView = (RecyclerView) findViewById(R.id.categoryFruit);

        iAdapter = new ItemAdapter(itemList, this);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(iAdapter);

        prepareItemData();
    }
    private void prepareItemData() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String date = df.format(c);
        Item item = new Item("Apple", date, "Fridge");
        itemList.add(item);

        item = new Item("Avocado", date, "Fridge");
        itemList.add(item);

        item = new Item("Banana", date, "Fridge");
        itemList.add(item);

        item = new Item("Blueberries", date, "Fridge");
        itemList.add(item);

        item = new Item("Cherries", date, "Fridge");
        itemList.add(item);

        item = new Item("Coconut", date, "Fridge");
        itemList.add(item);

        item = new Item("Grapes", date, "Fridge");
        itemList.add(item);

        item = new Item("Lemon", date, "Fridge");
        itemList.add(item);

        item = new Item("Lime", date, "Fridge");
        itemList.add(item);

        item = new Item("Mango", date, "Fridge");
        itemList.add(item);
    }
}