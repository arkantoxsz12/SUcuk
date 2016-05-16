package com.sucuk.sucuk;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasketActivity extends Activity{
    final Context context=this;
    String payment="";
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        dialog= new Dialog(context);
        Cursor cursor = getContentResolver().query(OrderProvider.CONTENT_URI,DBOpenHelper.ALL_COLUMNS,null,null,null);
        String [] from = {DBOpenHelper.MENU_NAME,DBOpenHelper.MENU_COUNT};
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
                sendToast("Item removed from basket");

                return true;
            }
        });

    }
    public void cancelAll(View v)
    {
        Uri uri = Uri.parse(OrderProvider.CONTENT_URI+"/");
        getContentResolver().delete(uri, "1=1", null);
        sendToast("All items removed from basket");
    }
    public void order(View v){

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
                    OrderItem order = new OrderItem();
                    order.setAddress(editAddress.getText().toString());
                    order.setName(LoginActivity.user);
                    order.setPhone(editPhone.getText().toString());
                    order.setDate(getNow());
                    order.setOrder(getOrder());
                    order.setPayment(payment);
                    order.setStatus("In Progress");
                    order.setId(randomID);
                    ref.setValue(order);
                    dialog.hide();
                    Toast.makeText(BasketActivity.this, "Order taken!", Toast.LENGTH_SHORT);
                    Uri uri = Uri.parse(OrderProvider.CONTENT_URI+"/");
                    getContentResolver().delete(uri, "1=1", null);
                }

            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
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
    protected void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
    }
}