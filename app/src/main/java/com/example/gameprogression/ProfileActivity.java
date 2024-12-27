package com.example.gameprogression;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    private EditText usernameInput;
    private Button updateUsernameButton, logoutButton;
    private Switch darkThemeToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameInput = findViewById(R.id.usernameInput);
        updateUsernameButton = findViewById(R.id.updateUsernameButton);
        logoutButton = findViewById(R.id.logoutButton);
        darkThemeToggle = findViewById(R.id.darkThemeToggle);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_main) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (itemId == R.id.nav_notes) {
                startActivity(new Intent(this, NotesActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                return true;
            }
            return false;
        });
        if (this instanceof ProfileActivity) {
            bottomNavigationView.setSelectedItemId(R.id.nav_profile);
        }
        //Theme toggle
        SharedPreferences preferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);
        boolean isDarkTheme = preferences.getBoolean("DarkTheme", false);
        darkThemeToggle.setChecked(isDarkTheme);

        darkThemeToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("DarkTheme", isChecked);
            editor.apply();

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        // Update username
        updateUsernameButton.setOnClickListener(v -> {
            String newUsername = usernameInput.getText().toString().trim();
            if (newUsername.isEmpty()) {
                Toast.makeText(ProfileActivity.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Username", newUsername);
                editor.apply();
                Toast.makeText(ProfileActivity.this, "Username updated to: " + newUsername, Toast.LENGTH_SHORT).show();
            }
        });

        // Logout
        logoutButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Toast.makeText(ProfileActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        });
    }
}
