package com.example.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class RegisterActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText mail;
    private EditText pass;
    private EditText confirm_pass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameText = findViewById(R.id.nameText);
        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        confirm_pass = findViewById(R.id.confirm_pass);
        Button sign_up = findViewById(R.id.sign_up);
        mAuth = FirebaseAuth.getInstance();
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString();
                String password = pass.getText().toString();
                String confirmPass = confirm_pass.getText().toString();
                final String display_name = nameText.getText().toString();
                if(!TextUtils.isEmpty(display_name)||!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)||confirmPass.equals(password))
                {
                   mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful())
                          {
                              Toast.makeText(RegisterActivity.this,"Creating Account",Toast.LENGTH_LONG).show();
                              Intent mainIntent = new Intent(RegisterActivity.this,AdminActivity.class);
                              startActivity(mainIntent);
                          }
                          else
                          {
                              Toast.makeText(RegisterActivity.this,"THERE IS SOME ERROR",Toast.LENGTH_LONG).show();
                          }
                       }
                   });



                }

            }
        });
    }

}