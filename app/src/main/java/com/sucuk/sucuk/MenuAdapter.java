package com.sucuk.sucuk;

import android.app.Activity;
;
import android.view.View;

import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;


public class MenuAdapter extends FirebaseListAdapter<MenuItem> {


    public MenuAdapter(Activity activity, Class<MenuItem> modelClass, int modelLayout, Firebase ref) {
        super(activity, modelClass,R.layout.meal_list_item, ref);
    }

    @Override
    protected void populateView(View view, MenuItem menuItem, int i) {
        ((TextView)view.findViewById(R.id.mealPrice)).setText(Double.toString(menuItem.getPrice()));
        ((TextView)view.findViewById(R.id.mealName)).setText(menuItem.getName());
    }

}
