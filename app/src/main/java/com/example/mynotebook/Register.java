package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    FirebaseAuth auth;
    Button reg;
    EditText ename,eemail,epassword,econfirmP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        ename = findViewById(R.id.name);
        eemail = findViewById(R.id.email);
        epassword = findViewById(R.id.pass);
        econfirmP = findViewById(R.id.passc);

        reg = findViewById(R.id.regi);
        reg.setOnClickListener(r -> {
            String name = ename.getText().toString().trim();
            String email = eemail.getText().toString().trim();
            String password = epassword.getText().toString().trim();
            String confirmP = econfirmP.getText().toString().trim();

            Register(name,email,password,confirmP);
            //String n = "y@gmail.com";
            //String p = "234";
            //Register(n,p);

        });

    }
    void Register(String name, String email, String password, String confirmP){
        //String name, String email, String password, String confirmP

        auth.createUserWithEmailAndPassword( email, password).addOnCompleteListener(task-> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Hello world hello for reAL", Toast.LENGTH_SHORT).show();

                User user = new User (email,password);

                FirebaseDatabase.getInstance().getReference("Users")
                        .child(auth.getCurrentUser().getUid())
                        .setValue(user).addOnCompleteListener(tas -> {
                    if(tas.isSuccessful()){
                        Toast.makeText(this, "user registered", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "failed failed", Toast.LENGTH_SHORT).show();

                    }
                });


            } else {
                Toast.makeText(this,"nothing" , Toast.LENGTH_SHORT).show();

                //comes here only if i am using already existing email in the database
            }
        });
    }
}