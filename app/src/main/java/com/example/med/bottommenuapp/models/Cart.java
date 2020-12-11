package com.example.med.bottommenuapp.models;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Med on 5/8/2017.
 */

public class Cart {
    public static Commande commande;
    static double  somme=0;
    public static ArrayList<Plat> dataCart;
    public Cart()
    {

    }

    public static void cartInit()
    {
        commande = new Commande();
        dataCart=new ArrayList<Plat>();
    }

    public static  double totalCart()
    {
        somme=0;
        for (Plat plat:dataCart) {
            somme+=(plat.getPrice()*plat.getQuantity());

        }
        return somme;
    }

    public static String toJson()
    {
        Gson gsonObject=new Gson();
        String m=gsonObject.toJson(dataCart);
        System.out.println(m);
        return m;
    }

}
