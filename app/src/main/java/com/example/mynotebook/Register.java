package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    FirebaseAuth auth;
    Button reg;
    EditText ename,eemail,epassword,econfirmP;
    TextView pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        ename = findViewById(R.id.name);
        eemail = findViewById(R.id.email1);
        epassword = findViewById(R.id.pass1);
        econfirmP = findViewById(R.id.passc);
        pass = findViewById(R.id.textView3);
        pass.setOnClickListener(v ->{
            startActivity(new Intent(this,MainActivity.class));

        });

        reg = findViewById(R.id.regi);
        reg.setOnClickListener(r -> {

            String name = ename.getText().toString().trim();
            String email = eemail.getText().toString().trim();
            String password = epassword.getText().toString().trim();
            String confirmP = econfirmP.getText().toString().trim();

            if(name.length() == 0){
                ename.setError("Enter your username");
            }
            else if(email.length() == 0){
                eemail.setError("Enter email");
            }
            else if(password.length() == 0){
                epassword.setError("Enter password");
            }
            else if (confirmP.length() == 0){
                econfirmP.setError("Enter confirmation password");
            }
            else if(!password.equals(confirmP)){
                econfirmP.setError("Passwords do not match");
            }
            else {
                Register(name, email, password, confirmP);
            }
        });

    }
    void Register(String name, String email, String password, String confirmP){

        auth.createUserWithEmailAndPassword( email, password).addOnCompleteListener(task-> {
            if (task.isSuccessful()) {

                User user = new User (email,password, name);

                FirebaseDatabase.getInstance().getReference("Users")
                        .child(auth.getCurrentUser().getUid())
                        .setValue(user).addOnCompleteListener(tas -> {
                    if(tas.isSuccessful()){
                        Toast.makeText(this, "user registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(this, "failed to register user", Toast.LENGTH_SHORT).show();

                    }
                });

            } else {
                Toast.makeText(this, task.getException().getMessage() , Toast.LENGTH_SHORT).show();
             }
        });
    }
}