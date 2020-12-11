package com.example.med.bottommenuapp.models;

/**
 * Created by Med on 6/12/2017.
 */

public class Commande {
    public long mId;
    public double mPrice;
    public long mIdResto;

    public Commande() {

    }
    public Commande(long id, double price, long idResto) {
        mId = id;
        mPrice = price;
        mIdResto = idResto;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public long getIdResto() {
        return mIdResto;
    }

    public void setIdResto(long idResto) {
        mIdResto = idResto;
    }

    public String toString() {
        return mIdResto+":"+mPrice;
    }


}
