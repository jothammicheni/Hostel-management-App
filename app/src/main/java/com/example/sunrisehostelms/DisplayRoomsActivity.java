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
        TextView UserProfile=findViewById(R.id.profile);

        //navigate back to home page
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToHmePage=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(backToHmePage);
            }
        });

        //navigate to userProfile

        UserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserLoginActivity.class);
                startActivity(intent);
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


            //create all contents holder

            LinearLayout contentLayout=new LinearLayout(this);
            // Set bottom margin in pixels
            int marginBottomInPixels = 9; // Adjust the value as needed
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(10, 40, 10, marginBottomInPixels);

            contentLayout.setLayoutParams(layoutParams);


            // Create a new LinearLayout for each room
            LinearLayout roomLayout = new LinearLayout(this);
            roomLayout.setOrientation(LinearLayout.VERTICAL);


            //create  button layout
            LinearLayout buttonLayout=new LinearLayout(this);
            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            buttonLayoutParams.setMargins(200, 40, 10, marginBottomInPixels);
           buttonLayout.setLayoutParams(buttonLayoutParams);

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

            buttonLayout.addView(bookButton);

            // Add the LinearLayout to the main LinearLayout
            contentLayout.addView(roomLayout);
            contentLayout.addView(buttonLayout);

            LLdisplayItems.addView(contentLayout);


            bookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     Intent userRegister=new Intent(getApplicationContext(),RegisterNewUser.class);
                    // Pass room details as extras
                    userRegister.putExtra("price", price);
                    userRegister.putExtra("roomNo", roomNo);

                    startActivity(userRegister);
                }
            });
        }

        cursor.close();
        db.close();





    }
}
