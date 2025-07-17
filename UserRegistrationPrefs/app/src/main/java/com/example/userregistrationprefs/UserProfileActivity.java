package com.example.userregistrationprefs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserProfileActivity extends AppCompatActivity {

    TextView display;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        display = findViewById(R.id.tv_display_info);
        logout = findViewById(R.id.btn_logout);

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREF, MODE_PRIVATE);
        String fname = sharedPreferences.getString(MainActivity.KEY_FNAME, null);
        String lname= sharedPreferences.getString(MainActivity.KEY_LNAME, null);
        int age = sharedPreferences.getInt(MainActivity.KEY_AGE, 0);

        String info = "First Name: " + fname + "\nLast Name: " + lname + "\nAge: " + age;
        display.setText(info);

        logout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }
}