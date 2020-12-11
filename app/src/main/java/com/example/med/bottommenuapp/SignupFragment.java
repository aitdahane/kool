package com.example.med.bottommenuapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.med.bottommenuapp.models.Data;
import com.example.med.bottommenuapp.models.Restaurant;
import com.example.med.bottommenuapp.models.User;
import com.example.med.bottommenuapp.models.UserList;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;


public class SignupFragment extends Fragment {
    ImageView  mImageView;
    EditText mNameText;
    EditText mEmailText;
    EditText  mMobileText;
    EditText mPasswordText;
    EditText mPasswordagainText;
    Button mSignupButton;
    TextView mLoginLink;

    public SignupFragment() {
        // Required empty public constructor
    }


    public static SignupFragment newInstance() {
        SignupFragment fragment = new SignupFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);
        mImageView = (ImageView) rootView.findViewById(R.id.img_signup);
        mNameText = (EditText) rootView.findViewById(R.id.input_name);
        mEmailText = (EditText) rootView.findViewById(R.id.input_email);
        mMobileText = (EditText) rootView.findViewById(R.id.input_mobile);
        mPasswordText = (EditText) rootView.findViewById(R.id.input_password);
        mPasswordagainText = (EditText) rootView.findViewById(R.id.input_passwordagain);
        mSignupButton = (Button) rootView.findViewById(R.id.btn_signup);
        mLoginLink = (TextView) rootView.findViewById(R.id.link_login);
        mImageView.setImageResource(R.drawable.logo);

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    signup();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = LoginFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, fragment);
                transaction.commit();
            }
        });
        return rootView;
    }

    public void signup() throws Exception {

        if (!validate()) {
            onSignupFailed();
            return;
        }

        mSignupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creation d'un compte...");
        progressDialog.show();

        String name = mNameText.getText().toString();
        String email = mEmailText.getText().toString();
        String mobile = mMobileText.getText().toString();
        String password = mPasswordText.getText().toString();

        boolean exist = false;
        // TODO: Implement your own signup logic here.
        User u = new User(name, email, mobile, password);



        if (!Data.dataUser.contains(u)) {
            Data.dataUser.add(u);
            MainActivity.authenticated = true;
            MainActivity.user = u;
            MainActivity.bdd.insertData(u);

        } else {
            exist = true;
        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        //onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

        if (!exist) {
            Fragment fragment = HomeFragment.newInstance();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, fragment);
            transaction.commit();
        } else {
            onSignupFailed();
        }
    }


    public void onSignupSuccess() {
        mSignupButton.setEnabled(true);

    }

    public void onSignupFailed() {
        Toast.makeText(getContext(), "Signup echoue", Toast.LENGTH_LONG).show();
        mSignupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = mNameText.getText().toString();
        String email = mEmailText.getText().toString();
        String mobile = mMobileText.getText().toString();
        String password = mPasswordText.getText().toString();
        String passwordagain = mPasswordagainText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            mNameText.setError("Au moins 3 caractere");
            valid = false;
        } else {
            mNameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailText.setError("E-mail invalide");
            valid = false;
        } else {
            mEmailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            mMobileText.setError("Mobile invalide");
            valid = false;
        } else {
            mMobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPasswordText.setError("Plus que 6 caracteres alphanumeriques");
            valid = false;
        } else {
            mPasswordText.setError(null);
        }

        if (passwordagain.isEmpty() || passwordagain.length() < 6 || !(passwordagain.equals(password))) {
            mPasswordagainText.setError("Erreur password");
            valid = false;
        } else {
            mPasswordagainText.setError(null);
        }

        return valid;
    }


}
