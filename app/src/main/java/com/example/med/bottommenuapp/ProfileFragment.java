package com.example.med.bottommenuapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.med.bottommenuapp.models.Data;


public class ProfileFragment extends Fragment {

    TextView mNameText;
    EditText mMobileEdit;
    EditText mEmailEdit;
    Button mValiderBtn;
    Button mLogoutBtn;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        mNameText = (TextView) rootView.findViewById(R.id.tv_name);
        mEmailEdit = (EditText) rootView.findViewById(R.id.edit_email);
        mMobileEdit = (EditText) rootView.findViewById(R.id.edit_mobile);
        mValiderBtn = (Button) rootView.findViewById(R.id.btn_valider);
        mLogoutBtn = (Button) rootView.findViewById(R.id.btn_logout);

        mNameText.setText(MainActivity.user.getName());
        mEmailEdit.setText(MainActivity.user.getEmail());
        mMobileEdit.setText(MainActivity.user.getMobile());

        mValiderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.user.setEmail(mEmailEdit.getText().toString());
                MainActivity.user.setMobile(mMobileEdit.getText().toString());
                MainActivity.user.setName(mNameText.getText().toString());
            }
        });

        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.authenticated = false;
                Fragment fragment = LoginFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, fragment);
                transaction.commit();
            }
        });
        return rootView;
    }

}
