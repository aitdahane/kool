package com.example.med.bottommenuapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;

import com.example.med.bottommenuapp.models.BDD;
import com.example.med.bottommenuapp.models.Cart;
import com.example.med.bottommenuapp.models.Data;
import com.example.med.bottommenuapp.models.Menu;
import com.example.med.bottommenuapp.models.Plat;
import com.example.med.bottommenuapp.models.Restaurant;
import com.example.med.bottommenuapp.models.User;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static boolean authenticated = false;
    public static User user;
    public static BDD bdd;
    public static File usersFile;
    private BottomNavigationView mBtmNavigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = HomeFragment.newInstance();
                    break;
                case R.id.navigation_profile:
                    if (authenticated) {
                        selectedFragment = ProfileFragment.newInstance();
                    } else {
                        selectedFragment = LoginFragment.newInstance();
                    }
                    break;
                case R.id.navigation_shopping_basket:
                    selectedFragment = BasketFragment.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bdd = new BDD(this);



        try {
            Data.dataInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i = 0; i < Data.dataUser.size(); i++)
            Log.d("TAG",Data.dataUser.get(i).getEmail() + " " + Data.dataUser.get(i).getPassword());
        Cart.cartInit();
        // Bottom Navigation View
        mBtmNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        mBtmNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();
    }



}
