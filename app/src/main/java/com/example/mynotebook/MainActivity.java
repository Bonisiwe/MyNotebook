package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String email ="65543@gmail.com";
        String password ="1234556";
        auth = FirebaseAuth.getInstance();
        Register(email,password);
    }

    void Register(String email, String password){
        auth.createUserWithEmailAndPassword( email, password).addOnCompleteListener(task-> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Hello world hello", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });
    }
}