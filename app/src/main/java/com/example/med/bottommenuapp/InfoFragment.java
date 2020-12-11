package com.example.med.bottommenuapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.med.bottommenuapp.models.Data;



public class InfoFragment extends Fragment {

    ImageView mImgResto;
    Button mBtnMenu;


    public InfoFragment() {
        // Required empty public constructor
    }
    private static final String ARG_ID = "id";

    private long mId;

    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(long id) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = getArguments().getLong(ARG_ID);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_info, container, false);
        mImgResto = (ImageView) rootView.findViewById(R.id.img_resto_info);
        mBtnMenu = (Button) rootView.findViewById(R.id.btn_menu_info);

        mImgResto.setImageResource(Data.dataRestaurant.get(((int) mId) - 1).getIdImg());
        mBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ((AppCompatActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, MenuFragment.newInstance(mId));
                transaction.commit();
            }
        });
        return rootView;
    }




}
