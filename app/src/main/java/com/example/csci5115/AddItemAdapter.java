package com.example.csci5115;

import java.util.ArrayList;
import java.util.List;

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

public class AddItemAdapter extends RecyclerView.Adapter<AddItemAdapter.MyViewHolder> {

    private List<Item> itemList;
    private RecyclerViewClickInterface onClickListener;

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

    public AddItemAdapter(List<Item> itemList, RecyclerViewClickInterface onClickListener) {
        this.itemList = itemList;
        this.onClickListener = onClickListener;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemName.setText(item.getItemName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(List<Item> newItemList) {
        itemList = newItemList;
    }
}