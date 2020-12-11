package com.example.med.bottommenuapp.models;



public class Restaurant {
    private long mId;
    private String mTitle;
    private String mRating;
    private String mDistance;
    private Integer mIdImg;


    public Restaurant(long id, String title, String rating, String distance, Integer idImg) {
        mId = id;
        this.mTitle = title;
        this.mRating = rating;
        this.mDistance = distance;
        mIdImg = idImg;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        this.mRating = rating;
    }

    public String getDistance() {
        return mDistance;
    }

    public void setDistance(String distance) {
        this.mDistance = distance;
    }

    public Integer getIdImg() {
        return mIdImg;
    }

    public void setIdImg(Integer idImg) {
        mIdImg = idImg;
    }
}
