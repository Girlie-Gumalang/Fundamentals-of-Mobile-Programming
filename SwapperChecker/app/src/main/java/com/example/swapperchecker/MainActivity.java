package com.example.swapperchecker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText firstText, secondText;
    Button swapBtn, checkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstText = findViewById(R.id.first_word);
        secondText = findViewById(R.id.second_word);

        swapBtn = findViewById(R.id.swap_btn);
        checkBtn = findViewById(R.id.check_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();

        swapBtn.setOnClickListener(v -> {
            String first_word = firstText.getText().toString();
            String second_word = secondText.getText().toString();

            firstText.setText(second_word);
            secondText.setText(first_word);
        });

        checkBtn.setOnClickListener(v -> {
            String first_word = firstText.getText().toString();
            String second_word = secondText.getText().toString();

            String resultMessage = "";

            if(first_word.equals(second_word)) {
                resultMessage = "SAME";
            }
            else{
                resultMessage = "NOT THE SAME";
            }

            Intent intent = new Intent (MainActivity.this, MyMessage.class);
            intent.putExtra("resultMessage", resultMessage);
            startActivity(intent);
        });
    }
}