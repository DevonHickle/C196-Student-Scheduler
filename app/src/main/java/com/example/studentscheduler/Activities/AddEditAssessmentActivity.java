package com.example.studentscheduler.Activities;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentscheduler.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEditAssessmentActivity extends AppCompatActivity {
    public static final String EXTRA_ASSESSMENT_ID = "";
    public static final String EXTRA_COURSE_ID = "";
    public static final String EXTRA_COURSE_ASSESSMENT_TITLE = "";
    public static final String EXTRA_COURSE_ASSESSMENT_GOAL_DATE = "";
    public static final String EXTRA_COURSE_ASSESSMENT_ALERT = "";
    public static final String EXTRA_ASSESSMENT_TYPE = "";

    public static final int EDIT_ASSESSMENT_REQUEST = 2;

    public static final String DATE_FORMAT = "MM/dd/yyyy";

    private Calendar calGoalDate;

    private int assessmentID;
    private EditText editTitle;
    private RadioGroup editRadioType;
    private EditText editGoalDate;
    private CheckBox checkAlarmEnabled;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_assessment);

        // Find IDs in Layout
        editTitle = findViewById();

        //Get and Set Calendar dates


        // Save Assessment


        // Create and Item Select Options Menu


        //Update Label
        private void updateLabel() {
            String dateFormat = DATE_FORMAT;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
            editGoalDate.setText(simpleDateFormat.format(calGoalDate.getTime()));
        }
    }
}
