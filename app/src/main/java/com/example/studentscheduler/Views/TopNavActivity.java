package com.example.studentscheduler.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentscheduler.R;

public class TopNavActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_nav);

        Button viewTermListButton = findViewById(R.id.btn_view_term_list);
        viewTermListButton.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), TermListActivity.class);
            view.getContext().startActivity(intent);
        });
    }
}
