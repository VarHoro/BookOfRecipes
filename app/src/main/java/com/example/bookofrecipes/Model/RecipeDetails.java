package com.example.bookofrecipes.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipeDetails extends Recipe {//object for recipeModel.details
    @SerializedName("similar")
    private ArrayList<RecipeBrief> similar;

    public ArrayList<RecipeBrief> getSimilar() {
        return similar;
    }

    public void setSimilar(ArrayList<RecipeBrief> similar) {
        this.similar = similar;
    }
}
