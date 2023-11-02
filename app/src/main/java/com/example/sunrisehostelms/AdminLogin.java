package com.example.sunrisehostelms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
  TextView  backHome;
  EditText  email,password;
  Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
     backHome=findViewById(R.id.backHome)   ;
     email=findViewById(R.id.emailEditText)   ;
     password=findViewById(R.id.password)   ;
     submit=findViewById(R.id.login);

     submit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
              setAdminLogin();
         }
     });

     backHome.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
              Intent backToHome=new Intent(getApplicationContext(),MainActivity.class);
              startActivity(backToHome);
         }
     });



    }
    public void setAdminLogin(){
        if(email.getText().toString().equals("")&& password.getText().toString().equals("")){
            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
            Intent  goToDashboard=new Intent(getApplicationContext(),AdminDashboard.class);
            startActivity(goToDashboard);
        }
        else {
            Toast.makeText(this, "Wrong details", Toast.LENGTH_SHORT).show();
        }
    }

}