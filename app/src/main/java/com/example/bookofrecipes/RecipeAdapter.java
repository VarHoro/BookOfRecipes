package com.example.bookofrecipes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {

    private List<Recipe> recipies = new ArrayList<>();

    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item_layout, parent, false);
        return new RecipeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        Recipe current =recipies.get(position);
        holder.titleTextView.setText(current.getTitle());
        holder.descriptionTextView.setText(current.getDescription());
    }

    @Override
    public int getItemCount() {
        return recipies.size();
    }

    class RecipeHolder extends RecyclerView.ViewHolder{
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
