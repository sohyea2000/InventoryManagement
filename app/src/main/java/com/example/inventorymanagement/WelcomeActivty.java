package com.example.inventorymanagement;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;



public class WelcomeActivty extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_activty);
 }
     //if employee wants to scan a product
    public void scanStart(View view)
    {
        Intent in = new Intent(WelcomeActivty.this,DisplayActivity.class);
        startActivity(in);

    }

// when worker wants to logout
    public void logoutEmp(View view)
    {
        Intent mainIntent = new Intent(WelcomeActivty.this,MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
