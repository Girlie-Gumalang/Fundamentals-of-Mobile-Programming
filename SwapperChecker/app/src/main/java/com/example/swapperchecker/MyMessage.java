package com.example.swapperchecker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MyMessage extends AppCompatActivity {
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);

        result = findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /*Intent intent = getIntent();
        String message = intent.getStringExtra("resultMessage");
        result.setText(message);*/

        result.setText(getIntent().getStringExtra("resultMessage")); // shortcut
    }
}