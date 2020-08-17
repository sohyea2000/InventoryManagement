package com.example.inventorymanagement;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView adminButton = findViewById(R.id.adminButton);
        TextView workButton = findViewById(R.id.workButton);


        //Gets executed when admin uses the app
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AdminLogin.class);
                startActivity(intent);
                finish();
            }
        });

        //Gets execute when worker uses the app
        workButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,WorkerLogin.class);
                startActivity(in);
                finish();
            }
        });


    }

}
