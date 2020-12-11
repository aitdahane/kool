package com.example.med.bottommenuapp.adapters;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.med.bottommenuapp.MainActivity;
import com.example.med.bottommenuapp.MenuFragment;
import com.example.med.bottommenuapp.R;
import com.example.med.bottommenuapp.models.Cart;
import com.example.med.bottommenuapp.models.Data;
import com.example.med.bottommenuapp.models.Plat;

import java.util.ArrayList;


public class PlatAdapter extends RecyclerView.Adapter<PlatAdapter.ViewHolder> {
    private ArrayList<Plat> mData;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardvMenu;
        private ImageView mImgPlat;
        private TextView mTvTitle;
        private  TextView mTvPrice;
        private ImageButton mImBtnAddCart;

        public ViewHolder(CardView v) {
            super(v);
            mCardvMenu = v;
            mImgPlat = (ImageView) v.findViewById(R.id.img_plat);
            mTvTitle = (TextView) v.findViewById(R.id.tv_plat_title);
            mTvPrice = (TextView) v.findViewById(R.id.tv_price);
            mImBtnAddCart = (ImageButton) v.findViewById(R.id.imbtn_add_cart);

        }
    }

    public PlatAdapter(ArrayList<Plat> data) {
        mData = data;
    }

    @Override
    public PlatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_menu, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mImgPlat.setImageResource(mData.get(position).getIdImg());
        holder.mTvTitle.setText(mData.get(position).getTitle());
        holder.mTvPrice.setText(String.valueOf(mData.get(position).getPrice()));
        holder.mImBtnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.get(position).getQuantity() == 0) {
                    mData.get(position).setQuantity(1);
                    Cart.dataCart.add(mData.get(position));
                    Toast.makeText(v.getContext(),"Plat ajouté",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}



