package com.example.med.bottommenuapp;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.med.bottommenuapp.models.User;

import java.util.Iterator;


public class LoginFragment extends Fragment {

    ImageView mImageView;
    EditText mEmailText;
    EditText mPasswordText;
    Button mLoginButton;
    TextView mSignupLink;


    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        mImageView = (ImageView) rootView.findViewById(R.id.img_login);
        mEmailText = (EditText) rootView.findViewById(R.id.input_email);
        mPasswordText = (EditText) rootView.findViewById(R.id.input_password);
        mLoginButton = (Button) rootView.findViewById(R.id.btn_login);
        mSignupLink = (TextView) rootView.findViewById(R.id.link_signup);
        mImageView.setImageResource(R.drawable.logo);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        mSignupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment fragment = SignupFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, fragment);
                transaction.commit();

            }
        });
        return rootView;
    }

    public void login() {


        if (!validate()) {
            onLoginFailed();
            return;
        }

        mLoginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authentification...");
        progressDialog.show();

        String email = mEmailText.getText().toString();
        String password = mPasswordText.getText().toString();

        // TODO: Implement your own authentication logic here.
        boolean exist = false;
        Iterator<User> iterator = Data.dataUser.iterator();
        while(iterator.hasNext()) {
            User u = (User) iterator.next();
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                exist = true;
                MainActivity.authenticated = true;
                MainActivity.user = u;
                break;
            }
        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        //onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
        if (exist) {
            Fragment fragment = HomeFragment.newInstance();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, fragment);
            transaction.commit();
        } else {
            onLoginFailed();
        }
    }

    public void onLoginSuccess() {
        mLoginButton.setEnabled(true);
        Toast.makeText(getContext(), "Login reussi", Toast.LENGTH_LONG).show();

    }

    public void onLoginFailed() {
        Toast.makeText(getContext(), "Login echoue", Toast.LENGTH_LONG).show();
        mLoginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String email = mEmailText.getText().toString();
        String password = mPasswordText.getText().toString();
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailText.setError("E-mail invalide");
            valid = false;
        } else {
            mEmailText.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            mPasswordText.setError("Plus que 6 caracteres alphanumeriques");
            valid = false;
        } else {
            mPasswordText.setError(null);
        }
        return valid;
    }

}
