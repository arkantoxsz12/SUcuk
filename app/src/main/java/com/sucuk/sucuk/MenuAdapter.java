package com.sucuk.sucuk;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class MenuAdapter extends CursorAdapter {
    public MenuAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.meal_list_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView mealName = (TextView) view.findViewById(R.id.mealName);
        TextView mealPrice = (TextView) view.findViewById(R.id.mealPrice);

    }
}
