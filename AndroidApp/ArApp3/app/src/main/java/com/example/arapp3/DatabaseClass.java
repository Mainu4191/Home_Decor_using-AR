package com.example.arapp3;

import android.content.Context;

public class DatabaseClass {
    public static final String DATABASE_NAME = "cart.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "cart";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_TOTAL = "total";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_PRICE + " TEXT, " + COLUMN_IMAGE + " TEXT, " + COLUMN_QUANTITY + " INTEGER, " + COLUMN_TOTAL + " TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;





}
