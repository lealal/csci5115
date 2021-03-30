package com.example.csci5115;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface {
    private static List<Item> itemList = new ArrayList<>();
    private List<Item> filteredList = new ArrayList<>();
    private Item deletedItem;
    private RecyclerView recyclerView;
    private ItemAdapter iAdapter;
    private TabLayout tabLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    public static ArrayList<Item> addedNewItems = new ArrayList<>();
    private static boolean RUN_ONCE = true;

    public static List<Item> getList() {
        return itemList;
    }


    public static void setList(List<Item> list) {
        itemList = list;
    }


    @Override
    public void onListItemClick(int position) {
        // This is where we will create intents and pass the itemList.get(position) object onto the next activity
        Item item = filteredList.get(position);
        Intent intent = new Intent(this, ViewItem.class);
        intent.putExtra("Item", item);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        iAdapter = new ItemAdapter(itemList, this);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(iAdapter);

        if (RUN_ONCE){
            prepareItemData();
        }
        if (addedNewItems.size()>0){
            for(Item newItem : addedNewItems){
                itemList.add(newItem);
            }
        }
        addedNewItems.clear();
        filteredList = itemList;
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int direction) {

                int position = target.getAdapterPosition();
                String tmp = filteredList.get(position).getItemName();
                iAdapter.notifyDataSetChanged();
                Snackbar snackbar = Snackbar.make(findViewById(R.id.recyclerView), "Deleted Item: " + filteredList.get(position).getItemName(),
                        Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.snack_bar_undo, v -> undoDelete(tmp, position));
                snackbar.show();
                String itemDelete = filteredList.get(position).getItemName();
                deletedItem = filteredList.get(position);
                int posItem=0;
                for (int i = 0; i < itemList.size(); i++){
                    if (itemList.get(i).getItemName().equals(itemDelete)){
                        posItem=i;
                    }
                }
                itemList.remove(posItem);
                createFilteredList();
            }
        });
        helper.attachToRecyclerView(recyclerView);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                filteredList = new ArrayList<>();
                if (pos == 0) {
                    // all
                    for (Item i : itemList) {
                        filteredList.add(i);
                    }
                } else if (pos == 1) {
                    // pantry
                    for (Item i : itemList) {
                        if (i.getLocation().equals("Pantry")) {
                            filteredList.add(i);
                        }
                    }
                } else if (pos == 2) {
                    // fridge
                    for (Item i : itemList) {
                        if (i.getLocation().equals("Fridge")) {
                            filteredList.add(i);
                        }
                    }
                } else if (pos == 3) {
                    // freezer
                    for (Item i : itemList) {
                        if (i.getLocation().equals("Freezer")) {
                            filteredList.add(i);
                        }
                    }
                }
                iAdapter.setItemList(filteredList);
                recyclerView.setAdapter(iAdapter);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void undoDelete(String tmp, int position) {
        itemList.add(deletedItem);
        createFilteredList();
        iAdapter.notifyItemInserted(filteredList.size() - 1);
    }

    private void createFilteredList(){
        int pos = tabLayout.getSelectedTabPosition();
        filteredList = new ArrayList<>();
        if (pos == 0) {
            // all
            for (Item i : itemList) {
                filteredList.add(i);
            }
        } else if (pos == 1) {
            // pantry
            for (Item i : itemList) {
                if (i.getLocation().equals("Pantry")) {
                    filteredList.add(i);
                }
            }
        } else if (pos == 2) {
            // fridge
            for (Item i : itemList) {
                if (i.getLocation().equals("Fridge")) {
                    filteredList.add(i);
                }
            }
        } else if (pos == 3) {
            // freezer
            for (Item i : itemList) {
                if (i.getLocation().equals("Freezer")) {
                    filteredList.add(i);
                }
            }
        }
        iAdapter.setItemList(filteredList);
        recyclerView.setAdapter(iAdapter);
    }

    private void prepareItemData() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String date = df.format(c);
        Item item = new Item("Beef", date, "Freezer");
        itemList.add(item);

        item = new Item("Eggs", date, "Fridge");
        itemList.add(item);

        item = new Item("Green onions", date, "Fridge", "green_onions");
        itemList.add(item);

        item = new Item("Bread", date, "Fridge", "bread");
        itemList.add(item);

        item = new Item("Cheese", date, "Pantry");
        itemList.add(item);

        item = new Item("Butter", date, "Fridge");
        itemList.add(item);

        item = new Item("Pasta", date, "Fridge");
        itemList.add(item);

        item = new Item("Tomato sauce", date, "Fridge");
        itemList.add(item);

        RUN_ONCE = false;
    }

    // Call when the user taps the Add Item button
    public void addItem(View view) {
        Intent intent = new Intent(this, AddItems.class);
        startActivity(intent);
    }

    public void viewRecipes(View view) {
        ArrayList<String> checked = new ArrayList<>();
        for (int i = 0; i < filteredList.size(); i++) {
            if (filteredList.get(i).getChecked()) {
                checked.add(filteredList.get(i).getItemName());
                filteredList.get(i).setChecked(false);
            }
        }

        Intent intent = new Intent(this, RecipeFragment.class);

        if (checked.size() != 0)
            intent.putExtra("checked", (ArrayList<String>) checked);
        else {
            ArrayList<String> filteredStringList = new ArrayList<>();

            for (Item item : filteredList)
                filteredStringList.add(item.getItemName());

            intent.putExtra("checked", filteredStringList);
        }
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