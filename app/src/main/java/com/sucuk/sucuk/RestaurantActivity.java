package com.sucuk.sucuk;

import android.app.Activity;
import android.os.Bundle;

public class RestaurantActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        setTitle("Orders");
    }
    @Override
    public void onBackPressed(){
        finish();
    }
}
