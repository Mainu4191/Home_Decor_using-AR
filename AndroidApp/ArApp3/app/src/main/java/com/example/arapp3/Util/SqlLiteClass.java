package com.example.arapp3.Util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SqlLiteClass {
    SQLiteDatabase myDatabase;
    Context context;
    String DATABASE_NAME;
    String TAG = "TAG";

    List<?> cartModelList = new ArrayList<>();

    public  SqlLiteClass(){

    }

    public SqlLiteClass(Context context, String DATABASE_NAME){
        this.context = context;
        this.DATABASE_NAME = DATABASE_NAME;
        myDatabase = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);
    }


    public void createDatabase(Context context, String DATABASE_NAME){
        this.context = context;
        this.DATABASE_NAME = DATABASE_NAME;
        myDatabase = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);
    }

    public void showDatabase(){
        String[] databases = context.databaseList();
        for (String database: databases){
            Log.d(TAG, "showDatabase: " + database);
        }
    }

    public void createTable(String TABLE_NAME, String CREATE_TABLE){
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + CREATE_TABLE + ")");
    }

    public void showTables(){
        Cursor c = myDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        int nameIndex = c.getColumnIndex("name");
        Log.d(TAG, "showTables: "+String.valueOf(nameIndex));
        try {
            c.moveToFirst();
            if (c != null && c.moveToFirst()) {
                do{
                    Log.d(TAG, "showTables: " + c.getString(nameIndex));
                }while (c.moveToNext());
            }
        }catch (Exception e){
            Log.d(TAG, "showTables: " + e);
        }

    }

    public void dropTable(String TABLE_NAME){
        myDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void insertData(String TABLE_NAME, String COLUMN_NAME, String COLUMN_VALUE){
      try {
          myDatabase.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ") VALUES (" + COLUMN_VALUE + ")");
          Log.d(TAG, "insertData: " + "Data Inserted");
        } catch (Exception e) {
            Log.d(TAG, "insertData: " + e);
            }

      }

    public void insertData(String TABLE_NAME, String COLUMN_NAME, List<String> COLUMN_VALUE) {
        try {
            String values = "";
            for (int i = 0; i < COLUMN_VALUE.size(); i++) {
                if (i == COLUMN_VALUE.size() - 1) {
                    values += "'" + COLUMN_VALUE.get(i) + "'";
                } else {
                    values += "'" + COLUMN_VALUE.get(i) + "', ";
                }
            }
            myDatabase.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ") VALUES (" + values + ")");
            Log.d(TAG, "insertData: " + "Data Inserted");
        } catch (Exception e) {
            Log.d(TAG, "insertData: " + e);
        }

    }

    public Cursor readData(String TABLE_NAME){
       try {
              Cursor c = myDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
              return c;
       }
         catch (Exception e){
              Log.d(TAG, "readData: " + e);
              return null;
         }
    }

    public List<?> getCartData(String TABLE_NAME){
        Cursor c = myDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        int idIndex = c.getColumnIndex("id");
        int nameIndex = c.getColumnIndex("name");
        int priceIndex = c.getColumnIndex("price");
        int quantityIndex = c.getColumnIndex("quantity");
        int imageIndex = c.getColumnIndex("image");
        c.moveToFirst();
        while (c!=null){
            Log.d("id",c.getString(idIndex));
            Log.d("name",c.getString(nameIndex));
            Log.d("price",c.getString(priceIndex));
            Log.d("quantity",c.getString(quantityIndex));
            Log.d("image",c.getString(imageIndex));
            c.moveToNext();
        }
        c.close();
        return cartModelList;
    }


    public boolean updateQuantity(String TABLE_NAME, String COLUMN_NAME, String COLUMN_VALUE, String ID){
        try {
            myDatabase.execSQL("UPDATE " + TABLE_NAME + " SET " + COLUMN_NAME + " = " + COLUMN_VALUE + " WHERE id = " + ID);
            return true;
        }catch (Exception e){
            Log.d(TAG, "updateQuantity: " + e);
            return false;
        }
    }

    public boolean deleteItem(String TABLE_NAME, String ID){
        try {
            myDatabase.execSQL("DELETE FROM " + TABLE_NAME + " WHERE id = " + ID);
            return true;
        }catch (Exception e){
            Log.d(TAG, "deleteData: " + e);
            return false;
        }
    }

    public boolean deleteAllData(String TABLE_NAME){
        try {
            myDatabase.execSQL("DELETE FROM " + TABLE_NAME);
            return true;
        }catch (Exception e){
            Log.d(TAG, "deleteData: " + e);
            return false;
        }
    }

    public void closeDatabase(){
        myDatabase.close();
    }

    public int numberOfRows(String TABLE_NAME){
        Cursor c = myDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        int count = c.getCount();
        c.close();
        return count;
    }

}
