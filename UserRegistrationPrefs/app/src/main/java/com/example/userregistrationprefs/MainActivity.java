package com.example.userregistrationprefs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName, age;
    Button submit;

    public static final String SHARED_PREF = "pref";
    public static final String KEY_FNAME = "fname";
    public static final String KEY_LNAME = "lname";
    public static final String KEY_AGE = "age";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        if (sharedPreferences.contains(KEY_FNAME)) {
            Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.et_first_name);
        lastName = findViewById(R.id.et_last_name);
        age = findViewById(R.id.et_age);
        submit = findViewById(R.id.btn_submit);

        submit.setOnClickListener(v -> {

            String first_name = firstName.getText().toString();
            String last_name = lastName.getText().toString();
            String user_age = age.getText().toString();

            if (first_name.isEmpty() || last_name.isEmpty() || user_age.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int userAge = Integer.parseInt(user_age);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_FNAME, first_name);
            editor.putString(KEY_LNAME, last_name);
            editor.putInt(KEY_AGE, userAge);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
            startActivity(intent);

        });
    }
}