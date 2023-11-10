package com.example.sunrisehostelms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sunrisehostelms.Core.DBHelper;
import com.example.sunrisehostelms.Core.PersonalDetailsContract;

public class UserLoginActivity extends AppCompatActivity {
    TextView backHome;
    EditText Editemail, EditregNo;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login2);
        backHome = findViewById(R.id.backHome);
        Editemail = findViewById(R.id.emailEditText);
        EditregNo = findViewById(R.id.RegNo);
        submit = findViewById(R.id.login);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backToHome);
            }

        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Editemail.getText().toString().equals("") ||
                        EditregNo.getText().toString().equals("")
                ) {
                    Toast.makeText(UserLoginActivity.this, "fill All the fields ", Toast.LENGTH_SHORT).show();
                } else {

                    loginUser();

                }

            }
        });

    }

    private void loginUser() {
        String email = Editemail.getText().toString();
        String regno = EditregNo.getText().toString();
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + PersonalDetailsContract.PersonalDetailsEntry.TABLE_NAME +
                " WHERE " + PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_REG_NO + " = ?" +
                " AND " + PersonalDetailsContract.PersonalDetailsEntry.COLUMN_NAME_EMAIL + "=?", new String[]{regno, email});

        if(!cursor.moveToFirst()){
            Toast.makeText(this, "User  does not Exists", Toast.LENGTH_SHORT).show();
            cursor.close();
        }
        else{
            Intent intent=new Intent(getApplicationContext(),UserProfile.class);
            intent.putExtra("regno",regno);
            startActivity(intent);


        }

    }

}