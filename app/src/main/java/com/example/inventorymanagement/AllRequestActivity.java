package com.example.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AllRequestActivity extends AppCompatActivity {

    RecyclerView mRequestList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_request);
        mRequestList = findViewById(R.id.user_request_list);

        mRequestList.setHasFixedSize(true);
        mRequestList.setLayoutManager(new LinearLayoutManager(this));
    }
}
