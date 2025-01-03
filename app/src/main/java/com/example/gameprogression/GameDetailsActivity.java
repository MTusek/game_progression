package com.example.gameprogression;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameDetailsActivity extends AppCompatActivity {

    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        String gameName = getIntent().getStringExtra("gameName");
        int gameCover = getIntent().getIntExtra("gameCover", R.drawable.placeholder);

        TextView gameTitle = findViewById(R.id.gameTitle);
        ImageView gameCoverImage = findViewById(R.id.gameCover);
        Spinner categorySpinner = findViewById(R.id.categorySpinner);
        EditText userNotes = findViewById(R.id.userNotes);
        Button saveButton = findViewById(R.id.saveButton);
        Button backButton = findViewById(R.id.backButton);

        gameTitle.setText(gameName);
        gameCoverImage.setImageResource(gameCover);

        //Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCategory = "Miscellaneous"; // Default category
            }
        });

        saveButton.setOnClickListener(v -> {
            String notes = userNotes.getText().toString().trim();
            if (notes.isEmpty()) {
                Toast.makeText(this, "Please add some notes", Toast.LENGTH_SHORT).show();
            } else {
                // Save notes along with the selected category (to be implemented)
                Toast.makeText(this, "Notes saved under category: " + selectedCategory, Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> finish());
    }
}
