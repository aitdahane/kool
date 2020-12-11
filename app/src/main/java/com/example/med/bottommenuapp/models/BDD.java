package com.example.med.bottommenuapp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Med on 6/11/2017.
 */

public class BDD extends SQLiteOpenHelper {

    public static final String Database_name="users.db";
    public static final String Table_name="Users_Table";
    public static final String Col_1="ID";
    public static final String Col_2="NAME";
    public static final String Col_3="EMAIL";
    public static final String Col_4="MOBILE";
    public static final String Col_5="PASSWORD";

    public BDD(Context context){
        super(context,Database_name,null,1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ Table_name+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,MOBILE TEXT,PASSWORD TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(db);
    }

    public boolean insertData(User user){
        SQLiteDatabase bdd= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(Col_2,user.getName());
        contentValues.put(Col_3,user.getEmail());
        contentValues.put(Col_4,user.getMobile());
        contentValues.put(Col_5,user.getPassword());
        long res=bdd.insert(Table_name,null,contentValues);
        if(res==-1)
            return false;
        else
            return true;
    }
    public Cursor getData(){

        SQLiteDatabase bdd=this.getWritableDatabase();
        Cursor cursor=bdd.rawQuery("select * from "+ Table_name,null);
        return cursor;
    }
}
