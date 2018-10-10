package com.sreeyainfotech.sampleasynchronous.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sreeyainfotech.sampleasynchronous.CategoryActivity;
import com.sreeyainfotech.sampleasynchronous.R;
import com.sreeyainfotech.sampleasynchronous.model.OffersList;

import java.util.ArrayList;

public class OfferPageAdapter extends RecyclerView.Adapter<OfferPageAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<OffersList> offerList;

    public OfferPageAdapter(Context mContext, ArrayList<OffersList> fromScreen) {
        this.mContext = mContext;
        this.offerList = fromScreen;


    }

    @SuppressLint("NewApi")
    @Override
    public OfferPageAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        viewGroup.setClipToPadding(false);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_row, viewGroup, false);
        return new OfferPageAdapter.ViewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final OfferPageAdapter.ViewHolder holder, final int position) {

        final OffersList offerlist = offerList.get(position);
        holder.textView1.setText(offerlist.getContent());
        Picasso.with(mContext).load(offerlist.getImagepath()).into(holder.imageView1);
        holder.layout1l.setContentDescription(offerlist.getContent());
        holder.textView1.setTag(position);
        holder.layout1l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, CategoryActivity.class);
                intent.putExtra("Offers_Model", offerlist);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;
        ImageView imageView1;
        LinearLayout layout1l;

        public ViewHolder(View view) {
            super(view);
            textView1 = (TextView)view.findViewById(R.id.textView1);
            imageView1=(ImageView)view.findViewById(R.id.imageView1);
            layout1l=(LinearLayout)view.findViewById(R.id.layout1);
        }
    }
}

