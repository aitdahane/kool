package com.example.med.bottommenuapp.adapters;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.med.bottommenuapp.BasketFragment;
import com.example.med.bottommenuapp.R;
import com.example.med.bottommenuapp.models.Cart;
import com.example.med.bottommenuapp.models.Data;
import com.example.med.bottommenuapp.models.Plat;

import java.util.ArrayList;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<Plat> mData;



    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImgPlat;
        private TextView mTvTitle;
        private  TextView mTvPrice;
        private TextView mTvQuantity;
        private Button mImBtnPlus;
        private Button mImBtnMinus;

        public ViewHolder(CardView v) {
            super(v);
            mImgPlat = (ImageView) v.findViewById(R.id.img_cart);
            mTvTitle = (TextView) v.findViewById(R.id.tv_title_cart);
            mTvPrice = (TextView) v.findViewById(R.id.tv_price_cart);
            mTvQuantity = (TextView) v.findViewById(R.id.tv_quantity_cart);
            mImBtnPlus = (Button) v.findViewById(R.id.imbtn_plus_cart);
            mImBtnMinus = (Button) v.findViewById(R.id.imbtn_minus_cart);

        }
    }

    public CartAdapter(ArrayList<Plat> data) {
        mData = data;
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_cart, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mImgPlat.setImageResource(mData.get(position).getIdImg());
        holder.mTvTitle.setText(mData.get(position).getTitle());
        holder.mTvPrice.setText(String.valueOf(mData.get(position).getPrice()));
        holder.mTvQuantity.setText(mData.get(position).getQuantity()+"");

        holder.mImBtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mData.get(position).setQuantity(mData.get(position).getQuantity() + 1);
                    holder.mTvQuantity.setText(mData.get(position).getQuantity() + "");
                    BasketFragment.mTvSolde.setText(Cart.totalCart()+" DH");

            }
        });
        holder.mImBtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.get(position).setQuantity(mData.get(position).getQuantity() - 1);
                holder.mTvQuantity.setText(mData.get(position).getQuantity() + "");
                BasketFragment.mTvSolde.setText(Cart.totalCart() + " DH");
                if(mData.get(position).getQuantity() == 0) {
                    mData.remove(position);
                    FragmentTransaction transaction = ((AppCompatActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, BasketFragment.newInstance());
                    transaction.commit();
                }




            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}



