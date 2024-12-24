package com.example.gameprogression;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        String gameName = getIntent().getStringExtra("gameName");
        int gameCover = getIntent().getIntExtra("gameCover", R.drawable.placeholder_cover);

        TextView gameTitle = findViewById(R.id.gameTitle);
        ImageView gameCoverImage = findViewById(R.id.gameCover);

        gameTitle.setText(gameName);
        gameCoverImage.setImageResource(gameCover);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

    }
}
