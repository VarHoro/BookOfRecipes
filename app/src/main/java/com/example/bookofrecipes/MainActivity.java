package com.example.bookofrecipes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Recipe> mRecipes;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecipeAdapter(mRecipes = new ArrayList<>());
        recyclerView.setAdapter(adapter);
        loadJSON();
    }

    private void loadJSON() {
        NetworkService.getInstance()
                .getRecipesApi()
                .getRecipes()
                .enqueue(new Callback<RecipeList>() {
                    @Override
                    public void onResponse(Call<RecipeList> call, Response<RecipeList> response) {
                        ArrayList<Recipe> recipeArrayList = response.body().getRecipes();
                        mRecipes.addAll(recipeArrayList);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<RecipeList> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                View menuItemView = findViewById(R.id.action_sort);
                PopupMenu popupMenu = new PopupMenu(this, menuItemView);
                popupMenu.inflate(R.menu.popup);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.sort_by_date:
                                Collections.sort(mRecipes, new Comparator<Recipe>() {
                                    @Override
                                    public int compare(Recipe recipe, Recipe t1) {
                                        return recipe.getLastUpdated().compareTo(t1.getLastUpdated());
                                    }
                                });
                                adapter.notifyDataSetChanged();
                                return true;
                            case R.id.sort_by_name:
                                Collections.sort(mRecipes, new Comparator<Recipe>() {
                                    @Override
                                    public int compare(Recipe recipe, Recipe t1) {
                                        return recipe.getName().compareToIgnoreCase(t1.getName());
                                    }
                                });
                                adapter.notifyDataSetChanged();
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                break;
            default:
                break;
        }
        return true;
    }
}
