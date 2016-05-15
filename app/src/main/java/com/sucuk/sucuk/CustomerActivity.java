package com.sucuk.sucuk;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class CustomerActivity extends Activity {

    ImageView btnPiazza,btnPigastro,btnInn,btnKopuklu,btnSima;
    public static int restaurantID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
    }
    public void sendToMenu(View v)
    {
        Intent intent = new Intent(CustomerActivity.this,MenuActivity.class);
        restaurantID=v.getId();
        intent.putExtra("Rest",v.getId());
        startActivityForResult(intent,1);
    }
    @Override
    public void onBackPressed(){
        finish();
    }
}
