package com.sucuk.sucuk;

import android.app.Activity;
;
import android.view.View;

import android.widget.TextView;


import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;


public class MenuAdapter extends FirebaseListAdapter<MenuItem> {


    public MenuAdapter(Query ref,Activity activity) {
        super(activity,MenuItem.class,R.layout.meal_list_item, ref);
    }

    @Override
    protected void populateView(View view, MenuItem menuItem, int i) {
        ((TextView)view.findViewById(R.id.mealPrice)).setText(Double.toString(menuItem.getPrice())+" TL");
        ((TextView)view.findViewById(R.id.mealName)).setText(menuItem.getName());
    }

}
