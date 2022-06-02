package com.example.sc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity3 extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btn_logout=(Button)findViewById(R.id.btn_logout);
        mAuth=FirebaseAuth.getInstance();

        btn_logout.setOnClickListener(View ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity3.this,MainActivity.class));
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(MainActivity3.this,MainActivity.class));
        }
    }
}