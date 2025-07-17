package com.example.quizprefinals;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText nameET, usernameET, passwordET;
    Spinner courseSpinner;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.name);
        usernameET = findViewById(R.id.username);
        passwordET = findViewById(R.id.password);
        courseSpinner = findViewById(R.id.course_spinner);
        nextBtn = findViewById(R.id.btn1);
    }

    @Override
    protected void onStart() {
        super.onStart();

        nextBtn.setOnClickListener(v -> {
            String name = nameET.getText().toString();
            String program = courseSpinner.getSelectedItem().toString();

            Intent intent = new Intent(MainActivity.this, StudentRole.class);
            intent.putExtra("name", name);
            intent.putExtra("program", program);
            startActivity(intent);
        });
    }
}