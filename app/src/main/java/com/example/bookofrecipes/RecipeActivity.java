package com.example.bookofrecipes;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class RecipeActivity extends AppCompatActivity {

    private RecipeDetails recipeDetails;
    private TextView name, description;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        name = findViewById(R.id.recipe_name_details);
        description = findViewById(R.id.recipe_description_details);
        image = findViewById(R.id.recipe_image_view);

        Intent intent = getIntent();
        String uuid = intent.getStringExtra("uuid");
        if (uuid != null) NetworkService.getInstance()
                .getRecipesApi()
                .getRecipeDetails(uuid)
                .enqueue(new Callback<RecipeDetails>() {
                    @Override
                    public void onResponse(Call<RecipeDetails> call, Response<RecipeDetails> response) {
                        recipeDetails = response.body();
                        Toast.makeText(RecipeActivity.this, recipeDetails.getUuid(), Toast.LENGTH_SHORT).show();
                        name.setText(recipeDetails.getName());
                        description.setText(recipeDetails.getDescription());
                    }

                    @Override
                    public void onFailure(Call<RecipeDetails> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                    }
                });
    }
}
