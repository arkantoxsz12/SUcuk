package com.sucuk.sucuk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Authentication mRootRef;
    Button btnLogout;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);
        mRootRef=new Authentication();
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPass = (EditText) findViewById(R.id.etPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final String email=etEmail.getText().toString().trim();
                String pass=etPass.getText().toString().trim();
                Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Log.d("Login","success");
                        Log.d("Login UID",authData.getUid().toString());
                        Intent intent = new Intent(LoginActivity.this,RestaurantActivity.class);
                        startActivityForResult(intent,2);
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("uname",email.substring(0,email.indexOf('@')));
                        mRootRef.child("users").child(authData.getUid()).setValue(map);
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Log.d("Login","Fail");
                    }

                };
                mRootRef.authWithPassword(email,pass,authResultHandler);
            }
        });
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mRootRef.userLogout();
            }
        });
        //mRootRef.userCreate("dgur@gmail.com","cum")
    }
    public void sendToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
