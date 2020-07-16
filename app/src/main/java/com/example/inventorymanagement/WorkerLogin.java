package com.example.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Objects;


public class WorkerLogin extends AppCompatActivity {
    private TextView employee_id;
    private DatabaseReference workerDatabase;


    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_login);
        employee_id = findViewById(R.id.employee_id);
        Button login = findViewById(R.id.worker_login);


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);

        //gets executed after scanning of the employee id card
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = employee_id.getText().toString();
                workerDatabase = FirebaseDatabase.getInstance().getReference();
                DatabaseReference ref = workerDatabase.child(id);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = Objects.requireNonNull(dataSnapshot.child("EMPLOYEE ID").getValue()).toString();
                        //checks if employee id exists in the database
                        if(value.equals(id))
                        {
                            Intent intent = new Intent(WorkerLogin.this,WelcomeActivty.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }


    //Scans id card of the worker
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
                employee_id.setText("CANCELLED");
            }
            else{
                employee_id.setText(intentResult.getContents());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    

}
