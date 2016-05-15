package com.sucuk.sucuk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eralpsahin on 5/15/2016.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sucuk.db";
    private static final int DATABASE_VERSION = 2;

    //Constants for identifying table and columns
    public static final String TABLE_ORDER = "orders";
    public static final String ORDER_ID = "_id";
    public static final String MENU_NAME = "menuName";
    public static final String MENU_PRICE = "menuPrice";

    public static final String[] ALL_COLUMNS = {ORDER_ID,MENU_NAME,MENU_PRICE};
    //SQL to create table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_ORDER + " (" +
                    ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MENU_NAME + " TEXT, " +
                    MENU_PRICE + " INTEGER " +
                    ")";


    public DBOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ORDER);
        onCreate(db);
    }
}
