package com.example.studentscheduler.Activities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentscheduler.Activities.AddEdit.AddEditAssessments;
import com.example.studentscheduler.Alarms.AssessmentsAlarm;
import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModels.AssessmentVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

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
    private String assessmentTitle;
    private boolean isAlarmEnabled;

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
        assessmentID = parentIntent.getIntExtra(EXTRA_ASSESSMENT_ID, -1);
        courseTitle = parentIntent.getStringExtra(EXTRA_ASSESSMENT_COURSE_TITLE);
        assessmentTitle = parentIntent.getStringExtra(EXTRA_ASSESSMENT_TITLE);
        isAlarmEnabled = parentIntent.getBooleanExtra(EXTRA_ASSESSMENT_ALARM, false);
        if(isAlarmEnabled == true) {
            ivAlarm.setVisibility(View.VISIBLE);
        } ivAlarm.setVisibility(View.INVISIBLE);

        setTitle(courseTitle + " | " + assessmentTitle);

        tvTitle.setText(assessmentTitle);
        tvDueDate.setText(parentIntent.getStringExtra(EXTRA_ASSESSMENT_DUE_DATE));
        int type = parentIntent.getIntExtra(EXTRA_ASSESSMENT_TYPE, -1);
        tvType.setText(getAssessmentType(type));

        FloatingActionButton editAssessmentButton = findViewById(R.id.btn_edit_assessment);
        editAssessmentButton.setOnClickListener(view -> {
            Intent editAssessment = new Intent(this, AddEditAssessments.class);
            editAssessment.putExtra(AddEditAssessments.EXTRA_ASSESSMENT_ID, assessmentID);
            editAssessment.putExtra(AddEditAssessments.EXTRA_COURSE_ID, courseID);
            editAssessment.putExtra(AddEditAssessments.EXTRA_COURSE_ASSESSMENT_TITLE, courseTitle);
            editAssessment.putExtra(AddEditAssessments.EXTRA_COURSE_ASSESSMENT_GOAL_DATE, parentIntent.getStringExtra(EXTRA_ASSESSMENT_DUE_DATE));
            editAssessment.putExtra(AddEditAssessments.EXTRA_COURSE_ASSESSMENT_ALERT, parentIntent.getBooleanExtra(EXTRA_ASSESSMENT_ALARM, false));
            editAssessment.putExtra(AddEditAssessments.EXTRA_ASSESSMENT_TYPE, type);
            startActivityForResult(editAssessment, AddEditAssessments.EDIT_ASSESSMENT_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == AddEditAssessments.EDIT_ASSESSMENT_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            int assessmentID = data.getIntExtra(AddEditAssessments.EXTRA_ASSESSMENT_ID, -1);
            String assessmentName = data.getStringExtra(AddEditAssessments.EXTRA_COURSE_ASSESSMENT_TITLE);
            int assessmentType = data.getIntExtra(AddEditAssessments.EXTRA_ASSESSMENT_TYPE, -1);
            String assessmentGoalDate = data.getStringExtra(AddEditAssessments.EXTRA_COURSE_ASSESSMENT_GOAL_DATE);
            boolean assessmentAlert = data.getBooleanExtra(AddEditAssessments.EXTRA_COURSE_ASSESSMENT_ALERT, false);

            if(assessmentID == -1 || assessmentType == -1) {
                Toast.makeText(this, "Unable to save assessment", Toast.LENGTH_SHORT).show();
            }

            tvTitle.setText(assessmentName);
            tvDueDate.setText(assessmentGoalDate);
            tvType.setText(getAssessmentType(assessmentType));

            if(assessmentAlert == true) {
                ivAlarm.setVisibility(View.VISIBLE);
            } ivAlarm.setVisibility(View.INVISIBLE);

            if(assessmentAlert == true) {
                alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
                Intent alarmIntent = new Intent(this, AssessmentsAlarm.class);
                alarmIntent.putExtra(AssessmentsAlarm.ALARM_NOTIFICATION_TITLE, assessmentName);
                alarmIntent.putExtra(AssessmentsAlarm.ALARM_NOTIFICATION_TYPE, getAssessmentType(assessmentType));
                alarmIntent.putExtra(AssessmentsAlarm.ALARM_NOTIFICATION_TEXT, assessmentGoalDate);

                @SuppressLint("UnspecifiedImmutableFlag") PendingIntent assessmentPendingAlert = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

                Calendar assessmentCalendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat(AddEditAssessments.DATE_FORMAT, Locale.ENGLISH);

                try {
                    assessmentCalendar.setTime(Objects.requireNonNull(dateFormat.parse(assessmentGoalDate)));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                assessmentCalendar.set(Calendar.HOUR, 0);

                alarmManager.set(AlarmManager.RTC, assessmentCalendar.getTimeInMillis(), assessmentPendingAlert);
            } else if(alarmManager != null) {
                alarmManager.cancel(alarmIntent);
                alarmIntent.cancel();
            }
        }
    }

    private String getAssessmentType(int type) {
        return switch (type) {
            case TYPE_PA -> "PA";
            case TYPE_OA -> "OA";
            default -> "";
        };
    }
}
