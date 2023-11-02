// addnewroom class

package com.example.sunrisehostelms;

import androidx.appcompat.app.AppCompatActivity;
import com.example.sunrisehostelms.Core.DBHelper;
import com.example.sunrisehostelms.Core.RoomContract;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class AddNewRoom extends AppCompatActivity {
 TextView backToAdminDashboard;
 Button addnewRoomBtn;
 EditText  priceEditText,roomCategoryNoEditText,floorNoEditText, roomNoEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_room);

        //declare components//
        addnewRoomBtn=findViewById(R.id.addRoomBtn);
       priceEditText=findViewById(R.id.priceEditText);
        roomCategoryNoEditText=findViewById(R.id.roomCategoryNoEditText);
        floorNoEditText=findViewById(R.id.floorNoEditText);
        roomNoEditText=findViewById(R.id.roomNoEditText);
        backToAdminDashboard=findViewById(R.id.TVbackToAdminHome);
        backToAdminDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToAdminDashboard = new Intent(getApplicationContext(), AdminDashboard.class);
                startActivity(backToAdminDashboard);
            }
            });

        addnewRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!priceEditText.getText().toString().equals("") &&
                        !roomCategoryNoEditText.getText().toString().equals("") &&
                        !floorNoEditText.getText().toString().equals("") &&
                        !roomNoEditText.getText().toString().equals("")) {

                    DBHelper dbHelper = new DBHelper(AddNewRoom.this);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    String roomNo = roomNoEditText.getText().toString();

                    // Check if the room already exists
                    Cursor cursor = db.rawQuery("SELECT * FROM " + RoomContract.RoomEntry.TABLE_NAME +
                            " WHERE " + RoomContract.RoomEntry.COLUMN_NAME_ROOM_NO + "=?", new String[]{roomNo});

                    if (cursor.moveToFirst()) {
                        // Room already exists in the database
                        Toast.makeText(AddNewRoom.this, "Room already exists in the database", Toast.LENGTH_SHORT).show();
                    } else {
                        // Get data from EditText fields
                        String price = priceEditText.getText().toString();
                        String category = roomCategoryNoEditText.getText().toString();
                        String floor = floorNoEditText.getText().toString();

                        ContentValues values = new ContentValues();
                        values.put(RoomContract.RoomEntry.COLUMN_NAME_PRICE, price);
                        values.put(RoomContract.RoomEntry.COLUMN_NAME_CATEGORY, category);
                        values.put(RoomContract.RoomEntry.COLUMN_NAME_FLOOR, floor);
                        values.put(RoomContract.RoomEntry.COLUMN_NAME_ROOM_NO, roomNo);

                        long newRowId = db.insert(RoomContract.RoomEntry.TABLE_NAME, null, values);

                        if (newRowId != 0) {
                            Toast.makeText(AddNewRoom.this, "Data inserted Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddNewRoom.this, "Error inserting data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    cursor.close();
                    db.close();
                } else {
                    Toast.makeText(AddNewRoom.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}