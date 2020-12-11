package com.example.med.bottommenuapp.models;

/**
 * Created by Med on 5/8/2017.
 */

public class Plat {
    private long mId;
    private String mTitle;
    private double mPrice;
    private int mQuantity = 0;
    private Integer mIdImg;

    public Plat(long id, String title, double price, Integer idImg) {
        mId = id;
        mIdImg = idImg;
        mTitle = title;
        mPrice = price;
    }

    public Plat() {

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

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        this.mPrice = price;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        this.mQuantity = quantity;
    }

    public Integer getIdImg() {
        return mIdImg;
    }

    public void setIdImg(Integer idImg) {
        mIdImg = idImg;
    }

    public String toString() {
        return mTitle+":"+mPrice+":"+mQuantity;
    }
}
