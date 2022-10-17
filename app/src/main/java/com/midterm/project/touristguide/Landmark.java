package com.midterm.project.touristguide;

import java.io.Serializable;

public class Landmark implements Serializable {
    private int[] resourceImage;
    private String name;
    private String description;
    private int rating;
    private String wikipage;
    private String phoneNumber;
    private String address;
    public Landmark(int[] resourceImage, String name, String description, int rating, String wikipage, String phoneNumber, String address) {
        this.resourceImage = resourceImage;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.wikipage = wikipage;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int[] getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(int[] resourceImage) {
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

    public String getWikipage() {
        return wikipage;
    }

    public void setWikipage(String wikipage) {
        this.wikipage = wikipage;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
