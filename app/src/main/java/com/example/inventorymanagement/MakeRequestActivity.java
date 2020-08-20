package com.example.inventorymanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;



public class MakeRequestActivity extends AppCompatActivity {
    private TextView employee_id;
    private EditText employee_name;
    private EditText product_name;

    private DatabaseReference databaseReference;
    private String i = "a";
    String empId;
    String procode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_request);

        employee_name = findViewById(R.id.empName);
        product_name = findViewById(R.id.proName);
        employee_id = findViewById(R.id.myId);
        Button requestBtn = findViewById(R.id.make_request_btn);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = employee_name.getText().toString();
                String proname = product_name.getText().toString();



                if(!TextUtils.isEmpty(name)||!TextUtils.isEmpty(proname)){
                  databaseReference = FirebaseDatabase.getInstance().getReference().child("Requests");
                   databaseReference.push().setValue(proname+ " : "+name);

                    Toast.makeText(MakeRequestActivity.this, "YOUR REQUEST HAS BEEN SENT", Toast.LENGTH_SHORT).show();
                     i= i+1;
                }
                else {
                    Toast.makeText(MakeRequestActivity.this, "CHECK YOUR FORM", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void scanId(View view)
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
                employee_id.setText("CANCELLED");
            }
            else{
                employee_id.setText(intentResult.getContents());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
