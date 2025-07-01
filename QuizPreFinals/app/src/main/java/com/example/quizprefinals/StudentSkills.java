package com.example.quizprefinals;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentSkills extends AppCompatActivity {

    CheckBox javaCB, csharpCB, xmlCB, sqlCB, pythonCB;
    Button nextBtn;
    ArrayList<String> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_skills);

        javaCB = findViewById(R.id.java_cb);
        csharpCB = findViewById(R.id.csharp_cb);
        xmlCB = findViewById(R.id.xml_cb);
        sqlCB = findViewById(R.id.sql_cb);
        pythonCB = findViewById(R.id.python_cb);
        nextBtn = findViewById(R.id.btn3);

        setupCheckBox(javaCB, "Java");
        setupCheckBox(csharpCB, "C#");
        setupCheckBox(xmlCB, "XML");
        setupCheckBox(sqlCB, "SQL");
        setupCheckBox(pythonCB, "Python");
    }

    private void setupCheckBox(CheckBox cb, String skillName) {
        cb.setOnClickListener(v -> {
            if (cb.isChecked()) {
                studentList.add(skillName);
            } else {
                studentList.remove(skillName);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String name = getIntent().getStringExtra("name");
        String program = getIntent().getStringExtra("program");
        String role = getIntent().getStringExtra("role");

        nextBtn.setOnClickListener(v -> {
            String skills = String.join(" ", studentList);
            Intent intent = new Intent(StudentSkills.this, Output.class);
            intent.putExtra("name", name);
            intent.putExtra("program", program);
            intent.putExtra("role", role);
            intent.putExtra("skills", skills);
            startActivity(intent);
        });
    }
}