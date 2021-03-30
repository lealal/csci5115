package com.example.csci5115;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    private List<Recipe> recipeList;
    private RecyclerViewClickInterface onClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recipeName, recipeIngredients;
        public ImageView recipeImage;
        public View view;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            cardView = view.findViewById(R.id.recipeCard);
            recipeName = view.findViewById(R.id.recipeName);
            recipeIngredients = view.findViewById(R.id.recipeIngredients);
            recipeImage = view.findViewById(R.id.recipeImage);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), " clicked!", Toast.LENGTH_LONG).show();
        }

    }

    public RecipeAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.recipeName.setText(recipe.getRecipeName());
        int id = holder.view.getResources().getIdentifier(recipe.getRecipeImage(), "drawable", holder.view.getContext().getPackageName());
        Drawable drawable = holder.view.getResources().getDrawable(id);
        holder.recipeImage.setImageDrawable(drawable);
        holder.cardView.setOnClickListener(v -> {
            Toast.makeText(holder.view.getContext(), recipe.getRecipeName() + " clicked!", Toast.LENGTH_LONG).show();
        });

        int ingredientSize = recipe.getRecipeIngredients().size();
        String ingredientCount;

        if (ingredientSize == 1)
            ingredientCount = Integer.toString(ingredientSize) + " item";
        else
            ingredientCount = Integer.toString(ingredientSize) + " items";

        holder.recipeIngredients.setText(ingredientCount);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void setRecipeList(List<Recipe> newItemList) {
        recipeList = newItemList;
    }
}
