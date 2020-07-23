package com.example.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserActivity extends AppCompatActivity {
    private DatabaseReference userDatabase;
    private EditText empname;
    private EditText empgender;
    public EditText empId;
    public static String name;
    public static String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
         empgender = findViewById(R.id.empGender);
         empId = findViewById(R.id.empId);
         empname = findViewById(R.id.employeeName);
         Button addButton = findViewById(R.id.addEmp);
         addButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 EmployeeId.EmployeeId = empId.getText().toString();
                  id = empId.getText().toString();
                  EmployeeId.name = empname.getText().toString();
                  name = empname.getText().toString();
                 String gender = empgender.getText().toString();
                 userDatabase = FirebaseDatabase.getInstance().getReference().child(id);
                 HashMap<String,String> userMap = new HashMap<>();
                 userMap.put("NAME",name);
                 userMap.put("GENDER",gender);
                 userMap.put("EMPLOYEEID",id);
                 userDatabase.setValue(userMap);
                 Intent intent = new Intent(UserActivity.this,AdminActivity.class);
                 startActivity(intent);
                 finish();
             }
         });
    }
}
