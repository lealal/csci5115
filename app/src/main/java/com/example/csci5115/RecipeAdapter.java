package com.example.csci5115;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    private List<Recipe> recipeList;
    private RecyclerViewClickInterface onClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recipeName, recipeIngredients;

        public MyViewHolder(View view) {
            super(view);
            recipeName = view.findViewById(R.id.recipeName);
            recipeIngredients = view.findViewById(R.id.recipeIngredients);
        }

        @Override
        public void onClick(View v) {
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
