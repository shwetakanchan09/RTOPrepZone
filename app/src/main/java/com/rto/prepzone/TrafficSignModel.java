package com.rto.prepzone;

import com.google.gson.annotations.SerializedName;

public class TrafficSignModel {
    @SerializedName("id")
    String id;

    @SerializedName("image")
    String image;

    @SerializedName("name")
    String name;


    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


}
