package com.sucuk.sucuk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Firebase firebase;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);
    }
    @Override
    protected void onStart() {
        super.onStart();
        textView = (TextView) findViewById(R.id.textView);
        firebase    = new Firebase("https://dazzling-torch-792.firebaseio.com/elma");

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                textView.setText(data);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
