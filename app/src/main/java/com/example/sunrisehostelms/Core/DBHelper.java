package com.example.sunrisehostelms.Core;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "RoomsDB";
    private static final int DATABASE_VERSION = 1;

    // Define the table and column names in the contract class

    // Constructor
    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your table(s) here
        db.execSQL(RoomContract.RoomEntry.CREATE_TABLE);
        db.execSQL(PersonalDetailsContract.PersonalDetailsEntry.CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade if needed
    }

    public long insertData(String tableName, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            return db.insert(tableName, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("DBHelper", "Error inserting data: " + e.getMessage()); // Log the error message
            return -1;
        } finally {
            db.close();
        }
    }

}
