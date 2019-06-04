package com.example.bookofrecipes.WorkWithApi;

import com.example.bookofrecipes.Model.RecipeList;
import com.example.bookofrecipes.Model.RecipeObect;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecipesApi {
    @GET("recipes")
    Call<RecipeList> getRecipes();

    @GET("recipes/{uuid}")
    Call<RecipeObect> getRecipeDetails(@Path("uuid") String uuid);
}
