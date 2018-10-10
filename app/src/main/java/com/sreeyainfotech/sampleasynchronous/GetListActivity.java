package com.sreeyainfotech.sampleasynchronous;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sreeyainfotech.sampleasynchronous.adapter.OfferPageAdapter;
import com.sreeyainfotech.sampleasynchronous.asynchronous.OfferAsync;
import com.sreeyainfotech.sampleasynchronous.interfaces.OfferAsyncResponse;
import com.sreeyainfotech.sampleasynchronous.model.OffersList;
import com.sreeyainfotech.sampleasynchronous.network.WebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetListActivity extends AppCompatActivity {

    private ArrayList<OffersList> offerList = new ArrayList<OffersList>();
    private OfferAsync offerAsync;
    OfferAsyncResponse offerAsyncResponce;
    private TextView offer_code_textview,offer_value_textview;
    private RecyclerView offer_recycle_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getlist);
        
        offer_code_textview = (TextView) findViewById(R.id.offer_code_textview);
        offer_value_textview = (TextView) findViewById(R.id.offer_value_textview);
        offer_recycle_view = (RecyclerView) findViewById(R.id.offer_recycle_view);

        try {
            if (WebServices.isNetworkAvailable(GetListActivity.this)) {
                getOffers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getOffers() {
        offerAsync = new OfferAsync(GetListActivity.this, WebServices.OFFERS_SERVICE);
        offerAsync.execute();
        offerAsync.delegate = new OfferAsyncResponse() {
            @Override
            public void offerAsyncResponce(JSONObject result) {
                try {
                    offer_value_textview.setText(String.valueOf(result.getString("content")));
                    offer_code_textview.setText(String.valueOf("Use Code : " + result.getString("Use PromoCode")));
                    if (result.has("offers")) {
                        JSONArray orderArray = result.getJSONArray("offers");

                        for (int i = 0; i < orderArray.length(); i++) {
                            OffersList quick = new OffersList(orderArray.getJSONObject(i));
                            offerList.add(quick);
                        }
                    }

                    OfferPageAdapter adapter = new OfferPageAdapter(GetListActivity.this, offerList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GetListActivity.this);
                    offer_recycle_view.setLayoutManager(layoutManager);
                    offer_recycle_view.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

    }

}
