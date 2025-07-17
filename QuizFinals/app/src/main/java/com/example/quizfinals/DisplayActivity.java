package com.example.quizfinals;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    TextView output;
    Button btnLogout;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        output = findViewById(R.id.tv_displayText);
        btnLogout = findViewById(R.id.btn_logout);

        sharedPref = getSharedPreferences(MainActivity.KEY_SHARED_PREF, MODE_PRIVATE);

        boolean isLoggedIn = sharedPref.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            Intent intent = new Intent(DisplayActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            String username = sharedPref.getString(MainActivity.KEY_USERNAME, null);
            String displayInfo = "Welcome, " + username + "!";
            output.setText(displayInfo);
        }

        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("isLoggedIn", false);  // Mark user as logged out
            editor.apply();

            Intent intent = new Intent(DisplayActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}