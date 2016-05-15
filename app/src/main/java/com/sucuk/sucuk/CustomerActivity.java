package com.sucuk.sucuk;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class CustomerActivity extends Activity {

    public static int restaurantID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.MENU_NAME," hello");
        values.put(DBOpenHelper.MENU_PRICE,22);
        Uri uri =getContentResolver().insert(OrderProvider.CONTENT_URI,values);

        Log.d("Tag","Inserted order "+uri.getLastPathSegment());
    }
    public void sendToMenu(View v)
    {
        Intent intent = new Intent(CustomerActivity.this,MenuActivity.class);
        restaurantID=v.getId();
        startActivityForResult(intent,1);
    }
    @Override
    public void onBackPressed(){
        finish();
    }
}
