package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewPost extends AppCompatActivity {

    DatabaseReference ref;
    FirebaseAuth auth;
    Button display;
    EditText new_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        new_post = findViewById(R.id.newpost);
        display = findViewById(R.id.display);
        auth = FirebaseAuth.getInstance();
        display.setOnClickListener(d -> {
            String note1 = new_post.getText().toString().trim();
            addNote(note1);
        });
    }

    public void addNote(String body){
        Post post = new Post(body, auth.getCurrentUser().getUid());
        ref = FirebaseDatabase.getInstance().getReference("Notes");

        ref.setValue(post)
                .addOnCompleteListener(n -> {
                    if(n.isSuccessful()){
                        Toast.makeText(this, "Added post successfully", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this, "failed", Toast.LENGTH_LONG).show();

                    }
                });

    }
}