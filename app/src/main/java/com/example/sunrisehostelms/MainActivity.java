package com.example.sunrisehostelms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView admin,UserProfile;
Button displayRoomsBtn, ViewRoomsPhotosBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewRoomsPhotosBtn=findViewById(R.id.ViewRoomsPhotosBtn);
        displayRoomsBtn = findViewById(R.id.displayRoomsBtn);
        admin=findViewById(R.id.navAdmin);
        UserProfile=findViewById(R.id.profile);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAdmin=new Intent(getApplicationContext(),AdminLogin.class);
                startActivity(goToAdmin);
            }
        });
        // Add this inside the onCreate method of MainActivity


        displayRoomsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayRoomsActivity.class);
                startActivity(intent);
            }
        });


        ViewRoomsPhotosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayRoomPhotos.class);
                startActivity(intent);
            }
        });

    }
}