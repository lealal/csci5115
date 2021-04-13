package com.example.csci5115;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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

import com.google.android.material.snackbar.Snackbar;
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
    private AddItemAdapter iAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ArrayList<Item> addedItems = new ArrayList<>();

    @Override
    public void onListItemClick(int position) {
        // This is where we will create intents and pass the itemList.get(position) object onto the next activity
        // Intent intent = new Intent(this, ViewItem.class);
//        intent.putExtra("Item", item);
        // startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        setTitle("Add Items");

        recyclerView = (RecyclerView) findViewById(R.id.addItemList);

        iAdapter = new AddItemAdapter(itemList, this);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(iAdapter);

        prepareItemData();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int direction) {

                int position = target.getAdapterPosition();
                String tmp = addedItems.get(position).getItemName();
                iAdapter.notifyDataSetChanged();
                Snackbar snackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayoutAdd), "Deleted Item: " + addedItems.get(position).getItemName(),
                        Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.snack_bar_undo, v -> undoDelete(tmp, position));
                snackbar.show();
                itemList.remove(position);
                addedItems.remove(position);
            }


        });
        helper.attachToRecyclerView(recyclerView);
    }
    private void undoDelete(String tmp, int position) {
        Item item = new Item(tmp, "lol", "Fridge");
        itemList.add(item);
        addedItems.add(item);
        iAdapter.notifyItemInserted(itemList.size() - 1);
    }

    private void prepareItemData() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);

        for(Item newItem : addedItems){
            itemList.add(newItem);
        }
    }

    public void addItemInput(View view) {
        Date date = new Date();
        EditText editText = (EditText) findViewById(R.id.itemName);
        String newItemName = editText.getText().toString();
        Item newItem = new Item(newItemName, "Today", "Fridge");
        itemList.add(newItem);
        addedItems.add(newItem);
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

    public void addAllItems(View view){
        for(Item newItem : addedItems){
            MainActivity.addedNewItems.add(newItem);
        }
        addedItems.clear();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

//    public static void hideKeyboardFrom(Context context, View view) {
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }
}