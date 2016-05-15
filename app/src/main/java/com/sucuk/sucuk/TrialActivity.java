package com.sucuk.sucuk;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TrialActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        System.out.println(bundle.getString("Rest"));
    }
}
