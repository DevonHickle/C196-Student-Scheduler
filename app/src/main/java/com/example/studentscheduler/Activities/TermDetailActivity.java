package com.example.studentscheduler.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentscheduler.Activities.AddEdit.AddEditTerms;
import com.example.studentscheduler.Models.TermModel;
import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModel.TermVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        termVM = new ViewModelProvider(this).get(TermVM.class);
        setContentView(R.layout.term_detail);

        textViewTitle = findViewById(R.id.textview_detailed_term_title);
        textViewStartDate = findViewById(R.id.textview_detailed_term_start_date);
        textViewEndDate = findViewById(R.id.textview_detailed_term_end_date);
        Button courseListButton = findViewById(R.id.edit_term_save_button);

        Intent intent = getIntent();
        termID = intent.getIntExtra(EXTRA_ID, -1);
        setTitle(intent.getStringExtra(EXTRA_TITLE));
        textViewTitle.setText(intent.getStringExtra(EXTRA_TITLE));
        textViewStartDate.setText(intent.getStringExtra(EXTRA_START_DATE));
        textViewEndDate.setText(intent.getStringExtra(EXTRA_END_DATE));

        courseListButton.setOnClickListener(view -> {
            Intent loadCourseList = new Intent(this, AddEditTerms.class);
            loadCourseList.putExtra(CourseListActivity.EXTRA_COURSE_TERM_ID, termID);
            loadCourseList.putExtra(CourseListActivity.EXTRA_COURSE_TERM_TITLE, intent.getStringExtra(EXTRA_TITLE));
            startActivity(loadCourseList);
        });

        FloatingActionButton addTermButton = findViewById(R.id.btn_add_term);
        addTermButton.setOnClickListener(view -> {
            Intent addTermIntent = new Intent(this, AddEditTerms.class);
            addTermIntent.putExtra(AddEditTerms.EXTRA_TERM_ID, intent.getIntExtra(EXTRA_ID, -1));
            addTermIntent.putExtra(AddEditTerms.EXTRA_TERM_TITLE, intent.getStringExtra(EXTRA_TITLE));
            addTermIntent.putExtra(AddEditTerms.EXTRA_TERM_START_DATE, intent.getStringExtra(EXTRA_START_DATE));
            addTermIntent.putExtra(AddEditTerms.EXTRA_TERM_END_DATE, intent.getStringExtra(EXTRA_END_DATE));
            startActivityForResult(addTermIntent, EDIT_TERM_REQUEST);
        });
    }

    protected void onActivityResult(int request, int result, @Nullable Intent data) {
        super.onActivityResult(request, result, data);
        if(request == EDIT_TERM_REQUEST && result == RESULT_OK) {
            assert data != null;
            String title = data.getStringExtra(AddEditTerms.EXTRA_TERM_TITLE);
            String startDate = data.getStringExtra(AddEditTerms.EXTRA_TERM_START_DATE);
            String endDate = data.getStringExtra(AddEditTerms.EXTRA_TERM_END_DATE);
            int id = data.getIntExtra(AddEditTerms.EXTRA_TERM_ID, -1);
            if(id == -1) {
                Toast.makeText(this, "Error! Unable to save term", Toast.LENGTH_SHORT).show();
            }

            textViewTitle.setText(title);
            textViewStartDate.setText(startDate);
            textViewEndDate.setText(endDate);

            TermModel termModel = new TermModel(title, startDate, endDate);
            termModel.setId(id);
            termVM.update(termModel);

            Toast.makeText(this, "Term updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Unable to save term", Toast.LENGTH_SHORT).show();
        }
    }
}
