package com.example.quizfinals;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button btnLogin;
    TextView createAccount;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.et_login_username);
        loginPassword = findViewById(R.id.et_login_password);
        btnLogin = findViewById(R.id.btn_login);
        createAccount = findViewById(R.id.tv_create_account);

        sharedPref = getSharedPreferences(MainActivity.KEY_SHARED_PREF, MODE_PRIVATE);

        btnLogin.setOnClickListener(v -> {
            String username = loginUsername.getText().toString();
            String password = loginPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get the password for the entered username
            String savedPassword = sharedPref.getString(username, null);

            // Check if the user exists and password matches
            if (savedPassword == null) {
                Toast.makeText(this, "Username does not exist", Toast.LENGTH_SHORT).show();
            }
            else if (!savedPassword.equals(password)) {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
            }
            else {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("isLoggedIn", true);  // Mark user as logged in
                editor.putString(MainActivity.KEY_USERNAME, username);
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, DisplayActivity.class);
                startActivity(intent);
                finish();
            }
        });

        createAccount.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}