package com.example.med.bottommenuapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.med.bottommenuapp.adapters.PlatAdapter;
import com.example.med.bottommenuapp.models.Data;
import com.example.med.bottommenuapp.models.Plat;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    private RecyclerView mRecvListPlat;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mPlatAdapter;

    //private ArrayList<> myDataset;

    private static final String ARG_ID = "id";

    private long mId;

    public MenuFragment() {

    }

    public static MenuFragment newInstance(long id) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getArguments().getLong(ARG_ID);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_menu, container, false);
        mRecvListPlat = (RecyclerView) rootView.findViewById(R.id.recv_list_menu);
        mRecvListPlat.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecvListPlat.setLayoutManager(mLayoutManager);
        int index = 0;
        for(int i = 0; i < Data.dataMenu.size(); i++) {
            if (Data.dataMenu.get(i).getId() == mId) {
                index = i;
                break;
            }
        }
        mPlatAdapter = new PlatAdapter(Data.dataMenu.get(index).getListPlat());
        mRecvListPlat.setAdapter(mPlatAdapter);
        return rootView;
    }


}
