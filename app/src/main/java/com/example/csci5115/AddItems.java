package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;
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
import java.util.Date;
import java.util.List;

import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AddItems extends AppCompatActivity implements RecyclerViewClickInterface {
    private static final String LIST_STATE_KEY = "saveState";
    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter iAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ArrayList<String> addedItems = new ArrayList<String>();

    @Override
    public void onListItemClick(int position) {
        // This is where we will create intents and pass the itemList.get(position) object onto the next activity
        Intent intent = new Intent(this, ViewItem.class);
//        intent.putExtra("Item", item);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        setTitle("Add Items");

        recyclerView = (RecyclerView) findViewById(R.id.addItemList);

        iAdapter = new ItemAdapter(itemList, this);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(iAdapter);

        prepareItemData();
    }

    private void prepareItemData() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        System.out.println(itemList);
        System.out.println(addedItems);
//        Item item = new Item("Bananas", date, "Fridge");
//        itemList.add(item);
//
//        item = new Item("Beef", date, "Fridge");
//        itemList.add(item);
//
//        item = new Item("Orange", date, "Fridge");
//        itemList.add(item);

        for(String newItemString : addedItems){
            Item item = new Item(newItemString, date, "Fridge");
            itemList.add(item);
        }
    }

    public void addItemInput(View view) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        System.out.println(strDate);
        EditText editText = (EditText) findViewById(R.id.itemName);
        String newItemName = editText.getText().toString();
        Item item = new Item(newItemName, date, "Fridge");
        itemList.add(item);
        addedItems.add(newItemName);
        iAdapter.notifyItemInserted(itemList.size() - 1);
    }

    public void scanReceipt(View view){
        Intent intent = new Intent(this, ScanReceipt.class);
        startActivity(intent);
    }

    public void itemCategories(View view){
        Intent intent = new Intent(this, ItemCategories.class);
        startActivity(intent);
    }

//    public static void hideKeyboardFrom(Context context, View view) {
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }
}