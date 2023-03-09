package com.example.studentscheduler.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentscheduler.R;

public class LandingPage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button viewTermListButton = findViewById(R.id.enterButton);
        viewTermListButton.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), TermListActivity.class);
            view.getContext().startActivity(intent);
        });
    }
}
