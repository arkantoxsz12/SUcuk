package com.sucuk.sucuk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;

import java.util.ArrayList;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Firebase firebase;
    Authentication mRootRef;
    ArrayList<String> mMessages = new ArrayList<>();
    TextView textView;
    Button btnLogout;
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);
        mRootRef=new Authentication(Authentication.FIREBASE_URL);
        btnLogout = (Button) findViewById(R.id.btn_Logout);
        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mRootRef.userLogout();
            }
        });
        //mRootRef.userCreate("dgur@gmail.com","cum");
        mRootRef.userLogin("dgur@gmail.com","cum");
    }
    public void sendToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
