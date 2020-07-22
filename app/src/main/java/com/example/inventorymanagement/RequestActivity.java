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
import java.util.List;
import java.util.Map;

public class RequestActivity extends AppCompatActivity {

    private ListView usersList;
    private List<String> users;
    private ArrayAdapter<String> adapter;
    /*private ListView requestList;
    private ArrayList<String> requests ;
    private ArrayAdapter<String> requestAdapter ;*/
    private DatabaseReference reference21;
    private DatabaseReference reference22;
   public EmployeeId id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        id = new EmployeeId();
       // String code = .procode;
        String empCode = EmployeeId.empId;
        usersList = findViewById(R.id.listRequest);
        users = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,users);
        usersList.setAdapter(adapter);

        reference21 = FirebaseDatabase.getInstance().getReference().child("Requests").child(empCode);
       reference21.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

               Map<String,String> map = (Map<String, String>)dataSnapshot.getValue();
                String requestValue = map.toString();
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
