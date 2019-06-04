package com.example.bookofrecipes;

import com.google.gson.annotations.SerializedName;

public class RecipeBrief {
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("name")
    private String name;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
