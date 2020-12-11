package com.example.med.bottommenuapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


import com.example.med.bottommenuapp.adapters.RestaurantAdapter;
import com.example.med.bottommenuapp.models.Data;
import com.example.med.bottommenuapp.models.Restaurant;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private SearchView mSchRestaurant;
    private RecyclerView mRecvListRestaurant;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mRestaurantAdapter;


    private ArrayList<Restaurant> mData;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchRestaurant("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mRecvListRestaurant = (RecyclerView) rootView.findViewById(R.id.recv_list_restaurant);
        mRecvListRestaurant.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecvListRestaurant.setLayoutManager(mLayoutManager);
        mRestaurantAdapter = new RestaurantAdapter(mData);
        mRecvListRestaurant.setAdapter(mRestaurantAdapter);

        mSchRestaurant = (SearchView) rootView.findViewById(R.id.sch_restaurant);
        mSchRestaurant.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               searchRestaurant(query);
               mRestaurantAdapter = new RestaurantAdapter(mData);
               mRecvListRestaurant.setAdapter(mRestaurantAdapter);
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               return false;
           }
       });
        return rootView;
    }

    private void searchRestaurant(String query) {
        mData = new ArrayList<Restaurant>();
        boolean find = false;
        for(int i = 0; i < Data.dataRestaurant.size(); i++) {
            if (Data.dataRestaurant.get(i).getTitle().contains(query)) {
                mData.add(Data.dataRestaurant.get(i));
                find = true;
            }
        }
        if (!find) {
            mData = Data.dataRestaurant;
        }
    }
}
