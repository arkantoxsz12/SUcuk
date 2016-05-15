package com.sucuk.sucuk;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

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
    public void userCreate(final String email, final String password)
    {
        Firebase.ResultHandler resultHandler = new Firebase.ResultHandler(){
            @Override
            public void onError(FirebaseError firebaseError) {
                Log.d("Creation","failed");
            }

            @Override
            public void onSuccess() {

                Log.d("Creation","success");
            }
        };
        createUser(email,password,resultHandler);
    }
    public void userLogout()
    {
        unauth();
    }
}
