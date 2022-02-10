package com.example.mynotebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Home extends AppCompatActivity {


    ListView myListv;
    ArrayList<String> mylist = new ArrayList<>();
    ArrayList<String> myl = new ArrayList<>();
    ArrayList<String> myl2 = new ArrayList<>();

    private  Toolbar toolbar;
    DatabaseReference ref;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    ArrayAdapter<String> myarrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //toolbar =findViewById(R.id.tool_bar);
        //setSupportActionBar(toolbar);



        myarrayAdapter = new ArrayAdapter<String>(Home.this, android.R.layout.simple_list_item_1, mylist);
        myListv = findViewById(R.id.listview);

        FloatingActionButton mFAB = findViewById(R.id.btn_new_post);
        mFAB.setOnClickListener(v -> startActivity(new Intent(this , NewPost.class)));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String CUid = user.getUid();
        Query query = FirebaseDatabase.getInstance().getReference("Notes")
                .orderByChild("uid").equalTo(CUid);


            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    for (DataSnapshot ds : snapshot.getChildren()) {

                        String value = (String) ds.child("topic").getValue(String.class);
                        String value2 = (String) ds.child("body").getValue(String.class);

                        myl.add(value);
                        myl2.add(value2);
                    }

                    for (int i = 0; i < myl.size(); i++) {
                        mylist.add(myl.get(i));
                     }
                    myListv.setAdapter(myarrayAdapter);

                    myListv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                        //Toast.makeText(view.getContext(), myl2.get(position)+" two", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(view.getContext(), DisplayNote.class);
                                        intent.putExtra("note", myl2.get(position));
                                        startActivity(intent);
                        }
                    });
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                startActivity(new Intent(this,Home.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(this,Profile.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(this,MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}