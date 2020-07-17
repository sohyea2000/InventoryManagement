package com.example.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Button addEmployee = findViewById(R.id.add_employee);
        Button addProduct = findViewById(R.id.add_product);
        Button update = findViewById(R.id.updateButton);
        Button viewButton = findViewById(R.id.viewBtn);
        
         //Gets executed when admin wants to add new employee
        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AdminActivity.this,UserActivity.class);
                startActivity(in);
            }
        });
        
         //Gets executed when admin wants to add new product
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this,ProductActivity.class);
                startActivity(intent);
            }
        });
        
         //Gets executed when admin wants to change the details of the product
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AdminActivity.this,UpdateActivity.class);
                startActivity(in);

            }
        });
        
         //Gets executed when admin wants to logout
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
