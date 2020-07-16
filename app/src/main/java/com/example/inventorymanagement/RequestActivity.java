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

public class RequestActivity extends AppCompatActivity {

    private ListView usersList;
    private ArrayList<String> users;
    private ArrayAdapter<String> adapter;
    /*private ListView requestList;
    private ArrayList<String> requests ;
    private ArrayAdapter<String> requestAdapter ;*/
    private DatabaseReference ref;
   // MakeRequestActivity ra = new MakeRequestActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

       // String empId = ra.empId;
        //String code = ra.procode;
        usersList = findViewById(R.id.listRequest);
        users = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,users);
        usersList.setAdapter(adapter);

        ref = FirebaseDatabase.getInstance().getReference().child("Requests");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              String requestValue = dataSnapshot.getValue(String.class);
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