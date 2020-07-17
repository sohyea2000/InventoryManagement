package com.example.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    private Toolbar mToolbar;
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Button addEmployee = findViewById(R.id.add_employee);
        Button addProduct = findViewById(R.id.add_product);
        Button update = findViewById(R.id.updateButton);
        Button viewButton = findViewById(R.id.viewBtn);
        mToolbar = findViewById(R.id.mainAppBar);
        setSupportActionBar(mToolbar);
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
                Intent intent = new Intent(AdminActivity.this,UserProductActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_logout_button:
                Intent in = new Intent(AdminActivity.this,MainActivity.class);
                startActivity(in);

                return true;
            case R.id.main_request_button:
                Intent intent = new Intent(AdminActivity.this,RequestActivity.class);
                startActivity(intent);
                return true;
            case R.id.get_button:
                Intent intent1 = new Intent(AdminActivity.this,WelcomeActivty.class);
                startActivity(intent1);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
