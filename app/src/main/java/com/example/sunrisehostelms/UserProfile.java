package com.example.sunrisehostelms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunrisehostelms.Core.DBHelper;
import com.example.sunrisehostelms.Core.PersonalDetailsContract;


public class UserProfile extends AppCompatActivity {
TextView txtName,txtEmail,txtRegNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Intent intent = getIntent();
       String regNo = intent.getStringExtra("regNo");
        txtName = findViewById(R.id.name);
        txtEmail = findViewById(R.id.email);
        txtRegNo = findViewById(R.id.regno);


        DBHelper  dbHelper= new DBHelper(this);


        // Get the user details from the database
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + PersonalDetailsContract.PersonalDetailsEntry.TABLE_NAME +
                " WHERE " + PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_REG_NO + " = ?", new String[]{getIntent().getStringExtra("regNo")});

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_FULL_NAME));
            String email = cursor.getString(cursor.getColumnIndex(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_EMAIL));
            String regno = cursor.getString(cursor.getColumnIndex(PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_REG_NO));

            txtName.setText(name);
            txtEmail.setText(email);
            txtRegNo.setText(regno);
        } else {
            Toast.makeText(this, "hhhshjh", Toast.LENGTH_SHORT).show();
        }


    }

}
