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
    private SharedPreferences preferences; // Declare globally
    private boolean isRestarting = false;  // Prevent redundant restarts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);
        applySavedTheme();

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

        //Darkmode
        boolean isDarkTheme = preferences.getBoolean("DarkTheme", false);
        darkThemeToggle.setChecked(isDarkTheme);

        darkThemeToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isRestarting) {
                isRestarting = true;
                saveThemePreference(isChecked);
                applyTheme(isChecked);
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

        //Logout button
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
    private void applySavedTheme() {
        boolean isDarkTheme = preferences.getBoolean("DarkTheme", false);
        AppCompatDelegate.setDefaultNightMode(isDarkTheme ?
                AppCompatDelegate.MODE_NIGHT_YES :
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void saveThemePreference(boolean isDarkTheme) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("DarkTheme", isDarkTheme);
        editor.apply();
    }

    private void applyTheme(boolean isDarkTheme) {
        AppCompatDelegate.setDefaultNightMode(isDarkTheme ?
                AppCompatDelegate.MODE_NIGHT_NO:
                AppCompatDelegate.MODE_NIGHT_YES);

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRestarting = false;
    }
}
