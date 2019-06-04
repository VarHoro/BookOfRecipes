package com.example.bookofrecipes;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookofrecipes.Model.Recipe;
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
    public RecipeHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item_layout, parent, false);
        return new RecipeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, final int position) {
        Recipe current = recipes.get(position);
        Picasso.get().load(current.getImages().get(0)).into(holder.imageView);
        holder.titleTextView.setText(current.getName());
        holder.descriptionTextView.setText(current.getDescription());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipe item = recipes.get(position);
                Context context = view.getContext();
                Intent intent = new Intent(context, RecipeActivity.class);
                intent.putExtra("uuid", item.getUuid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class RecipeHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView, descriptionTextView;
        private ImageView imageView;
        private View card;

        public RecipeHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.recipe_title_text_view);
            descriptionTextView = itemView.findViewById(R.id.recipe_description_text_view);
            imageView = itemView.findViewById(R.id.recipe_image_view);
            card = itemView.findViewById(R.id.recipe_card);
        }
    }
}
