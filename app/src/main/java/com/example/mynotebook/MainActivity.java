package com.example.mynotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView toReg;
    EditText eemail1, epassword1;
    Button Login;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        eemail1 = findViewById(R.id.email1);
        epassword1 = findViewById(R.id.pass1);
        Login = findViewById(R.id.login);
        Login.setOnClickListener(l -> {
            String email1 = eemail1.getText().toString().trim();
            String password1 = epassword1.getText().toString().trim();
            if(email1.isEmpty()){
                eemail1.setError("Email not entered");
            }
            if(password1.isEmpty()){
                epassword1.setError("Password not entered");
            }
            else {
                Login(email1, password1);
            }
        });

        toReg = findViewById(R.id.textView);
        toReg.setOnClickListener(tor -> {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        });
    }
    void Login(String email, String password){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(t -> {
                    if(t.isSuccessful()){
                        startActivity(new Intent(this, Home.class));
                    }
                });
    }

}