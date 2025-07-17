package com.example.oddorevenchecker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText userInput;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = findViewById(R.id.et_user_input);
        btn = findViewById(R.id.check_btn);

        btn.setOnClickListener(v -> {
            double num = Double.parseDouble(userInput.getText().toString());

            if (num % 2 == 0) {
                Intent intent = new Intent(MainActivity.this, EvenActivity.class);
                intent.putExtra("even", num);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this, OddActivity.class);
                intent.putExtra("odd", num);
                startActivity(intent);
            }
        });
    }
}
