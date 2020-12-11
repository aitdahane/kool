package com.example.med.bottommenuapp.models;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.med.bottommenuapp.MainActivity;
import com.example.med.bottommenuapp.R;
import com.google.gson.Gson;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Med on 5/8/2017.
 */

public class Data {
    public static ArrayList<Restaurant> dataRestaurant;
    public static ArrayList<Plat> dataPlat1;
    public static ArrayList<Plat> dataPlat2;
    public static ArrayList<Plat> dataPlat3;
    public static ArrayList<Menu> dataMenu;
    public static ArrayList<User> dataUser;
    //public static ArrayList<Plat> dataCart;

    public static void dataInit() throws Exception {
        //Data.dataCart = new ArrayList<Plat>();
        dataUser = new ArrayList<User>();
        Cursor cursor= MainActivity.bdd.getData();

        Log.d("TAG","no cursor");
        while(cursor.moveToNext()) {
            User u = new User(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            dataUser.add(u);
        }
        /*Serializer serializer = new Persister();
        //User user = new User("test","test@test.com","0636554443","123456");
        //UserList ul = new UserList();
        //ul.userlist = new ArrayList<User>();
        //ul.userlist.add(user);
        Log.d("TAG","Salut");
        //serializer.write(ul, MainActivity.usersFile);
        Log.d("TAG","Salut1");
        UserList userlist = serializer.read(UserList.class, MainActivity.usersFile);
        Log.d("TAG","Salut2");
        dataUser = userlist.userlist;
        Log.d("TAG","Salut3");
        for(int i = 0; i < dataUser.size(); i++)
            Log.d("TAG",dataUser.get(i).getEmail() + " " + dataUser.get(i).getPassword());*/

        Data.dataRestaurant = new ArrayList<Restaurant>();

        Data.dataMenu = new ArrayList<Menu>();

        Data.dataPlat1 = new ArrayList<Plat>();
        Data.dataPlat2 = new ArrayList<Plat>();
        Data.dataPlat3 = new ArrayList<Plat>();


        /* Restaurant 1 */
        Data.dataRestaurant.add(new Restaurant(1,"Snack Al Rahma","3.2","6 KM", R.drawable.img_1));
        Data.dataPlat1.add(new Plat(1,"Sandwich Thon", 15.00, R.drawable.img_sandwich_thon));
        Data.dataPlat1.add(new Plat(2,"Sandwich Viande Hachee", 23.00, R.drawable.img_sandwich_viande));
        Data.dataPlat1.add(new Plat(3,"Tacos Poulet", 30.00, R.drawable.img_tacos_poulet));
        Data.dataPlat1.add(new Plat(4,"Tacos Viande Hachee", 30.00, R.drawable.img_tacos_viande));
        Data.dataPlat1.add(new Plat(5,"Pizza Margarihta", 30.00, R.drawable.img_pizza_margherita));
        Data.dataPlat1.add(new Plat(6,"Pizza Royale", 50.00, R.drawable.img_pizza_royale));
        Data.dataMenu.add(new Menu(1,Data.dataPlat1));

        /* Restaurant 2 */
        Data.dataRestaurant.add(new Restaurant(2,"O'TACOS","4","10 KM", R.drawable.img_2));
        Data.dataPlat2.add(new Plat(7,"Tacos Poulet", 50.00, R.drawable.img_tacos_poulet));
        Data.dataPlat2.add(new Plat(8,"Tacos Viande Hachee", 50.00, R.drawable.img_tacos_viande));
        Data.dataPlat2.add(new Plat(9,"Mega Giant", 80.00, R.drawable.img_mega_giant));
        Data.dataMenu.add(new Menu(2,Data.dataPlat2));

        /* Restaurant 3 */
        Data.dataRestaurant.add(new Restaurant(3,"Bô Sushi","5","12 KM", R.drawable.img_3));
        Data.dataPlat3.add(new Plat(10,"Sashimi shake", 50.00, R.drawable.img_sashimi_shake));
        Data.dataPlat3.add(new Plat(11,"Fry Roll Saumon", 60.00, R.drawable.img_fry_roll_saumon));
        Data.dataPlat3.add(new Plat(12,"Aromaki Saumon", 80.00, R.drawable.img_aromaki_saumon));
        Data.dataMenu.add(new Menu(3,Data.dataPlat3));
    }
}
