package com.example.sunrisehostelms.Core;

import android.provider.BaseColumns;

public class PersonalDetailsContract {

    private PersonalDetailsContract() {}

    public static class PersonalDetailsEntry implements BaseColumns {
        public static final String TABLE_NAME = "personal_details";
        public static final String COLUMN_NAME_FULL_NAME = "full_name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_REG_NO = "reg_no";
        public static final String COLUMN_NAME_ID_NO = "id_no";
        public static final String COLUMN_NAME_PHONE_NO = "phone_no";
        //public static final String COLUMN_NAME_ROOM_PRICE = "room_price";
       // public static final String COLUMN_NAME_ROOM_NO = "room_no";

        // Define the SQL command to create the table
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_FULL_NAME + " TEXT," +
                        COLUMN_NAME_EMAIL + " TEXT," +
                        COLUMN_NAME_REG_NO + " TEXT," +
                        COLUMN_NAME_ID_NO + " TEXT," +
                        COLUMN_NAME_PHONE_NO + " TEXT,)" ;
                        //+
                        //COLUMN_NAME_ROOM_PRICE + " INTEGER," +
                       // COLUMN_NAME_ROOM_NO + " TEXT)";
    }
}

