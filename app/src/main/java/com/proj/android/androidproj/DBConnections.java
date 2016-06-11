package com.proj.android.androidproj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by ronen_000 on 11/06/2016.
 */
public class DBConnections extends SQLiteOpenHelper  {

    public DBConnections(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS myReports");
        String query ="CREATE TABLE myReports (id INTEGER,name TEXT, phone TEXT,area TEXT,img_src TEXT,img_name,desc TEXT,PRIMARY KEY(id));";
        db.execSQL(query);

    }
    public void insertRowReports(String name,String phone,String img_src){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //contentValues.put("id",id);
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        contentValues.put("img_src",img_src);

        db.insert("myReports", null, contentValues);
    }

    public ArrayList getAllData()
    {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM myReports", null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex("id")));
            arrayList.add(res.getString(res.getColumnIndex("name")));
            arrayList.add(res.getString(res.getColumnIndex("phone")));
            arrayList.add(res.getString(res.getColumnIndex("img_src")));
            res.moveToNext();
        }
        return arrayList;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS myReports");
    }
}
