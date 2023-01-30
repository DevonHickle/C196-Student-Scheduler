package com.example.studentscheduler.Activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentscheduler.R;

public class TopNavActivity extends AppCompatActivity {

    private Button viewTermListButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_nav);
    }
}
