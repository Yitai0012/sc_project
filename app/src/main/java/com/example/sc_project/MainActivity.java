package com.example.sc_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth.AuthStateListener authStateListener;
    FirebaseAuth mAuth;
    Button gobutton;
    Button btn_login;
    TextInputEditText em_id;
    TextInputEditText em_pw;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gobutton = findViewById(R.id.gobutton);

        mAuth=FirebaseAuth.getInstance();
        btn_login=(Button)findViewById(R.id.btn_login);
        gobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(View ->   {
            loginUser();
        });
     }

    private void loginUser(){
        String email = em_id.getText().toString();
        String password = em_pw.getText().toString();

        if(TextUtils.isEmpty(email)){
            em_id.setError("email cannot be empty");
            em_id.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            em_pw.setError("password cannot be empty");
            em_pw.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"User Login Successfully" ,Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,MainActivity3.class));
                    }else{
                        Toast.makeText(MainActivity.this,"User Login Failed" ,Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        //if(user == null){
            //startActivity(new Intent(MainActivity.this,MainActivity.class));
        //}
    }
}
