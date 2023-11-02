package com.example.sunrisehostelms.Core;

import android.provider.BaseColumns;

public class RoomContract {

    private RoomContract() {}

    public static class RoomEntry implements BaseColumns {
        public static final String TABLE_NAME = "rooms";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_FLOOR = "floor";
        public static final String COLUMN_NAME_ROOM_NO = "room_no";

        // Define the SQL command to create the table
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_PRICE + " INTEGER," +
                        COLUMN_NAME_CATEGORY + " TEXT," +
                        COLUMN_NAME_FLOOR + " TEXT," +
                        COLUMN_NAME_ROOM_NO + " TEXT)";
    }
}
