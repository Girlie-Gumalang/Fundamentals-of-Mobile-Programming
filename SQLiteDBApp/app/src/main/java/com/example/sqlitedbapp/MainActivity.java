package com.example.sqlitedbapp;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etStdntID, etStdntName, etStdntProg;
    Button btAdd, btDelete, btSearch, btView;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etStdntID = findViewById(R.id.et_id);
        etStdntName = findViewById(R.id.et_name);
        etStdntProg = findViewById(R.id.et_program);

        btAdd = findViewById(R.id.btn_add);
        btDelete = findViewById(R.id.btn_delete);
        btSearch = findViewById(R.id.btn_search);
        btView = findViewById(R.id.btn_view);

        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(stdnt_id VARCHAR, stdnt_name VARCHAR, stdnt_prog VARCHAR);");

        btAdd.setOnClickListener(v -> {
            String id = etStdntID.getText().toString();
            String name = etStdntName.getText().toString();
            String prog = etStdntProg.getText().toString();

            if (id.isEmpty() || name.isEmpty()|| prog.isEmpty()) {
                showMessage("Error", "Please enter all values");
                return;
            }

            db.execSQL("INSERT INTO student VALUES('" + id + "','" + name + "','" + prog + "');");
            showMessage("Success", "Record added");
            clearText();
        });

        btDelete.setOnClickListener(v -> {
            String id = etStdntID.getText().toString();

            if (id.isEmpty()) {
                showMessage("Error", "Please enter Student ID");
                return;
            }

            Cursor c = db.rawQuery("SELECT * FROM student WHERE stdnt_id='" + id + "'", null);
            if (c.moveToFirst()) {
                db.execSQL("DELETE FROM student WHERE stdnt_id='" + id + "'");
                showMessage("Success", "Record Deleted");
            } else {
                showMessage("Error", "Invalid Student ID");
            }
            c.close();
            clearText();
        });

        btSearch.setOnClickListener(v -> {
            String id = etStdntID.getText().toString();

            if (id.isEmpty()) {
                showMessage("Error", "Please enter Student ID");
                return;
            }

            Cursor c = db.rawQuery("SELECT * FROM student WHERE stdnt_id='" + id + "'", null);
            if (c.moveToFirst()) {
                etStdntName.setText(c.getString(1));
                etStdntProg.setText(c.getString(2));
            } else {
                showMessage("Error", "No record found");
                clearText();
            }
            c.close();
        });

        btView.setOnClickListener(v -> {
            Cursor c = db.rawQuery("SELECT * FROM student", null);
            if (c.getCount() == 0) {
                showMessage("Error", "No records found");
                c.close();
                return;
            }

            StringBuilder buffer = new StringBuilder();
            while (c.moveToNext()) {
                buffer.append("ID: ").append(c.getString(0)).append("\n");
                buffer.append("Name: ").append(c.getString(1)).append("\n");
                buffer.append("Program: ").append(c.getString(2)).append("\n\n");
            }
            c.close();
            showMessage("Student Details", buffer.toString());
        });
    }

    private void clearText() {
        etStdntID.setText("");
        etStdntName.setText("");
        etStdntProg.setText("");
        etStdntID.requestFocus();
    }

    private void showMessage(String title, String message) {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}