package com.sucuk.sucuk.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.firebase.client.Firebase;
import com.sucuk.sucuk.Adapter.BasketAdapter;
import com.sucuk.sucuk.Bean.OrderItem;
import com.sucuk.sucuk.CustomFirebase;
import com.sucuk.sucuk.DBOpenHelper;
import com.sucuk.sucuk.OrderProvider;
import com.sucuk.sucuk.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasketActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>{
    final Context context=this;
    String payment="";
    Dialog dialog;
    BasketAdapter cursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        cursorAdapter = new BasketAdapter(this,null,0);
        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);
        if(list.isShown())
            System.out.println("hello");
        else
            System.out.println("heyyllo");
        list.setLongClickable(true);
        getLoaderManager().initLoader(0,null,this);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                Uri uri = Uri.parse(OrderProvider.CONTENT_URI+"/"+id);
                String noteFilter = DBOpenHelper.ORDER_ID + "=" + uri.getLastPathSegment();
                System.out.println(noteFilter);
                getContentResolver().delete(uri,noteFilter,null);
                restartLoader();
                sendToast("Item removed from basket");
                return true;
            }
        });

    }

    private void restartLoader() {
        getLoaderManager().restartLoader(0,null,this);
        cursorAdapter.setNumber(1);
    }

    public void cancelAll(View v)
    {
        Uri uri = Uri.parse(OrderProvider.CONTENT_URI+"/");
        getContentResolver().delete(uri, null, null);
        restartLoader();
        sendToast("All items removed from basket");
    }
    public void order(View v){
        dialog= new Dialog(context);
        dialog.setContentView(R.layout.dialog_basket);
        dialog.setTitle("Payment & Delivery Info");
        Button buttonOK = (Button)dialog.findViewById(R.id.dialogOK);
        Button buttonCancel = (Button) dialog.findViewById(R.id.dialogCancel);
        final EditText editPhone = (EditText) dialog.findViewById(R.id.editPhone);
        final EditText editAddress = (EditText) dialog.findViewById(R.id.editAddress);
        List<String> categories = new ArrayList<String>();
        categories.add("Cash");
        categories.add("Credit Card");
        Spinner spinner = (Spinner) dialog.findViewById(R.id.payment);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, categories);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payment = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                payment="";
            }
        });
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editPhone.getText().toString().length()==0 || payment.length()==0){
                    Toast.makeText(context,"Please check your phone number/payment method",Toast.LENGTH_SHORT).show();
                }else{
                    String randomID = randomID();
                    Firebase ref = new CustomFirebase("orders").child(CustomerActivity.restaurantID).child(randomID);
                    OrderItem order= new OrderItem(editAddress.getText().toString().trim(),getNow(),randomID,getOrder(),LoginActivity.user,payment,"Not seen",editPhone.getText().toString().trim());
                    ref.setValue(order);
                    dialog.hide();
                    dialog.dismiss();
                    sendToast("Order has been placed");
                    Uri uri = Uri.parse(OrderProvider.CONTENT_URI+"/");
                    getContentResolver().delete(uri, null, null);
                    restartLoader();
                }
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private String getOrder() {
        String result="";
        Cursor cursor = getContentResolver().query(OrderProvider.CONTENT_URI,DBOpenHelper.ALL_COLUMNS,null,null,null);
        if(cursor.moveToFirst())
        {
            do{
                String menuItem,menuPrice,menuCount;
                menuItem=cursor.getString(cursor.getColumnIndex(DBOpenHelper.MENU_NAME));
                result+=menuItem+'$';
                menuCount=cursor.getString(cursor.getColumnIndex(DBOpenHelper.MENU_COUNT));
                result+=menuCount+"$";
                menuPrice=cursor.getString(cursor.getColumnIndex(DBOpenHelper.MENU_PRICE));
                result+=menuPrice+'/';
            }while(cursor.moveToNext());
            result=result.substring(0,result.length()-1);
        }
        return result;
    }
    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
    public void sendToast(String message) {
        Toast.makeText(BasketActivity.this, message, Toast.LENGTH_SHORT).show();
    }
    private String randomID(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

        return formatter.format(date);
    }
    private static String getNow(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,OrderProvider.CONTENT_URI,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}