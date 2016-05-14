package com.sucuk.sucuk;

import android.util.Log;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eralpsahin on 5/14/2016.
 */
public class Authentication extends Firebase{
    static public final String FIREBASE_URL = "https://dazzling-torch-792.firebaseio.com";

    public Authentication(String url) {
        super(url);
    }
    public void userCreate(final String email, final String password)
    {
        Firebase.ResultHandler resultHandler = new Firebase.ResultHandler(){
            @Override
            public void onError(FirebaseError firebaseError) {
                Log.d("Creation","failed");
            }

            @Override
            public void onSuccess() {

                userLogin(email,password);
                Log.d("Creation","success");
               //TODO create user account in the JSON
            }
        };
        createUser(email,password,resultHandler);
    }
    public void userLogin(final String email,final String password)
    {
        addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    Log.d("Status","Loggedin");
                }
                else {
                    Log.d("Status","Loggedout");
                }

            }
        });
        Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Log.d("Login","success");
                Log.d("Login UID",authData.getUid().toString());
                Map<String, String> map = new HashMap<String, String>();
                map.put("uname",email.substring(0,email.indexOf('@')));
                child("users").child(authData.getUid()).setValue(map);
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.d("Login","Fail");
            }
        };
        authWithPassword(email,password, authResultHandler);
    }
    public void userLogout()
    {
        unauth();
    }
}
