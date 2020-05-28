package com.example.textspeech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="users.db";
    public UserDatabase(@NonNull Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user ( id integer primary key , name text , email text unique, password text )");
    }

    public boolean insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",user.getName());
        contentValues.put("email",user.getEmail());
        contentValues.put("password",user.getPassword());
        db.insert("user",null,contentValues);
        return true;

    }
    public Cursor readUser(User user){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.query("user",new String[]{"email,password"},"password = ?",new String[]{user.getPassword()},null, null,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
