package com.example.gymapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MemberActivity extends AppCompatActivity {
    TextView memberTitle;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        memberTitle = (TextView) findViewById(R.id.memberTitle);
    }

}
