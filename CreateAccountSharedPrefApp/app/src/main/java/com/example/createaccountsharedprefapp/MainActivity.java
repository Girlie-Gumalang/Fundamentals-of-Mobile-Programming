package com.example.createaccountsharedprefapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_NAME = "nameKey";
    private static final String KEY_PASSWORD = "passKey";
    private static final String KEY_EMAIL = "emailKey";

    EditText etName, etPassword, etEmail;
    Button btnSave;
    TextView display;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        etEmail = findViewById(R.id.et_email);
        btnSave = findViewById(R.id.btn_save);
        display = findViewById(R.id.tv_display);

        sharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String password = etPassword.getText().toString();
            String email = etEmail.getText().toString();

            if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(KEY_NAME, name);
            editor.putString(KEY_PASSWORD, password);
            editor.putString(KEY_EMAIL, email);
            editor.apply();

            Toast.makeText(MainActivity.this, "Saved successfully!", Toast.LENGTH_SHORT).show();

            display.setText("Name: " + name + "\nPassword: " + password + "\nEmail: " + email);
        });

        // Load previously saved data
        String savedName = sharedPref.getString(KEY_NAME, "");
        String savedPassword = sharedPref.getString(KEY_PASSWORD, "");
        String savedEmail = sharedPref.getString(KEY_EMAIL, "");

        display.setText("Name: " + savedName + "\nPassword: " + savedPassword + "\nEmail: " + savedEmail);
    }
}
