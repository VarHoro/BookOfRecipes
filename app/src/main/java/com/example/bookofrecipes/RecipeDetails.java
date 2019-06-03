package com.example.bookofrecipes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipeDetails extends Recipe {
    private ArrayList<RecipeBrief> similar;

    public ArrayList<RecipeBrief> getSimilar() {
        return similar;
    }

    public void setSimilar(ArrayList<RecipeBrief> similar) {
        this.similar = similar;
    }
}