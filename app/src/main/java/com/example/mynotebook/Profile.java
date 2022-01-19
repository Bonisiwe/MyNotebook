package com.example.mynotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView t1, t2;
    String mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        t1 = findViewById(R.id.text2);
        t2 = findViewById(R.id.text3);

        mAuth = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference mD =
                FirebaseDatabase.getInstance().getReference("Users");
        mD.child(mAuth)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User value = snapshot.getValue(User.class);
                        String email = value.getEmail();
                        String name = value.getUserName();
                        t1.setText(email);
                        t2.setText(name);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
}