package com.example.bookofrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.bookofrecipes.Model.RecipeBrief;
import com.example.bookofrecipes.Model.RecipeDetails;
import com.example.bookofrecipes.Model.RecipeObect;
import com.example.bookofrecipes.WorkWithApi.NetworkService;

public class RecipeActivity extends AppCompatActivity {

    private RecipeDetails recipeDetails;
    private TextView name, description, instructions;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private RatingBar ratingBar;
    private LinearLayout similarContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        name = findViewById(R.id.recipe_name_details);
        description = findViewById(R.id.recipe_description_details);
        instructions = findViewById(R.id.recipe_instractions_details);
        ratingBar = findViewById(R.id.rating_bar_details);
        ratingBar.setNumStars(5);
        pager = findViewById(R.id.view_pager_details);
        similarContainer = findViewById(R.id.recipe_similar_container_details);

        Intent intent = getIntent();
        String uuid = intent.getStringExtra("uuid");
        NetworkService.getInstance()
                .getRecipesApi()
                .getRecipeDetails(uuid)
                .enqueue(new Callback<RecipeObect>() {
                    @Override
                    public void onResponse(Call<RecipeObect> call, Response<RecipeObect> response) {
                        recipeDetails = response.body().getRecipe();

                        name.setText(recipeDetails.getName());
                        //check for description
                        if (recipeDetails.getDescription() != null) {
                            description.setText(recipeDetails.getDescription());
                        } else {
                            description.setVisibility(View.GONE);
                        }
                        instructions.setText(recipeDetails.getInstructions().replaceAll("<br>", "\n"));
                        ratingBar.setRating(recipeDetails.getDifficulty());

                        pagerAdapter = new RecipeFragmentPagerAdapter(getSupportFragmentManager(), recipeDetails.getImages());
                        pager.setAdapter(pagerAdapter);
                        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            }

                            @Override
                            public void onPageSelected(int position) {
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                        addSimilar();
                    }

                    @Override
                    public void onFailure(Call<RecipeObect> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                    }
                });
    }

    void addSimilar() {
        //if there is similar, than show, else hide fields
        if (recipeDetails.getSimilar().size() != 0) {
            for (final RecipeBrief s : recipeDetails.getSimilar()) {
                Button button = new Button(this);
                //how button looks
                button.setLayoutParams(new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.MATCH_PARENT));
                button.setText(s.getName());
                button.setTextColor(getResources().getColor(R.color.colorAccent));
                button.setBackgroundTintList(ContextCompat.getColorStateList(RecipeActivity.this, R.color.colorPrimary));
                //how button works
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(RecipeActivity.this, RecipeActivity.class);
                        intent.putExtra("uuid", s.getUuid());
                        startActivity(intent);
                    }
                });
                similarContainer.addView(button);
            }
        } else {
            TextView textView = findViewById(R.id.similar_text_view);
            textView.setVisibility(View.GONE);
            View similarContainer = findViewById(R.id.similar_views);
            similarContainer.setVisibility(View.GONE);
        }
    }
}
