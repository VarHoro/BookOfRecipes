package com.example.bookofrecipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {

    private ArrayList<Recipe> recipes;

    public RecipeAdapter(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item_layout, parent, false);
        return new RecipeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        Recipe current = recipes.get(position);
        Picasso.get().load(current.getImages().get(0)).into(holder.imageView);
        holder.titleTextView.setText(current.getName());
        holder.descriptionTextView.setText(current.getDescription());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class RecipeHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView, descriptionTextView;
        private ImageView imageView;

        public RecipeHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.recipe_title_text_view);
            descriptionTextView = itemView.findViewById(R.id.recipe_description_text_view);
            imageView = itemView.findViewById(R.id.recipe_image_view);
        }
    }
}