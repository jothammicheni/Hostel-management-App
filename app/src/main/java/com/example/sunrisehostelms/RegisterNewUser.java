package com.example.sunrisehostelms;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.sunrisehostelms.Core.RoomContract;
import com.example.sunrisehostelms.Core.DBHelper;
import com.example.sunrisehostelms.Core.PersonalDetailsContract;

public class RegisterNewUser extends AppCompatActivity {


    private String roomPrice;
    private String roomNo;
    EditText fullNameEditText, emailAdressEditText, regNoEditText, idNoEditText, phoneNoEditText;
    Button userRegisterBtn;
    TextView backToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);

        // Retrieve room details from intent
        Intent intent = getIntent();
        roomPrice = intent.getStringExtra("price");
        roomNo = intent.getStringExtra("roomNo");
        // Initialize components
        fullNameEditText = findViewById(R.id.userNameEditText);
        emailAdressEditText = findViewById(R.id.emailAdressEditText);
        regNoEditText = findViewById(R.id.regNoEditText);
        idNoEditText = findViewById(R.id.idnoEditText);
        phoneNoEditText = findViewById(R.id.phoneNoEditText);
        userRegisterBtn = findViewById(R.id.userRegisterBtn);
        backToHome = findViewById(R.id.TVbackToHome);

        // Set up a click listener for the registration button
        userRegisterBtn.setOnClickListener(view -> registerUser());

        // Set up a click listener to navigate back to home
        backToHome.setOnClickListener(view -> {
            Intent backToHome = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(backToHome);
        });
    }

    private void registerUser() {
        if (fullNameEditText.getText().toString().equals("") ||
                emailAdressEditText.getText().toString().equals("") ||
                regNoEditText.getText().toString().equals("") ||
                idNoEditText.getText().toString().equals("") ||
                phoneNoEditText.getText().toString().equals("")
        ) {
            Toast.makeText(this, "Fill a the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String regNo = regNoEditText.getText().toString();


        // Check if the room already exists
        Cursor cursor = db.rawQuery("SELECT * FROM " + PersonalDetailsContract.PersonalDetailsEntry.TABLE_NAME +
                " WHERE " + PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_ROOM_NO+ "=?", new String[]{roomNo});

        if (cursor.moveToFirst()) {
            // Room already exists in the database
            Toast.makeText(RegisterNewUser.this, "User exists ", Toast.LENGTH_SHORT).show();
            cursor.close();
        }
        else {
            String fullName = fullNameEditText.getText().toString();
            String email = emailAdressEditText.getText().toString();
            String idNo = idNoEditText.getText().toString();
            String phoneNo = phoneNoEditText.getText().toString();

            // Insert data into personal details table

            ContentValues values = new ContentValues();
            values.put(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_FULL_NAME, fullName);
            values.put(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_EMAIL, email);
            values.put(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_REG_NO, regNo);
            values.put(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_ID_NO, idNo);
            values.put(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_PHONE_NO, phoneNo);
            // Assuming you have a method to get room price and room number
            //values.put(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_ROOM_PRICE, roomPrice);
            // values.put(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_ROOM_NO, roomNo);

            long newRowId = dbHelper.insertData(PersonalDetailsContract.PersonalDetailsEntry.TABLE_NAME, values);

            if (newRowId != 0) {
                deleteRoomFromDatabase(roomNo);
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed To insert the data", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }

    }


        private void deleteRoomFromDatabase (String roomNo){
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Define the WHERE clause to identify the room to delete
            String selection = RoomContract.RoomEntry.COLUMN_NAME_ROOM_NO + " = ?";
            String[] selectionArgs = {roomNo};

            // Perform the delete operation
            int deletedRows = db.delete(RoomContract.RoomEntry.TABLE_NAME, selection, selectionArgs);

            if (deletedRows > 0) {
                Toast.makeText(this, "Room deleted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to delete room", Toast.LENGTH_SHORT).show();
            }


        }


}
