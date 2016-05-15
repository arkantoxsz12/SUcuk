package com.sucuk.sucuk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        ((TextView) findViewById(R.id.name)).setText(order.getName());
        ((TextView) findViewById(R.id.orderDate)).setText(order.getDate());
    }
}
