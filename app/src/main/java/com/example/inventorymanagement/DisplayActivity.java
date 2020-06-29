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

import java.util.Map;
import java.util.Objects;

public class DisplayActivity extends AppCompatActivity {
    private TextView name;
    private TextView code;
    private TextView description;
    private  TextView date;
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
        code = findViewById(R.id.codeText);
        name = findViewById(R.id.ProductName);
        description = findViewById(R.id.productDescription);
        date = findViewById(R.id.CurrentDate);
        time = findViewById(R.id.CurrentTime);

    }
   //Scans QR Code to view details of the product
    public void scanProduct(View view)
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
    
     //Displaying the details of the product
    public void submitCode(View view)
    {

        String barcode = code.getText().toString();
       DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child(barcode);
       
        //Retrieving multiple data stored in firebase database
        mRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Map<String,String> map = (Map<String, String>)dataSnapshot.getValue();

               assert map != null;
               String pname = map.get("PRODUCT NAME");
               name.setText(pname);
               String pdescription = map.get("PRODUCT DESCRIPTION");
               description.setText(pdescription);
               String pdate = map.get("CURRENT DATE OF SERVICE");
               date.setText(pdate);
               String ptime = map.get("CURRENT TIME OF SERVICE");
               time.setText(ptime);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }
}
