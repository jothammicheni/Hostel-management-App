package com.example.sunrisehostelms;
import com.example.sunrisehostelms.Core.DBHelper;
import com.example.sunrisehostelms.Core.RoomContract;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdminDashboard extends AppCompatActivity {
   TextView  backHome, addNewRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        backHome = findViewById(R.id.backHome);
        LinearLayout LLdisplayItems = findViewById(R.id.LLdisplayRoms);
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        backHome=findViewById(R.id.backHome);
        addNewRoom=findViewById(R.id.navAddNewRooms);
        addNewRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Addnewrooms=new Intent(getApplicationContext(),AddNewRoom.class);
                startActivity(Addnewrooms);
            }
        });
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToHome=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(backToHome);
            }
        });


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
            Button deleteButton = new Button(this);
            deleteButton .setText("DELETE");
            Button bookButton = new Button(this);
            deleteButton.setBackgroundColor(Color.RED);


            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper dbHelper = new DBHelper(AdminDashboard.this);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    String roomNoToDelete = roomNo;

                    String selection = RoomContract.RoomEntry.COLUMN_NAME_ROOM_NO + " = ?";
                    String[] selectionArgs = {roomNoToDelete};

                    int deletedRows = db.delete(
                            RoomContract.RoomEntry.TABLE_NAME,
                            selection,
                            selectionArgs
                    );

                    if (deletedRows > 0) {
                        // Deletion was successful
                        Toast.makeText(AdminDashboard.this, "Room Deleted Successfully", Toast.LENGTH_SHORT).show();
                        // You may want to update the UI or show a message here
                    } else {
                        Toast.makeText(AdminDashboard.this, "Deletion failed", Toast.LENGTH_SHORT).show();
                        // Deletion failed or no rows were affected
                        // You may want to handle this case as needed
                    }

                    db.close();
                }
            });

            // Add TextViews and Button to the LinearLayout
            roomLayout.addView(floorTextView);
            roomLayout.addView(categoryTextView);
            roomLayout.addView(priceTextView);
            roomLayout.addView(roomNoTextView);
            roomLayout.addView(deleteButton );

            // Add the LinearLayout to the main LinearLayout
            LLdisplayItems.addView(roomLayout);
        }

        cursor.close();
        db.close();

    }
}