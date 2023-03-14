package com.example.studentscheduler.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Activities.AddEdit.AddEditAssessments;
import com.example.studentscheduler.Adapters.AssessmentsAdapter;
import com.example.studentscheduler.Models.AssessmentModel;
import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModels.AssessmentVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AssessmentListActivity extends AppCompatActivity {
    public static final int ADD_ASSESSMENT_REQUEST = 1;
    public static final String EXTRA_COURSE_ID = "com.example.studentscheduler.Activities.COURSE_ID";
    public static final String EXTRA_COURSE_TITLE = "com.example.studentscheduler.Activities.COURSE_TITLE";

    private int courseID;
    private String courseTitle;
    private AssessmentVM assessmentVM;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_list);

        FloatingActionButton addAssessmentButton = findViewById(R.id.add_assessment_button);
        addAssessmentButton.setOnClickListener(view -> {
            Intent addAssessmentIntent = new Intent(this, AddEditAssessments.class);
            startActivity(addAssessmentIntent);
        });

        Intent loadAssessments = getIntent();
        courseID = loadAssessments.getIntExtra(EXTRA_COURSE_ID, -1);
        courseTitle = loadAssessments.getStringExtra(EXTRA_COURSE_TITLE);

        setTitle(courseTitle + " Assessments");

        //Recycler View
        RecyclerView recyclerView = findViewById(R.id.assessmentListRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.hasFixedSize();
        AssessmentsAdapter adapter = new AssessmentsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        assessmentVM = new ViewModelProvider(this).get(AssessmentVM.class);
        assessmentVM.getCourseAssignments(courseID).observe(this, adapter::setAssessments);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AssessmentModel deletedAssessment = adapter.getAssessments(viewHolder.getAdapterPosition());
                assessmentVM.delete(deletedAssessment);
                Toast.makeText(AssessmentListActivity.this, "Assessment Successfully Removed", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(assessmentModel -> {
            Intent loadAssessment = new Intent(this, AssessmentDetailActivity.class);
            loadAssessment.putExtra(AssessmentDetailActivity.EXTRA_ASSESSMENT_COURSE_ID, courseID);
            loadAssessment.putExtra(AssessmentDetailActivity.EXTRA_ASSESSMENT_COURSE_TITLE, courseTitle);
            loadAssessment.putExtra(AssessmentDetailActivity.EXTRA_ASSESSMENT_ID, assessmentModel.getId());
            loadAssessment.putExtra(AssessmentDetailActivity.EXTRA_ASSESSMENT_TITLE, assessmentModel.getName());
            loadAssessment.putExtra(AssessmentDetailActivity.EXTRA_ASSESSMENT_TYPE, assessmentModel.getType());
            loadAssessment.putExtra(AssessmentDetailActivity.EXTRA_ASSESSMENT_DUE_DATE, assessmentModel.getGoalDate());
            loadAssessment.putExtra(AssessmentDetailActivity.EXTRA_ASSESSMENT_ALARM, assessmentModel.getAlert());
            startActivity(loadAssessment);
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_ASSESSMENT_REQUEST && resultCode == RESULT_OK) {
            int courseID = getIntent().getIntExtra(EXTRA_COURSE_ID, -1);
            assert data != null;
            String assessmentName = data.getStringExtra(AddEditAssessments.EXTRA_COURSE_ASSESSMENT_TITLE);
            int assessmentType = data.getIntExtra(AddEditAssessments.EXTRA_ASSESSMENT_TYPE, -1);
            String assessmentGoalDate = data.getStringExtra(AddEditAssessments.EXTRA_COURSE_ASSESSMENT_GOAL_DATE);
            boolean assessmentAlertEnabled = data.getBooleanExtra(AddEditAssessments.EXTRA_COURSE_ASSESSMENT_ALERT, false);

            AssessmentModel assessmentModel = new AssessmentModel(courseID, assessmentName, assessmentType, assessmentGoalDate, assessmentAlertEnabled);

            assessmentVM.insert(assessmentModel);
        }
    }
}
