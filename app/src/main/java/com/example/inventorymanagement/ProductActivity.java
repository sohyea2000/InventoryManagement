package com.example.inventorymanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;

public class ProductActivity extends AppCompatActivity {
    private TextView code;
    private DatabaseReference mDatabase;
    private DatabaseReference databaseReference;
    private EditText name;
    private EditText description;
    private EditText date;
    private EditText time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        code = findViewById(R.id.code);
        name = findViewById(R.id.productName);
        description = findViewById(R.id.productDescription);
        date = findViewById(R.id.current_date);
        time = findViewById(R.id.current_time);
    }
    //Scans the QR Code
    public void scanCode(View view)
    {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult!=null){
            if(intentResult.getContents()== null)
            {
                code.setText("CANCELLED");
            }
            else{
                code.setText(intentResult.getContents());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //Admin adds a product to the company database
    public void addData(View view)
    {
        String str = code.getText().toString();
        String product_name = name.getText().toString();
        String product_description = description.getText().toString();
        String current_date = date.getText().toString();
        String current_time = time.getText().toString();

        //adding a child to firebase database

        mDatabase = FirebaseDatabase.getInstance().getReference().child(str);
        HashMap<String,String> userMap = new HashMap<>();

        userMap.put("PRODUCT NAME",product_name);
        userMap.put("PRODUCT DESCRIPTION",product_description);
        userMap.put("DATE OF ISSUE",current_date);
        userMap.put("DATE OF SUBMISSION",current_time);
        mDatabase.setValue(userMap);
        Intent in = new Intent(ProductActivity.this,AdminActivity.class);
        startActivity(in);

       databaseReference = FirebaseDatabase.getInstance().getReference().child("PRODUCTS");
        databaseReference.push().setValue(product_name + " : " + product_description);
        Toast.makeText(this, "PRODUCT IS ADDED", Toast.LENGTH_SHORT).show();
    }
}
