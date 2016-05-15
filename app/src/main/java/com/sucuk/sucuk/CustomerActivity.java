package com.sucuk.sucuk;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class CustomerActivity extends Activity {

    public static String restaurantID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);



    }
    public void sendToMenu(View v)
    {
        Intent intent = new Intent(CustomerActivity.this,MenuActivity.class);
        switch(v.getId()){
            case R.id.imginncafe:
                restaurantID="inncafe";
                break;
            case R.id.imgkopuklu:
                restaurantID="kopuklu";
                break;
            case R.id.imgpiazza:
                restaurantID="piazza";
                break;
            case R.id.imgsima:
                restaurantID="sima";
                break;
            case R.id.imgpigastro:
                restaurantID="pigastro";
                break;
        }
        startActivityForResult(intent,1);
    }
    @Override
    public void onBackPressed(){
        finish();
    }
}