package com.example.bookofrecipes.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Recipe extends RecipeBrief{ //Object for recipeModel.list
    @SerializedName("images")
    private ArrayList<String> images;
    @SerializedName("lastUpdated")
    private Integer lastUpdated;
    @SerializedName("description")
    private String description;
    @SerializedName("instructions")
    private String instructions;
    @SerializedName("difficulty")
    private Integer difficulty;

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public Integer getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }
}
