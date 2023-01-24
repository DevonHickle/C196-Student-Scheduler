package com.example.studentscheduler.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentscheduler.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    Button enterButton = findViewById(R.id.enterButton);
    enterButton.setOnClickListener(view -> {
        setContentView(R.layout.term_list);
        });
    }

    public void enterApp(View view) {
        Intent intent = new Intent(this, TermListActivity.class);
        startActivity(intent);
    }
}