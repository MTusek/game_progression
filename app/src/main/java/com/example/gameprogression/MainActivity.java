package com.example.gameprogression;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_main) {
                return true;
            } else if (itemId == R.id.nav_notes) {
                startActivity(new Intent(this, NotesActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });
        if (this instanceof MainActivity) {
            bottomNavigationView.setSelectedItemId(R.id.nav_main);
        }

        // Lista videoigri

        List<Game> games = new ArrayList<>();
        games.add(new Game("Terraria", R.drawable.terraria_cover));
        games.add(new Game("Minecraft",R.drawable.minecraft_cover));
        games.add(new Game("Simple MMO", R.drawable.smmo_cover));
        games.add(new Game("Warframe: 1999", R.drawable.warframe_cover));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        GameAdapter adapter = new GameAdapter(games, game -> {

            Intent intent = new Intent(MainActivity.this, GameDetailsActivity.class);
            intent.putExtra("gameName", game.getName());
            intent.putExtra("gameCover", game.getCover()); // Pass the cover image resource
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}
