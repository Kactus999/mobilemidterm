package com.midterm.project.touristguide;

import java.io.Serializable;

public class Landmark implements Serializable {
    private int resourceImage;
    private String name;
    private String description;
    private int rating;

    public Landmark(int resourceImage, String name, String description, int rating) {
        this.resourceImage = resourceImage;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public int getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(int resourceImage) {
        this.resourceImage = resourceImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
