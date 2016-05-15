package com.sucuk.sucuk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailsActivity extends Activity {

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
            result+="\nPrice: "+price;
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
                result+="\nPrice: "+price+"\n";

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
            result+="\nPrice: "+price;
        }

        return result;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

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
