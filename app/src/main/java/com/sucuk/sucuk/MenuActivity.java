package com.sucuk.sucuk;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.Firebase;

import at.markushi.ui.CircleButton;

public class MenuActivity extends Activity {

    ListView list;
    String restaurant="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        switch (CustomerActivity.restaurantID) {
            case 2131492946:
                restaurant="inncafe";
                break;
            case 2131492947:
                restaurant="kopuklu";
                break;
            case 2131492948:
                restaurant="piazza";
                break;
            case 2131492949:
                restaurant="pigastro";
                break;
            case 2131492950:
                restaurant="sima";
                break;
        }
        Log.d("Tag",restaurant);
        list = (ListView) findViewById(R.id.menuList);
        Firebase ref = new Firebase("https://dazzling-torch-792.firebaseio.com").child("restaurants").child(restaurant).child("menu");
        final ListAdapter adapter = new MenuAdapter(ref, this);
        list.setAdapter(adapter);
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                list.setSelection(adapter.getCount() - 1);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        list = (ListView) findViewById(R.id.menuList);
        Firebase ref = new Firebase("https://dazzling-torch-792.firebaseio.com").child("restaurants").child(restaurant).child("menu");
        final ListAdapter adapter = new MenuAdapter(ref, this);
        list.setAdapter(adapter);
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                list.setSelection(adapter.getCount() - 1);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void toBasket(View v) {
        Intent intent = new Intent(MenuActivity.this, BasketActivity.class);
        startActivity(intent);
    }
}
