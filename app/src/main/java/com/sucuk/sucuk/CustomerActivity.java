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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        btnPiazza= (ImageView) findViewById(R.id.imgpiazza);
        btnPiazza.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this,MenuActivity.class);
                intent.putExtra("Rest","Piazza");
                startActivityForResult(intent,1);
            }
        });

        btnPigastro= (ImageView) findViewById(R.id.imgpigastro);
        btnPigastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this,MenuActivity.class);
                intent.putExtra("Rest","Pigastro");
                startActivityForResult(intent,1);
            }
        });
        btnInn= (ImageView) findViewById(R.id.imginncafe);
        btnInn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this,MenuActivity.class);
                intent.putExtra("Rest","Inncafe");
                startActivityForResult(intent,1);
            }
        });

        btnKopuklu= (ImageView) findViewById(R.id.imgkopuklu);
        btnKopuklu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this,MenuActivity.class);
                intent.putExtra("Rest","Kopuklu");
                startActivityForResult(intent,1);
            }
        });
        btnSima= (ImageView) findViewById(R.id.imgsima);
        btnSima.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this,MenuActivity.class);
                intent.putExtra("Rest","Sima");
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    public void onBackPressed(){
        finish();
    }



}
