package com.sucuk.sucuk.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.sucuk.sucuk.Adapter.MenuAdapter;
import com.sucuk.sucuk.DBOpenHelper;
import com.sucuk.sucuk.OrderProvider;
import com.sucuk.sucuk.R;

import at.markushi.ui.CircleButton;

public class MenuActivity extends Activity {

    ListView list;
    CircleButton btnBskt;
    String restaurant=CustomerActivity.restaurantID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnBskt = (CircleButton) findViewById(R.id.btnBskt);

        btnBskt.setVisibility(View.GONE);
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
                System.out.println("asdfaf");
                System.out.println(id);
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
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position, long id){
                com.sucuk.sucuk.Bean.MenuItem item = (com.sucuk.sucuk.Bean.MenuItem) parent.getItemAtPosition(position);
                ContentValues values = new ContentValues();
                values.put(DBOpenHelper.MENU_NAME,item.getName());
                values.put(DBOpenHelper.MENU_PRICE,item.getPrice());
                getContentResolver().insert(OrderProvider.CONTENT_URI,values);
                sendToast(item.getName()+" added to the basket");
                if(btnBskt.getVisibility()==View.GONE) {
                    btnBskt.setVisibility(View.VISIBLE);
                }
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
    public void sendToast(String message) {
        Toast.makeText(MenuActivity.this, message, Toast.LENGTH_SHORT).show();
    }
    public void toBasket(View v) {
        Intent intent = new Intent(MenuActivity.this, BasketActivity.class);
        startActivity(intent);
    }
}