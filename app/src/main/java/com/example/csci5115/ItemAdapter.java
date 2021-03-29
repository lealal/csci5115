package com.example.csci5115;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>  {

    private List<Item> itemList;
    private RecyclerViewClickInterface onClickListener;
    private RecyclerViewClickInterface getOnClickListenerCheckBox;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Button itemName, itemAddedDate, location;
        public CheckBox checkBox;
        public ImageView editItemIcon;
        public View view;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            itemName = view.findViewById(R.id.itemName);
            checkBox = view.findViewById(R.id.checkBox);
            editItemIcon = view.findViewById(R.id.editItemIcon);
            itemName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onClickListener.onListItemClick(position);
        }

    }
    public ItemAdapter(List<Item> itemList, RecyclerViewClickInterface onClickListener) {
        this.itemList = itemList;
        this.onClickListener = onClickListener;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemName.setText(item.getItemName());

        // On edit item click, create and pass new intent to EditItems here
        holder.editItemIcon.setOnClickListener(v -> {
            Item theItem = itemList.get(position);
            Intent intent = new Intent(v.getContext(), EditItems.class);
            intent.putExtra("Item", theItem);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(List<Item> newItemList) {
        itemList = newItemList;
    }
}
