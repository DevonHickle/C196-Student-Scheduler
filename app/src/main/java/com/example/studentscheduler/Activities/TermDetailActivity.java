package com.example.studentscheduler.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModel.TermVM;

public class TermDetailActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.studentscheduler.Activities.ID";
    public static final String EXTRA_TITLE = "com.example.studentscheduler.Activities.TERM_TITLE";
    public static final String EXTRA_START_DATE = "com.example.studentscheduler.Activities.TERM_START_DATE";
    public static final String EXTRA_END_DATE = "com.example.studentscheduler.Activities.TERM_END_DATE";

    public static final int EDIT_TERM_REQUEST = 2;

    private TermVM termVM;

    private int termID;
    private TextView textViewTitle;
    private TextView textViewStartDate;
    private TextView textViewEndDate;
    private Button courseListButton;

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        termVM = new ViewModelProvider(this).get(TermVM.class);
        setContentView(R.layout.term_detail);

        // Find view by IDs here

        // Add click listeners for buttons and other functionality
    }
}
