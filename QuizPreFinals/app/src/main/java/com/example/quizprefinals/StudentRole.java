package com.example.quizprefinals;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentRole extends AppCompatActivity {

    RadioGroup roleGroup;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_role);

        roleGroup = findViewById(R.id.studRole_radgroup);
        nextBtn = findViewById(R.id.btn2);

    }

    @Override
    protected void onStart() {
        super.onStart();

        String name = getIntent().getStringExtra("name");
        String program = getIntent().getStringExtra("program");

        nextBtn.setOnClickListener(v -> {

            int selectedId = roleGroup.getCheckedRadioButtonId();
            RadioButton selectedRadio = findViewById(selectedId);
            String role = selectedRadio.getText().toString();

            Intent intent = new Intent(StudentRole.this, StudentSkills.class);
            intent.putExtra("name", name);
            intent.putExtra("program", program);
            intent.putExtra("role", role);
            startActivity(intent);
        });
    }
}