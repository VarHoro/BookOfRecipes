package com.example.bookofrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.SearchManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.bookofrecipes.Model.Recipe;
import com.example.bookofrecipes.Model.RecipeList;
import com.example.bookofrecipes.WorkWithApi.NetworkService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Recipe> mRecipes;
    private RecipeAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recycler and json
        initViews();
        //search
        SearchManager manager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView = findViewById(R.id.search_view);
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

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
