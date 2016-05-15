package com.sucuk.sucuk;

import android.app.Activity;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.Firebase;

public class RestaurantActivity extends Activity {
    ListView list;
    String rest;
    public String getRestaurant(){
        return  rest;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        setTitle("Orders");
    }

    @Override
    public void onStart(){super.onStart();
        list = (ListView) findViewById(R.id.orderList);
        Firebase ref = new Firebase("https://dazzling-torch-792.firebaseio.com").child("restaurants").child(getRestaurant()).child("menu");
        final ListAdapter adapter = new OrderAdapter(ref, this);
        list.setAdapter(adapter);
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                list.setSelection(adapter.getCount() - 1);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }
    @Override
    public void onBackPressed(){
        finish();
    }
}
