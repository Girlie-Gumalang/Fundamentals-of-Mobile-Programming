package com.example.oddorevenchecker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EvenActivity extends AppCompatActivity {

    TextView displayEven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even);

        displayEven = findViewById(R.id.display_even);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        String evenDisplay = String.valueOf(intent.getDoubleExtra("even", 0));
        displayEven.setText(evenDisplay);
    }
}