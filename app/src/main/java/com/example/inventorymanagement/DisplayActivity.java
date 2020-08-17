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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DisplayActivity extends AppCompatActivity {
    private TextView name;
    private TextView code;
    private TextView description;
    private  TextView date;
    private TextView time;
    private EditText empName;
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
        empName = findViewById(R.id.name);
    }

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
    public void submitCode(View view)
    {
         String name1 = empName.getText().toString();
        String barcode = code.getText().toString();
       DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child(barcode);
       mRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Map<String,String> map = (Map<String, String>)dataSnapshot.getValue();

               assert map != null;
               String pname = map.get("PRODUCT NAME");
               name.setText(pname);
               String pdescription = map.get("PRODUCT DESCRIPTION");
               description.setText(pdescription);
               String pdate = map.get("DATE OF ISSUE");
               date.setText(pdate);
               String ptime = map.get("DATE OF SUBMISSION");
               time.setText(ptime);
               DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference().child("USERPRODUCT");
               HashMap<String,String> map1 = new HashMap<>();
               map1.put(barcode,pname+" : "+name1);
               myDatabase.setValue(map1);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
       String name2 = empName.getText().toString();
       String pname2 = name.getText().toString();
        DatabaseReference mRef2 = FirebaseDatabase.getInstance().getReference();
        Query refQuery = mRef2.child("Requests").orderByChild("autokey").equalTo(pname2+" : "+name2);
        refQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    dataSnapshot1.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
