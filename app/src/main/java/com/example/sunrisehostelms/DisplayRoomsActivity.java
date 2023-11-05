package com.example.sunrisehostelms;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sunrisehostelms.Core.DBHelper;
import com.example.sunrisehostelms.Core.RoomContract;

public class DisplayRoomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_rooms);
        TextView  backHome=findViewById(R.id.backHome);
        LinearLayout LLdisplayItems = findViewById(R.id.LLdisplayRoms);


        //navigate back to home page
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToHmePage=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(backToHmePage);
            }
        });


        //display rooms
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                RoomContract.RoomEntry.COLUMN_NAME_PRICE,
                RoomContract.RoomEntry.COLUMN_NAME_CATEGORY,
                RoomContract.RoomEntry.COLUMN_NAME_FLOOR,
                RoomContract.RoomEntry.COLUMN_NAME_ROOM_NO
        };

        Cursor cursor = db.query(
                RoomContract.RoomEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String price = cursor.getString(cursor.getColumnIndex(RoomContract.RoomEntry.COLUMN_NAME_PRICE));
            String category = cursor.getString(cursor.getColumnIndex(RoomContract.RoomEntry.COLUMN_NAME_CATEGORY));
            String floor = cursor.getString(cursor.getColumnIndex(RoomContract.RoomEntry.COLUMN_NAME_FLOOR));
            String roomNo = cursor.getString(cursor.getColumnIndex(RoomContract.RoomEntry.COLUMN_NAME_ROOM_NO));

            // Create a new LinearLayout for each room
            LinearLayout roomLayout = new LinearLayout(this);
            roomLayout.setOrientation(LinearLayout.VERTICAL);

            // Create TextViews for room details
            TextView floorTextView = new TextView(this);
            floorTextView.setText("Floor: " + floor);

            TextView categoryTextView = new TextView(this);
            categoryTextView.setText("Category: " + category);

            TextView priceTextView = new TextView(this);
            priceTextView.setText("Price: " + price);

            TextView roomNoTextView = new TextView(this);
            roomNoTextView.setText("Room No: " + roomNo);

            // Create a Button for booking
            Button bookButton = new Button(this);
            bookButton.setText("Book");

            // Add TextViews and Button to the LinearLayout
            roomLayout.addView(floorTextView);
            roomLayout.addView(categoryTextView);
            roomLayout.addView(priceTextView);
            roomLayout.addView(roomNoTextView);
            roomLayout.addView(bookButton);

            // Add the LinearLayout to the main LinearLayout
            LLdisplayItems.addView(roomLayout);


            bookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     Intent userRegister=new Intent(getApplicationContext(),RegisterNewUser.class);
                    // Pass room details as extras
                    userRegister.putExtra("roomPrice", price);
                    userRegister.putExtra("roomNo", roomNo);

                    startActivity(userRegister);
                }
            });
        }

        cursor.close();
        db.close();





    }
}
