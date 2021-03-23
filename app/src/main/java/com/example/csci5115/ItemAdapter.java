package com.example.csci5115;

import java.util.List;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private List<Item> itemList;
    private RecyclerViewClickInterface onClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView itemName, itemAddedDate, location;

            public MyViewHolder(View view) {
                super(view);
                itemName = (TextView) view.findViewById(R.id.itemName);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Log.d("a","onClick");
                onClickListener.onListItemClick(position);
            }
        }

        public ItemAdapter(List<Item> itemList, RecyclerViewClickInterface onClickListener) {
            this.itemList = itemList;
            this.onClickListener = onClickListener;
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
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }
}
