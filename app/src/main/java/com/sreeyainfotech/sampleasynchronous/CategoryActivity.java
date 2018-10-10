package com.sreeyainfotech.sampleasynchronous;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sreeyainfotech.sampleasynchronous.model.OffersList;

public class CategoryActivity  extends AppCompatActivity{
OffersList offersList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catogiry);

       offersList=(OffersList)getIntent().getSerializableExtra("Offers_Model");

        Toast.makeText(getApplicationContext(),offersList.getContent().toString(), Toast.LENGTH_LONG).show();
    }
}
