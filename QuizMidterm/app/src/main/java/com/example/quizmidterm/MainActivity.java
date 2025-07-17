package com.example.quizmidterm;

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

    EditText fnum, snum;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fnum = findViewById(R.id.first_num);
        snum = findViewById(R.id.second_num);
        btn = findViewById(R.id.calc_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btn.setOnClickListener(v->{
            double first = Double.parseDouble(fnum.getText().toString());
            double second = Double.parseDouble(snum.getText().toString());
            double product  = first * second;

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("product", product);
            startActivity(intent);
        });
    }
}