package com.example.oddorevenchecker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OddActivity extends AppCompatActivity {

    TextView displayOdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odd);

        displayOdd = findViewById(R.id.display_odd);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        String oddDisplay = String.valueOf(intent.getDoubleExtra("odd", 0));
        displayOdd.setText(oddDisplay);
    }
}