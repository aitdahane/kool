package com.example.med.bottommenuapp.adapters;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.med.bottommenuapp.HomeFragment;
import com.example.med.bottommenuapp.InfoFragment;
import com.example.med.bottommenuapp.MainActivity;
import com.example.med.bottommenuapp.MenuFragment;
import com.example.med.bottommenuapp.ProfileFragment;
import com.example.med.bottommenuapp.R;
import com.example.med.bottommenuapp.models.Cart;
import com.example.med.bottommenuapp.models.Restaurant;

import java.util.ArrayList;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    private ArrayList<Restaurant> mData;
    long idResto;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardvRestaurant;
        private ImageView mImgResto;
        private TextView mTvTitle;
        private RatingBar mRtbRating;
        private Button mBtnMenu;
        private Button mBtnInfos;
        private Button mBtnDistance;


        public ViewHolder(CardView v) {
            super(v);
            mCardvRestaurant = v;
            mImgResto = (ImageView) v.findViewById(R.id.img_resto);
            mTvTitle = (TextView) v.findViewById(R.id.tv_resto_title);
            mRtbRating = (RatingBar) v.findViewById(R.id.rtb_resto_rating);
            mBtnMenu = (Button) v.findViewById(R.id.btn_menu);
            mBtnInfos = (Button) v.findViewById(R.id.btn_infos);
            mBtnDistance = (Button) v.findViewById(R.id.btn_distance);
        }
    }

    public RestaurantAdapter(ArrayList<Restaurant> data) {
        mData = data;
    }

    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_restaurant, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mImgResto.setImageResource(mData.get(position).getIdImg());
        holder.mTvTitle.setText(mData.get(position).getTitle());
        holder.mRtbRating.setRating(Float.valueOf(mData.get(position).getRating()));
        holder.mBtnDistance.setText(mData.get(position).getDistance());

        holder.mBtnInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idResto = mData.get(position).getId();
                FragmentTransaction transaction = ((AppCompatActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, InfoFragment.newInstance(idResto));
                transaction.commit();
            }
        });
        holder.mBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idResto = mData.get(position).getId();
                Cart.commande.setIdResto(idResto);
                FragmentTransaction transaction = ((AppCompatActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, MenuFragment.newInstance(idResto));
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}



