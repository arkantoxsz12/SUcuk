package com.sucuk.sucuk;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eralpsahin on 5/14/2016.
 */
public class CustomFirebase extends Firebase{
    static public final String FIREBASE_URL = "https://dazzling-torch-792.firebaseio.com";
    public CustomFirebase() {
        super(FIREBASE_URL);
    }

    public CustomFirebase(String url) {
        super(FIREBASE_URL+"/"+url);
    }
            public void userCreate ( final String email, final String password)
            {
                Firebase.ResultHandler resultHandler = new Firebase.ResultHandler() {
                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Log.d("Creation", "failed");
                    }

                    @Override
                    public void onSuccess() {
                        Log.d("Creation", "success");
                        authWithPassword(email,password,new AuthResultHandler(){
                            @Override
                            public void onAuthenticated(AuthData authData) {
                                Map<String, String> map = new HashMap<String, String>();
                                String uname = email.substring(0, email.indexOf('@'));
                                Log.d("s","b");
                                map.put("uname", uname);
                                map.put("role", "c");
                                child("users").child(authData.getUid()).setValue(map);
                            }

                            @Override
                            public void onAuthenticationError(FirebaseError firebaseError) {
                                Log.d("s","a");
                            }
                        });
                    }
                };
                createUser(email, password, resultHandler);


            }

            public void userLogout () {
                unauth();
            }

    };


