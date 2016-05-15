package com.sucuk.sucuk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {

    CustomFirebase rootRef;
    //pass is rest
    private static final int CUSTOMER_REQUEST_CODE = 1001;
    private Button btnLogout;
    private Button btnLogin;
    private Button btnSignup;
    private ProgressBar pB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);
        rootRef=new CustomFirebase();

        btnSignup = (Button) findViewById(R.id.btnSignup);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPass = (EditText) findViewById(R.id.etPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        pB = (ProgressBar)findViewById(R.id.progressBar);
        pB.setVisibility(View.GONE);

        btnSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                rootRef.userCreate(etEmail.getText().toString().trim(),etPass.getText().toString().trim());
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                pB.setVisibility(View.VISIBLE);
                final String email=etEmail.getText().toString().trim();
                String pass=etPass.getText().toString().trim();
                Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Log.d("Login","success");
                        Log.d("Login UID",authData.getUid().toString());

                        Map<String, String> map = new HashMap<String, String>();
                        String uname=email.substring(0,email.indexOf('@'));
                        map.put("uname",uname);
                        map.put("role","c");
                        rootRef.child("users").child(authData.getUid()).setValue(map);
                        pB.setVisibility(View.GONE);
                        Intent intent = new Intent(LoginActivity.this,CustomerActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Log.d("Login","Fail");
                        pB.setVisibility(View.GONE);
                    }
                };
                rootRef.authWithPassword(email,pass,authResultHandler);
            }
        });
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                rootRef.userLogout();
            }
        });
    }
    public void sendToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
