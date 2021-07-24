package com.example.mynotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String email ="16@gmail.com";
        String password ="1234556";
        auth = FirebaseAuth.getInstance();
        Register(email,password);

    }

    void Register(String email, String password){
        auth.createUserWithEmailAndPassword( email, password).addOnCompleteListener(task-> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Hello world hello for reAL", Toast.LENGTH_SHORT).show();

                User user = new User (email,password);

                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(user).addOnCompleteListener(tas -> {
                            if(tas.isSuccessful()){
                                Toast.makeText(this, "user registered", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(this, "failed failed", Toast.LENGTH_SHORT).show();

                            }
                });

            } else {
                Toast.makeText(this, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });
    }
}