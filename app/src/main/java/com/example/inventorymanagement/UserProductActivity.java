package com.example.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserProductActivity extends AppCompatActivity {

    private ListView usersList;
    private ArrayList<String> users;
    private ArrayAdapter<String> adapter;
    private DatabaseReference reference;
    //private DisplayActivity ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_product);
        //ab = new DisplayActivity();
        //String code = ab.barcode;
        usersList = findViewById(R.id.usersList);
        users = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,users);
        usersList.setAdapter(adapter);
        reference = FirebaseDatabase.getInstance().getReference().child("USERPRODUCT");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String val = dataSnapshot.getValue(String.class);
                users.add(val);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
