package com.sucuk.sucuk.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.sucuk.sucuk.DBOpenHelper;
import com.sucuk.sucuk.R;

/**
 * Created by eralpsahin on 5/18/2016.
 */
public class BasketAdapter extends CursorAdapter {

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int number=1;
    public BasketAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_basket,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String menuName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.MENU_NAME));
        TextView tvMenu = (TextView) view.findViewById(R.id.tvMenu);
        Double menuPrice = cursor.getDouble(cursor.getColumnIndex(DBOpenHelper.MENU_PRICE));
        Integer menuCount = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.MENU_COUNT));
        TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        tvMenu.setText(menuName);
        tvPrice.setText(String.valueOf(menuCount*menuPrice));
        TextView tvNumber = (TextView) view.findViewById(R.id.tvNumber);
        tvNumber.setText(String.valueOf(number));
        number++;
    }


}
