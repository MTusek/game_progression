package com.example.gameprogression;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        EditText gameNameInput = findViewById(R.id.gameNameInput);
        Button saveButton = findViewById(R.id.saveGameButton);

        saveButton.setOnClickListener(v -> {
            String gameName = gameNameInput.getText().toString().trim();
            if (!gameName.isEmpty()) {
                Toast.makeText(this, "Game saved: " + gameName, Toast.LENGTH_SHORT).show();
                finish(); // Close activity
            } else {
                Toast.makeText(this, "Game name cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
