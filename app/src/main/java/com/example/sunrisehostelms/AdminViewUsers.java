package com.example.sunrisehostelms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunrisehostelms.Core.DBHelper;
import com.example.sunrisehostelms.Core.RoomContract;
import com.example.sunrisehostelms.Core.DBHelper;
import com.example.sunrisehostelms.Core.PersonalDetailsContract;


public class AdminViewUsers extends AppCompatActivity {
    TextView backHome, addNewRoom,navUpdateUsers, navDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_users);

        navUpdateUsers= findViewById(R.id.navUpdateUsers);
        backHome = findViewById(R.id.backHome);
        LinearLayout LLdisplayItems = findViewById(R.id.LLdisplayRoms);
        addNewRoom=findViewById(R.id.navAddNewRooms);
        navDashboard=findViewById(R.id.navDashboard);
        // navigate the navbar//
        navDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToDashboard = new Intent(getApplicationContext(), AdminDashboard.class);
                startActivity(backToDashboard);
            }
        });
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
        navUpdateUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ViewUsers = new Intent(getApplicationContext(), AdminViewUsers.class);
                startActivity(ViewUsers);
            }
        });




        // dispalay  the users//
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_ROOM_NO,
                PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_EMAIL,
                PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_FULL_NAME,
                PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_PHONE_NO,
                PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_ID_NO,
                PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_REG_NO,
        };

        Cursor cursor = db.query(
                PersonalDetailsContract.PersonalDetailsEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String roomNo = cursor.getString(cursor.getColumnIndex(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_ROOM_NO));
            String userName = cursor.getString(cursor.getColumnIndex(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_FULL_NAME));
            String Email = cursor.getString(cursor.getColumnIndex(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_EMAIL));
            String regNo = cursor.getString(cursor.getColumnIndex(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_REG_NO));
            String idNo = cursor.getString(cursor.getColumnIndex(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_ID_NO));
            String  Phone = cursor.getString(cursor.getColumnIndex(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_PHONE_NO));

            // Create a new LinearLayout for each room
            LinearLayout roomLayout = new LinearLayout(this);
            roomLayout.setOrientation(LinearLayout.VERTICAL);

            // Create TextViews for room details
            TextView userNameTextView = new TextView(this);
            userNameTextView.setText("Name : " + userName);

            TextView EmailTextView = new TextView(this);
            EmailTextView.setText("Student Email : " + Email);

            TextView regNoTextView = new TextView(this);
            regNoTextView.setText("Student regNO : " + regNo);

            TextView idNoTextView = new TextView(this);
            idNoTextView.setText("Student idNo: " + idNo);

            TextView PhoneTextView = new TextView(this);
            PhoneTextView.setText("Room No: " + Phone);

            TextView roomNoTextView = new TextView(this);
            roomNoTextView.setText("Room No: " + roomNo);



            // Create a Button for booking
            Button bookButton = new Button(this);
            bookButton.setText("Book");

            // Add TextViews and Button to the LinearLayout
            roomLayout.addView(userNameTextView);
            roomLayout.addView(EmailTextView);
            roomLayout.addView( regNoTextView);
            roomLayout.addView(idNoTextView );
            roomLayout.addView( PhoneTextView);
            roomLayout.addView(roomNoTextView);


            roomLayout.addView(bookButton);

            // Add the LinearLayout to the main LinearLayout
            LLdisplayItems.addView(roomLayout);


            bookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent userRegister=new Intent(getApplicationContext(),RegisterNewUser.class);
                    // Pass room details as extras
                    Toast.makeText(AdminViewUsers.this, "Deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }

        cursor.close();
        db.close();


    }
}