package com.sucuk.sucuk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;



public class OrderAdapter extends FirebaseListAdapter<MenuItem> {

    public OrderAdapter(Query ref, Activity activity) {
        super(activity, MenuItem.class, R.layout.order_list_item, ref);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void populateView(View view, MenuItem order, int i) {
        ((TextView) view.findViewById(R.id.orderPrice)).setText(Double.toString(order.getPrice()));
        ((TextView) view.findViewById(R.id.orderNumber)).setText(order.getName());
    }

}

