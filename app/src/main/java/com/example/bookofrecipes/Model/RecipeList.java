package com.example.bookofrecipes.Model;

import java.util.ArrayList;

public class RecipeList { //object for array<recipeModel.list>
    private ArrayList<Recipe> recipes;

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }
}
