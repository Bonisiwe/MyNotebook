package com.example.mynotebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    DatabaseReference ref;
    FirebaseAuth auth;
    Button display;

    ListView myListv;
    ArrayList<String> mylist = new ArrayList<>();
    ArrayList<String> myl = new ArrayList<>();

    ArrayAdapter<String> myarrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myarrayAdapter = new ArrayAdapter<String>(Home.this, android.R.layout.simple_list_item_1, mylist);
        myListv = findViewById(R.id.listview);
        myListv.setAdapter(myarrayAdapter);
        display = findViewById(R.id.display);
        auth = FirebaseAuth.getInstance();

        FloatingActionButton mFAB = findViewById(R.id.btn_new_post);
        mFAB.setOnClickListener(v -> startActivity(new Intent(this , NewPost.class)));

        display.setOnClickListener(d -> {
            String note1 ="I got school work to donn";
            addNotes(note1);
        });
    }

    public void addNotes(String body){
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
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                String value = snapshot.getValue(String.class);

                myl.add(value);
                mylist.add(myl.get(0));
                myarrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved( DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved( DataSnapshot snapshot,  String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

}