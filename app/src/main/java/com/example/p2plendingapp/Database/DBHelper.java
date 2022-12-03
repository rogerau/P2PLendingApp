package com.example.p2plendingapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.p2plendingapp.Model.User;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    //user
    public static final String TABLE_USERS = "users";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + USERNAME + " TEXT PRIMARY KEY," + EMAIL + " TEXT," + PASSWORD + " TEXT," + FIRST_NAME + " TEXT," + LAST_NAME + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists " + TABLE_USERS);
        onCreate(db);
    }

    public Boolean InsertData(String username, String email, String password, String firstname, String lastname) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USERNAME, username);
        contentValues.put(EMAIL, email);
        contentValues.put(PASSWORD, password);
        contentValues.put(FIRST_NAME, firstname);
        contentValues.put(LAST_NAME, lastname);

        long result = MyDB.insert(TABLE_USERS, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean CheckUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) { //if user exists
            return true;
        } else {
            return false;
        }
    }

    public Boolean CheckUsernamePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) { //if user exists
            return true;
        } else {
            return false;
        }
    }

    public void addUser(User user) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(EMAIL, user.getEmail());
        values.put(FIRST_NAME, user.getFirstname());
        values.put(LAST_NAME, user.getLastname());
        values.put(PASSWORD, user.getPassword());
    }

    public Boolean UpdatePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password",password);
        long result = MyDB.update("users",values, "username = ?",new String[]{username});
        if (result == -1) {
            return false;
        }
        else{
            return true;
        }

    }


}
