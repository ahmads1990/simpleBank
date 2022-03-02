package com.example.simplebank.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.simplebank.User;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class databaseHelper extends SQLiteOpenHelper {

    //Database info
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "simpleBankDatabase";

    //Database tables
    //USERS  table
    private static final String TABLE_USERS = "users";
    private static final String KEY_USERS_ID = "id";
    private static final String KEY_USERS_NAME = "name";
    private static final String KEY_USERS_EMAIL = "email";
    private static final String KEY_USERS_BALANCE = "balance";

    //TRANSFERS table
    private static final String TABLE_TRANSFERS = "transfers";
    private static final String KEY_TRANSFERS_ID = "id";
    private static final String KEY_TRANSFERS_DATE = "date";
    private static final String KEY_TRANSFERS_AMOUNT = "amount";
    private static final String KEY_TRANSFERS_SENDER_ID = "sender";
    private static final String KEY_TRANSFERS_RECEIVER_ID = "receiver";


    //Constructor
    public databaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creates tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create user table
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_USERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USERS_NAME + " TEXT,"
                + KEY_USERS_EMAIL + " TEXT,"
                + KEY_USERS_BALANCE + "INTEGER NOT NULL" + ")";
        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);

        //create transfer table
        String CREATE_TRANSFERS_TABLE = "CREATE TABLE " + TABLE_TRANSFERS + "("
                + KEY_TRANSFERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TRANSFERS_DATE + " Date,"
                + KEY_TRANSFERS_AMOUNT + " INTEGER NOT NULL,"
                + KEY_TRANSFERS_SENDER_ID + " INTEGER,"
                + KEY_TRANSFERS_RECEIVER_ID + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_TRANSFERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSFERS);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    //Methods for database
    //add new user
    public void addNewUser(User newUser) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //put user data into content value to prepare for database insertion
        values.put(KEY_USERS_NAME, newUser.getUsername());
        values.put(KEY_USERS_EMAIL, newUser.getEmail());
        values.put(KEY_USERS_BALANCE, newUser.getCurrentBalance());

        try {
            //inserting row
            db.insert(TABLE_USERS, null, values);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("mytag", "user insert failed");
        }
        db.close();
    }

    //get all users info
    public ArrayList<User> getAllUserInfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<User> userArrayList;

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(selectQuery, null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("mytag", "user get all info failed");
        }
        if (cursor == null) return null;

        //create array list size same as cursor data size
        userArrayList = new ArrayList<User>(cursor.getCount());
        Log.d("mytag", "created user list size " + cursor.getCount());

        // looping through all rows and adding to list
        int userIndex = 0;
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setCurrentBalance(cursor.getFloat(4));
                // Adding user to list
                userArrayList.add(userIndex, user);
                userIndex++;
            } while (cursor.moveToNext());
        }
        db.close();
        // return contact
        return userArrayList;
    }

    //get user current balance
    public int getUserBalance(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            //query select balance with id
            cursor = db.query(TABLE_USERS, new String[]{KEY_USERS_ID,
                            KEY_USERS_BALANCE}, KEY_USERS_ID + "=?",
                    new String[]{String.valueOf(userId)}, null, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("mytag", "user balance check exception");
        }

        //if cursor isn't empty
        if (cursor != null) {
            //return user balance
            cursor.moveToFirst();
            Log.d("mytag", "user balance " + cursor.getInt(1));
            db.close();
            return cursor.getInt(1);
        } else {
            //else return -1 for error
            db.close();
            return -1;
        }
    }

    //add new transfer
    public void addNewTransfer(int senderId, int receiverId, float amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Date date = new Date();

        //add data to content values to prepare for inserting
        values.put(KEY_TRANSFERS_SENDER_ID, senderId);
        values.put(KEY_TRANSFERS_RECEIVER_ID, receiverId);
        values.put(KEY_TRANSFERS_AMOUNT, amount);
        values.put(KEY_TRANSFERS_DATE, DateFormat.getDateInstance().format(date));

        try {
            //inserting row
            db.insert(TABLE_TRANSFERS, null, values);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("mytag", "transfer insert failed");
        }
        db.close();
    }
}
