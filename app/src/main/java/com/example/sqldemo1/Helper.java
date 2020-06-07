package com.example.sqldemo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {
    public static final String mydb = "mydb";

    public Helper(@Nullable Context context) {
        super(context, mydb, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE PRODUCT(id INTEGER PRIMARY KEY ,NAME TEXT,DESCRIPTION TEXT,PRICE REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String name, String description, String price) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("DESCRIPTION", description);
        contentValues.put("PRICE", price);
        long result = sqLiteDatabase.insert("PRODUCT", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM PRODUCT", null);
        return res;
    }

    public boolean updatedata(String name, String description, String price) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("NAME", name);
        contentValues.put("DESCRIPTION", description);
        contentValues.put("PRICE", price);
        sqLiteDatabase.update("PRODUCT",contentValues,"NAME=?",new String[]{name});
        return true;
    }
    public Integer deletedata(String name)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       return sqLiteDatabase.delete("PRODUCT","NAME=?",new String[]{name});


    }

}
