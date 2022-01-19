package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewPost extends AppCompatActivity {

    DatabaseReference ref, ref2;
    FirebaseAuth auth;
    Button display;
    EditText new_post;
    EditText topic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        new_post = findViewById(R.id.newpost);
        topic = findViewById(R.id.Top_ic);
        display = findViewById(R.id.display);
        auth = FirebaseAuth.getInstance();
        display.setOnClickListener(d -> {
            String note1 = new_post.getText().toString().trim();
            String top = topic.getText().toString().trim();

            addNote(note1, top);
        });
    }

    public void addNote(String body, String topic){
        Post post = new Post(body, auth.getCurrentUser().getUid(), topic);
        ref = FirebaseDatabase.getInstance().getReference("Notes").push();


        String key = ref.getKey();
        post.setPostKey(key);

        ref.setValue(post)
                .addOnCompleteListener(n -> {
                     if(n.isSuccessful()){
                        Toast.makeText(this, "Added Note successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(this, Home.class));
                    }
                    else{
                        Toast.makeText(this, "failed", Toast.LENGTH_LONG).show();

                    }
                });


    }
}