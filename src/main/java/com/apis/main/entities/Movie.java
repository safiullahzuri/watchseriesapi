package com.apis.main.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Movie {

    @JsonIgnore
    private int id;

    private String name;
    private String url;
    private String imageSource;

    public Movie(){
    }

    public Movie(String name, String url, String imageSource) {
        this.name = name;
        this.url = url;
        this.imageSource = imageSource;
    }

    public Movie(int id, String name, String url, String imageSource) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.imageSource = imageSource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
}
