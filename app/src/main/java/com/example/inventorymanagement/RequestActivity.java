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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestActivity extends AppCompatActivity {

    private ListView usersList;
    private ArrayList<String> users;
    private ArrayAdapter<String> adapter;
    /*private ListView requestList;
    private ArrayList<String> requests ;
    private ArrayAdapter<String> requestAdapter ;*/
    private DatabaseReference reference21;
    private DatabaseReference reference22;
    private MakeRequestActivity ra ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        ra = new MakeRequestActivity();
        String code = ra.procode;
        String empCode = ra.empId;
        usersList = findViewById(R.id.listRequest);
        users = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,users);
        usersList.setAdapter(adapter);

        reference21 = FirebaseDatabase.getInstance().getReference().child("Requests");
       reference21.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

              String requestValue = dataSnapshot.child(empCode).child(code).getValue(String.class);
              users.add(requestValue);
              adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.notifyDataSetChanged();
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
