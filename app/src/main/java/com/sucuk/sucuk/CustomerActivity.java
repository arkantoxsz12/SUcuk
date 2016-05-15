package com.sucuk.sucuk;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.VideoView;

public class CustomerActivity extends Activity {

    Button btnPiazza,btnPigastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        btnPiazza= (Button) findViewById(R.id.btnPiazza);
        btnPiazza.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this,TrialActivity.class);
                intent.putExtra("Rest","Piazza");
                startActivityForResult(intent,1);
            }
        });

        btnPigastro= (Button) findViewById(R.id.btnPigastro);
        btnPigastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this,TrialActivity.class);
                intent.putExtra("Rest","Pigastro");
                startActivityForResult(intent,1);
            }
        });
    }



}
