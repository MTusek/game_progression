package com.example.gameprogression;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NotesListActivity extends AppCompatActivity {

    private TextView notesTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        notesTitle = findViewById(R.id.notesTitle);

        String theme = getIntent().getStringExtra("theme");
        notesTitle.setText(""+ theme);
    }
}
