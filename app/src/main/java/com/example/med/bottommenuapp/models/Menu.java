package com.example.med.bottommenuapp.models;

import java.util.ArrayList;

/**
 * Created by Med on 5/8/2017.
 */

public class Menu {
   private long mId;
   private ArrayList<Plat> mListPlat;

   public Menu(long id, ArrayList<Plat> listPlat) {
      mId = id;
      mListPlat = listPlat;
   }

   public long getId() {
      return mId;
   }

   public void setId(long id) {
      mId = id;
   }

   public ArrayList<Plat> getListPlat() {
      return mListPlat;
   }

   public void setListPlat(ArrayList<Plat> listPlat) {
      mListPlat = listPlat;
   }
}
