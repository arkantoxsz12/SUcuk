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
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

public class LoginActivity extends Activity {

    CustomFirebase rootRef;
    private Button btnLogout;
    private Button btnLogin;
    private Button btnSignup;
    private ProgressBar pB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);
        rootRef = new CustomFirebase();
        btnSignup = (Button) findViewById(R.id.btnSignup);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPass = (EditText) findViewById(R.id.etPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        pB = (ProgressBar) findViewById(R.id.progressBar);
        pB.setVisibility(View.GONE);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootRef.userCreate(etEmail.getText().toString().trim(), etPass.getText().toString().trim());
                sendToast("User created!");
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pB.setVisibility(View.VISIBLE);

                final String email = etEmail.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(final AuthData authData) {


                        CustomFirebase cf = new CustomFirebase("users");
                        Query q=cf.child(authData.getUid());
                        q.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                if(dataSnapshot.getKey().equals("role") &&dataSnapshot.getValue().equals("r"))
                                {
                                    pB.setVisibility(View.GONE);
                                    Intent intent = new Intent(LoginActivity.this, RestaurantActivity.class);
                                    startActivity(intent);

                                }
                                else if(dataSnapshot.getKey().equals("role") &&dataSnapshot.getValue().equals("c"))
                                {
                                    pB.setVisibility(View.GONE);
                                    Intent intent = new Intent(LoginActivity.this, CustomerActivity.class);
                                    startActivity(intent);
                                }
                            }
                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                            }
                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {
                            }
                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                            }
                        });
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        sendToast("Wrong credentials");
                        Log.d("Login", "Fail");
                        pB.setVisibility(View.GONE);
                    }
                };
                rootRef.authWithPassword(email, pass, authResultHandler);
            }
        });
    }
    public void sendToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
