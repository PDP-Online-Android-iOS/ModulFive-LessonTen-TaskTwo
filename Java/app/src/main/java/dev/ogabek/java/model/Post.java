package dev.ogabek.java.model;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private final int profile;
    private int photo;
    private final String fullName;
    private List<Photos> photos = new ArrayList<>();

    public Post(int profile, int photo, String fullName) {
        this.profile = profile;
        this.photo = photo;
        this.fullName = fullName;
    }

    public Post(int profile, List<Photos> photos, String fullName) {
        this.profile = profile;
        this.photos = photos;
        this.fullName = fullName;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public int getProfile() {
        return profile;
    }

    public int getPhoto() {
        return photo;
    }

    public String getFullName() {
        return fullName;
    }
}
