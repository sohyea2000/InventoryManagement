package com.example.inventorymanagement;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;





public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button adminButton = findViewById(R.id.adminButton);
        Button workButton = findViewById(R.id.workButton);
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AdminLogin.class);
                startActivity(intent);
                finish();
            }
        });
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
