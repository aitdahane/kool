package com.example.med.bottommenuapp;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.med.bottommenuapp.adapters.CartAdapter;
import com.example.med.bottommenuapp.models.Cart;
import com.example.med.bottommenuapp.models.Commande;
import com.example.med.bottommenuapp.models.Data;
import com.example.med.bottommenuapp.models.Plat;



import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class BasketFragment extends Fragment {

    private RecyclerView mRecvListCart;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mCartAdapter;
    public static TextView mTvSolde;
    public  Button mBtnOrder;

    String s;


    public BasketFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BasketFragment newInstance() {
        BasketFragment fragment = new BasketFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_basket, container, false);
        mRecvListCart = (RecyclerView) rootView.findViewById(R.id.recv_list_cart);
        mRecvListCart.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecvListCart.setLayoutManager(mLayoutManager);

        mCartAdapter = new CartAdapter(Cart.dataCart);
        mRecvListCart.setAdapter(mCartAdapter);


        mTvSolde = (TextView) rootView.findViewById(R.id.tv_solde);
        mTvSolde.setText(Cart.totalCart()+"0 DH");
        mBtnOrder = (Button) rootView.findViewById(R.id.btn_order);
        mBtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.authenticated) {
                    Cart.commande.setPrice(Cart.totalCart());
                    if (Cart.commande.getPrice() == 0) {
                        Toast.makeText(getContext(),"Commande vide!",Toast.LENGTH_SHORT).show();
                    } else {
                        String com = creeCommande();
                        envoiCommande(com);
                        Toast.makeText(getContext(),"Commande envoyée",Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(),"Vous n'êtes pas connecté!",Toast.LENGTH_SHORT).show();
                }
            }
        });


        return rootView;
    }

    private void envoiCommande(final String com) {
        new Thread(new Runnable() {
            public void run() {
                try{

                    String urlstring ="http://192.168.1.134:9090/SendCommand?commande="+com;
                    urlstring = urlstring.replace(' ','_');
                    URL url =new URL(urlstring);
                    Log.d("TAG",url.toString());
                    HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                    int s = urlConnection.getResponseCode();
                    Log.d("TAG",""+s);
                    //urlConnection.setDoInput(true);//urlConnection.setDoOutput(true);//urlConnection.setRequestMethod("GET");
                    Log.d("TAG","2");
                    urlConnection.connect();
                    Log.d("TAG","3");
                }catch(Exception e)
                {
                    Log.d("TAG","NOK: "+e);
                }
            }
        }).start();
        Log.d("TAG","bla");

    }

    public String creeCommande() {
        String s = "";
        s += Cart.commande.toString();
        for(Plat plat:Cart.dataCart) {
            s += ";"+plat.toString();
        }
        return s;
    }






}
