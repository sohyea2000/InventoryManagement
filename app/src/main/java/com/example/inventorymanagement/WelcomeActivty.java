package com.example.inventorymanagement;


import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;

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

    public void viewProduct(View view){
        Intent intent2 = new Intent(WelcomeActivty.this,ViewProductActivity.class);
        startActivity(intent2);

    }

    public void requestPro(View view)
    {
        Intent intent11 = new Intent(WelcomeActivty.this,MakeRequestActivity.class);
        startActivity(intent11);
    }
}
