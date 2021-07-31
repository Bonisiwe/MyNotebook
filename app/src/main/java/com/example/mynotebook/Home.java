package com.example.mynotebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Home extends AppCompatActivity {


    ListView myListv;
    ArrayList<String> mylist = new ArrayList<>();
    ArrayList<String> myl = new ArrayList<>();
    DatabaseReference ref;
    FirebaseAuth auth = FirebaseAuth.getInstance();


    ArrayAdapter<String> myarrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myarrayAdapter = new ArrayAdapter<String>(Home.this, android.R.layout.simple_list_item_1, mylist);
        myListv = findViewById(R.id.listview);

        FloatingActionButton mFAB = findViewById(R.id.btn_new_post);
        mFAB.setOnClickListener(v -> startActivity(new Intent(this , NewPost.class)));
        ref = FirebaseDatabase.getInstance().getReference("Notes");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String CUid = user.getUid();

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    //if(CUid.equals(snapshot.child("uid").toString())) {

                        for (DataSnapshot ds : snapshot.getChildren()) {

                            String value = (String) ds.child("body").getValue(String.class);
                            myl.add(value);
                        }

                    //}
                    for (int i = 0; i < myl.size(); i++) {
                            mylist.add(myl.get(i));

                    }
                    myListv.setAdapter(myarrayAdapter);

                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu, menu);
        return true;
    }


}