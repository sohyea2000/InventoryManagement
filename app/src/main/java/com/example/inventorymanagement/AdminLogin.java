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
        //initializing firebase authentication

        mAuth = FirebaseAuth.getInstance();
        Button signupButton = findViewById(R.id.createButton);

        //Gets executed when a user uses the app for the first time to create an account

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogin.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        //Gets executed when admin wants to login to the app

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = adminmail.getText().toString();
                String password = adminpass.getText().toString();
                if(!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)) // checks whether e-mail or password is empty
                {
                    //sign in function for existing user
                   mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {

                           //Gets executed when login is successful
                           if(task.isSuccessful())
                           {

                               Intent mainIntent = new Intent(AdminLogin.this,AdminActivity.class);
                               mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                               startActivity(mainIntent);

                           }

                           //when e-mail or password doesnot match
                           else
                           {

                               Toast.makeText(AdminLogin.this,"CANNOT SIGNIN PLEASE CHECK THE FORM AGAIN",Toast.LENGTH_LONG).show();
                           }
                       }
                   });
                }

                //when e-mail or password fields are empty
                else
                {
                    Toast.makeText(AdminLogin.this,"Email or Password is empty",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}
