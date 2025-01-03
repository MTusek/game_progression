package com.example.gameprogression;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewThemes;
    private List<String> themes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        recyclerViewThemes = findViewById(R.id.recyclerViewThemes);
        themes = new ArrayList<>();
        loadThemes();

        ThemeAdapter themeAdapter = new ThemeAdapter(this, themes);
        recyclerViewThemes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewThemes.setAdapter(themeAdapter);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_main) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (itemId == R.id.nav_notes) {
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });
        if (this instanceof NotesActivity) {
            bottomNavigationView.setSelectedItemId(R.id.nav_notes);
        }
    }

    private void loadThemes() {
        themes.add("Achievements");
        themes.add("Progress");
        themes.add("Diary");
        themes.add("Other");
    }
}