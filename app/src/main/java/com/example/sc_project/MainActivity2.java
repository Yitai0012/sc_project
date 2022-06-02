package com.example.sc_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button register_button;
    TextInputEditText email_id;
    TextInputEditText email_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        register_button=(Button)findViewById(R.id.register_button);
        email_id = (TextInputEditText)findViewById(R.id.email_id);
        email_pw = (TextInputEditText) findViewById(R.id.email_pw);
        mAuth=FirebaseAuth.getInstance();

        register_button.setOnClickListener(View -> {
            createUser();

        });

    }
    private void createUser(){
        String email = email_id.getText().toString();
        String password = email_pw.getText().toString();

        if (TextUtils.isEmpty(email)){
            email_id.setError("Email cannot be empty");
            email_id.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            email_pw.setError("Password cannot be empty");
            email_pw.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity2.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity2.this, MainActivity.class));
                    }else{
                        Toast.makeText(MainActivity2.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}