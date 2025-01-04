package com.example.gameprogression;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GameAdapter adapter;
    private List<Game> games;

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
        bottomNavigationView.setSelectedItemId(R.id.nav_main);

        // Initialize games list
        games = new ArrayList<>();
        games.add(new Game("Terraria", R.drawable.terraria_cover));
        games.add(new Game("Minecraft", R.drawable.minecraft_cover));
        games.add(new Game("Simple MMO", R.drawable.smmo_cover));
        games.add(new Game("Warframe: 1999", R.drawable.warframe_cover));
        games.add(new Game("Hollow Knight", R.drawable.placeholder));
        games.add(new Game("Cuphead", R.drawable.placeholder));
        games.add(new Game("Rocket League", R.drawable.placeholder));
        games.add(new Game("Stardew Valley", R.drawable.placeholder));
        games.add(new Game("Cyberpunk 2077", R.drawable.placeholder));
        games.add(new Game("Elden Ring", R.drawable.placeholder));
        games.add(new Game("Grand Theft Auto V - (GTAV)", R.drawable.placeholder));
        games.add(new Game("Project Zomboid", R.drawable.placeholder));
        games.add(new Game("Hitman", R.drawable.placeholder));
        games.add(new Game("Brawlhalla", R.drawable.placeholder));
        games.add(new Game("Valorant", R.drawable.placeholder));


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new GameAdapter(games, game -> {
            Intent intent = new Intent(MainActivity.this, GameDetailsActivity.class);
            intent.putExtra("gameName", game.getName());
            intent.putExtra("gameCover", game.getCover());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        // Add Spinner for sorting
        Spinner sortSpinner = findViewById(R.id.sortSpinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.sort_options, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(spinnerAdapter);

        sortSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                if (position == 0) {
                    sortGamesAlphabetically();
                } else if (position == 1) {
                    sortGamesByReverseAlphabet();
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });

        // Add SearchView for filtering
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterGames(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterGames(newText);
                return true;
            }
        });
    }

    private void sortGamesAlphabetically() {
        Collections.sort(games, Comparator.comparing(Game::getName));
        adapter.notifyDataSetChanged();
    }

    private void sortGamesByReverseAlphabet() {
        Collections.sort(games, (game1, game2) -> game2.getName().compareTo(game1.getName()));
        adapter.notifyDataSetChanged();
    }

    private void filterGames(String query) {
        List<Game> filteredList = new ArrayList<>();
        for (Game game : games) {
            if (game.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(game);
            }
        }
        adapter.updateGames(filteredList);
    }
}
