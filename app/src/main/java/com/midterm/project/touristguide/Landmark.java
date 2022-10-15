package com.midterm.project.touristguide;

public class Landmark {
    private int resourceImage;
    private String name;
    private String description;
    private String rating;

    public Landmark(int resourceImage, String name, String description, String rating) {
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
