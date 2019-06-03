package com.example.bookofrecipes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecipesApi {
    @GET("recipes")
    Call<RecipeList> getRecipes();

    @GET("recipes/{uuid}")
    Call<RecipeDetails> getRecipeDetails(@Path("uuid") String RecipeUuid);
}
