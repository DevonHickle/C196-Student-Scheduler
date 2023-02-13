package com.example.studentscheduler.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModels.AssessmentVM;

public class AssessmentDetailActivity extends AppCompatActivity {
    public static final int TYPE_PA = 0;
    public static final int TYPE_OA = 1;
    public static final String EXTRA_ASSESSMENT_COURSE_ID = "";
    public static final String EXTRA_ASSESSMENT_COURSE_TITLE = "";
    public static final String EXTRA_ASSESSMENT_ID = "";
    public static final String EXTRA_ASSESSMENT_TITLE = "";
    public static final String EXTRA_ASSESSMENT_TYPE = "";
    public static final String EXTRA_ASSESSMENT_DUE_DATE = "";
    public static final String EXTRA_ASSESSMENT_ALARM = "";

    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    private AssessmentVM assessmentVM;

    private int courseID;
    private String courseTitle;
    private int assessmentID;
    private TextView tvTitle;
    private TextView tvDueDate;
    private TextView tvType;
    private ImageView ivAlarm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assessmentVM = new ViewModelProvider(this).get(AssessmentVM.class);
        setContentView(R.layout.assessment_detail);

        tvTitle = findViewById(R.id.detailed_assessment_title);
        tvDueDate = findViewById(R.id.detailed_assessment_due_date);
        tvType = findViewById(R.id.detailed_assessment_type);
        ivAlarm = findViewById(R.id.detailed_assessment_image_alarm);

        Intent parentIntent = getIntent();
        courseID = parentIntent.getIntExtra(EXTRA_ASSESSMENT_COURSE_ID, -1);

    }
}
