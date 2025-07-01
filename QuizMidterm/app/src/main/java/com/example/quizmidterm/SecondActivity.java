package com.example.quizmidterm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        output = findViewById(R.id.output);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String display = String.valueOf(intent.getDoubleExtra("product", 0));
        output.setText(display);
    }
}