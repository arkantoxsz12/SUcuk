package com.sucuk.sucuk;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class BasketActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        Cursor cursor = getContentResolver().query(OrderProvider.CONTENT_URI,DBOpenHelper.ALL_COLUMNS,null,null,null);
        String [] from = {DBOpenHelper.MENU_NAME,DBOpenHelper.MENU_PRICE};
        int[] to = {android.R.id.text1,android.R.id.text2};
        final CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,from,to,0);
        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);
        list.setLongClickable(true);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                Uri uri = Uri.parse(OrderProvider.CONTENT_URI+"/"+id);
                String noteFilter = DBOpenHelper.ORDER_ID + "=" + uri.getLastPathSegment();
                System.out.println(noteFilter);
                getContentResolver().delete(uri,noteFilter,null);
                sendToast("Item removed basket");
                cursorAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basket, menu);
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
        Intent intent = new Intent();
        intent.putExtra("Rest",CustomerActivity.restaurantID);
        setResult(RESULT_OK,intent);
        super.onBackPressed();
    }

    public void sendToast(String message) {
        Toast.makeText(BasketActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}