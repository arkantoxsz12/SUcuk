package com.sucuk.sucuk;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.Firebase;

public class RestaurantActivity extends Activity {
    ListView list;
    public static com.sucuk.sucuk.OrderItem item;

    public static String getRestaurant() {
        return LoginActivity.user.substring(0, LoginActivity.user.indexOf("@"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        setTitle("Orders");
    }

    @Override
    public void onStart() {
        super.onStart();
        list = (ListView) findViewById(R.id.orderList);
        Firebase ref = new Firebase("https://dazzling-torch-792.firebaseio.com").child("orders").child(getRestaurant());
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
                item = (com.sucuk.sucuk.OrderItem) parent.getItemAtPosition(position);

                Intent intent = new Intent(RestaurantActivity.this, OrderDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
