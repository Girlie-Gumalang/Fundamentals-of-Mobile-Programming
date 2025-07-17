package com.example.quizfinals;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText regUsername, regPassword;
    Button regButton;
    TextView account;
    SharedPreferences sharedPref;

    public static final String KEY_SHARED_PREF = "userData";
    public static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        regUsername = findViewById(R.id.et_registration_username);
        regPassword = findViewById(R.id.et_registration_password);
        regButton = findViewById(R.id.btn_registration);
        account = findViewById(R.id.tv_account);

        sharedPref = getSharedPreferences(KEY_SHARED_PREF, MODE_PRIVATE);

        // Verification of user logged in, false by default since it is the start of the program
        boolean isLoggedIn = sharedPref.getBoolean("isLoggedIn", false);

        // Check if the user is already logged in
        if (isLoggedIn) {
            Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        regButton.setOnClickListener(v -> {
            String username = regUsername.getText().toString();
            String password = regPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (sharedPref.contains(username)) {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            }
            else {
                SharedPreferences.Editor editor = sharedPref.edit(); // Saved username as a key
                editor.putString(username, password);
                editor.apply();

                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        account.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}