package com.example.bookofrecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
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
    private TextView name, description, insctructions;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        name = findViewById(R.id.recipe_name_details);
        description = findViewById(R.id.recipe_description_details);
        insctructions = findViewById(R.id.recipe_instractions_details);
        pager = findViewById(R.id.view_pager_details);

        Intent intent = getIntent();
        String uuid = intent.getStringExtra("uuid");
        if (uuid != null) {
            NetworkService.getInstance()
                    .getRecipesApi()
                    .getRecipeDetails(uuid)
                    .enqueue(new Callback<RecipeObect>() {
                        @Override
                        public void onResponse(Call<RecipeObect> call, Response<RecipeObect> response) {
                            recipeDetails = response.body().getRecipe();
                            name.setText(recipeDetails.getName());
                            description.setText(recipeDetails.getDescription());
                            insctructions.setText(recipeDetails.getInstructions());
                            pagerAdapter = new RecipeFragmentPagerAdapter(getSupportFragmentManager());
                            pager.setAdapter(pagerAdapter);
                            pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                    //
                                }

                                @Override
                                public void onPageSelected(int position) {

                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<RecipeObect> call, Throwable t) {
                            Log.d("Error", t.getMessage());
                        }
                    });
        }
    }

    private class RecipeFragmentPagerAdapter extends FragmentPagerAdapter{
        public RecipeFragmentPagerAdapter(FragmentManager manager){
            super(manager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return PagerFragment.newInstance(position, recipeDetails.getImages());
        }

        @Override
        public int getCount() {
            return recipeDetails.getImages().size();
        }
    }
}
