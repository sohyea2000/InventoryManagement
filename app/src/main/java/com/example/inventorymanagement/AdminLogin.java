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


public class AdminLogin extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private EditText adminmail;
    private EditText adminpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        Button login = findViewById(R.id.loginButton);
        adminmail = findViewById(R.id.adminMail);
        adminpass = findViewById(R.id.adminPass);
        mAuth = FirebaseAuth.getInstance();
        Button signupButton = findViewById(R.id.createButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogin.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = adminmail.getText().toString();
                String password = adminpass.getText().toString();
                if(!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password))
                {
                   mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful())
                           {

                               Intent mainIntent = new Intent(AdminLogin.this,AdminActivity.class);
                               mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                               startActivity(mainIntent);

                           }
                           else
                           {

                               Toast.makeText(AdminLogin.this,"CANNOT SIGNIN PLEASE CHECK THE FORM AGAIN",Toast.LENGTH_LONG).show();
                           }
                       }
                   });
                }
                else
                {
                    Toast.makeText(AdminLogin.this,"Email or Password is empty",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}
