package com.rto.prepzone;

import com.google.gson.annotations.SerializedName;

public class VideoModel {
    @SerializedName("id")
    String id;

    @SerializedName("image")
    String image;

    @SerializedName("title")
    String title;

    @SerializedName("url")
    String url;


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

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

}
