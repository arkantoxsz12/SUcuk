package com.sucuk.sucuk.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.sucuk.sucuk.R;

public class OrderDetailsActivity extends Activity {
    Firebase ref;
    private static String id;
    public  void deny(View v){
        ref.child(id).removeValue();
        Log.d("Tag",id);
        RestaurantActivity.item.setStatus("denied");
        finish();
    }
    public void accept(View v){
        ref.child(id).removeValue();
        Log.d("Tag",id);
        RestaurantActivity.item.setStatus("accepted");
        finish();
    }
    public String parseOrder()
    {
        String word=RestaurantActivity.item.getOrder();
        String result="";
        String food;
        String quantity;
        String price;
        if(!word.contains("/"))
        {
            food=word.substring(0,word.indexOf('$'));
            result=food;
            word=word.substring(word.indexOf('$')+1);
            quantity=word.substring(0,word.indexOf('$'));
            result+=" Quantity: "+quantity;
            word=word.substring(word.indexOf('$')+1);
            price=word;
            Double p=Double.parseDouble(price);
            Double q=Double.parseDouble(quantity);
            price=Double.toString(p*q);
            result+="\nPrice: "+price+" TL";
        }
        else
        {
            while(word.contains("/"))
            {
                String line=word.substring(0,word.indexOf("/"));
                word=word.substring(word.indexOf("/")+1);
                food=line.substring(0,line.indexOf('$'));
                result+=food;
                line=line.substring(line.indexOf('$')+1);
                quantity=line.substring(0,line.indexOf('$'));
                result+=" Quantity: "+quantity;
                line=line.substring(line.indexOf('$')+1);
                price=line;
                Double p=Double.parseDouble(price);
                Double q=Double.parseDouble(quantity);
                price=Double.toString(p*q);
                result+="\nPrice: "+price+" TL"+"\n";

            }
            food=word.substring(0,word.indexOf('$'));
            result+=food;
            word=word.substring(word.indexOf('$')+1);
            quantity=word.substring(0,word.indexOf('$'));
            result+=" Quantity: "+quantity;
            word=word.substring(word.indexOf('$')+1);
            price=word;
            Double p=Double.parseDouble(price);
            Double q=Double.parseDouble(quantity);
            price=Double.toString(p*q);
            result+="\nPrice: "+price+" TL";
        }

        return result;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Firebase.setAndroidContext(this);
        ref=new Firebase("https://dazzling-torch-792.firebaseio.com").child("orders").child(RestaurantActivity.getRestaurant());;

        id=RestaurantActivity.item.getId();
        TextView z = ((TextView) findViewById(R.id.address));
        z.setText(RestaurantActivity.item.getAddress());
        TextView x = ((TextView) findViewById(R.id.payment));
        x.setText(RestaurantActivity.item.getPayment());
        TextView c = ((TextView) findViewById(R.id.phone));
        c.setText(RestaurantActivity.item.getPhone());
        TextView b = ((TextView) findViewById(R.id.order));
        b.setText(parseOrder());
        TextView v = ((TextView) findViewById(R.id.name));
        v.setText(RestaurantActivity.item.getName());
        TextView n = ((TextView) findViewById(R.id.date));
        n.setText(RestaurantActivity.item.getDate());
    }
}
