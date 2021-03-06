package com.example.inventorymanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class UpdateActivity extends AppCompatActivity {

    private TextView barCode;
    private EditText updateDate;
    private EditText updateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        barCode = findViewById(R.id.barCode);
        updateDate = findViewById(R.id.update_date);
        updateTime = findViewById(R.id.update_time);
    }

    //Scans code of the product which is to be updated
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
                barCode.setText("CANCELLED");
            }
            else{
                barCode.setText(intentResult.getContents());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // updates the date of service and current time of service in the database
    public void updateDetails(View view){
        String str = barCode.getText().toString();
        String date = updateDate.getText().toString();
        String time = updateTime.getText().toString();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        //changing value stored in a child in firebase database
        database.child(str).child("DATE OF ISSUE").setValue(date);
        database.child(str).child("DATE OF SUBMISSION").setValue(time);
        Intent intent = new Intent (UpdateActivity.this,AdminActivity.class);
        startActivity(intent);
    }
}
