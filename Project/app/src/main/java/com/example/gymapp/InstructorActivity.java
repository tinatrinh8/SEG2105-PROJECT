package com.example.gymapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InstructorActivity extends AppCompatActivity {


    TextView instructor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
        instructor = (TextView) findViewById(R.id.instructorTitle);
    }

}
