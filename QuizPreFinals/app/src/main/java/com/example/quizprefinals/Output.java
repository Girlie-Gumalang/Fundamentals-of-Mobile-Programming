package com.example.quizprefinals;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Output extends AppCompatActivity {
    TextView outputTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        outputTV = findViewById(R.id.output_txt);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String name = getIntent().getStringExtra("name");
        String program = getIntent().getStringExtra("program");
        String role = getIntent().getStringExtra("role");
        String skills = getIntent().getStringExtra("skills");

        String finalOutput = "Name: " + name + "\n" + "Program: " + program + "\n" + "Student role: " + role + "\n" + "Student skills: " + skills;
        outputTV.setText(finalOutput);
    }
}