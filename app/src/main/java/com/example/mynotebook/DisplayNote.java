package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayNote extends AppCompatActivity {

    TextView Note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_note);

        Intent intent = getIntent();
        String note = intent.getStringExtra("note");
        Note = findViewById(R.id.tv_view_body);
        Note.setText(note);


    }
}