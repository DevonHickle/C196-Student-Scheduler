package com.example.studentscheduler.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModel.TermVM;

public class TermListActivity extends AppCompatActivity {
    public static final int ADD_TERM_REQ = 1;

    private TermVM termVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_list);
    }
}
