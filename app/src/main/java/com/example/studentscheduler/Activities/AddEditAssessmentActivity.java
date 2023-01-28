package com.example.studentscheduler.Activities;

import static android.app.ProgressDialog.show;

import android.app.DatePickerDialog;
import android.content.Intent;
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
        editTitle = findViewById(R.id.edit_assessment_name);
        editRadioType = findViewById(R.id.edit_assessment_radio_type);
        editGoalDate = findViewById(R.id.edit_assessment_goal_date);
        checkAlarmEnabled = findViewById(R.id.edit_assessment_alarm);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        //Get and Set Calendar dates
        this.calGoalDate = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener setGoalDate = (view, year, month, dayOfMonth) -> {
            calGoalDate.set(Calendar.YEAR, year);
            calGoalDate.set(Calendar.MONTH, month);
            calGoalDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        editGoalDate.setOnClickListener(view -> new DatePickerDialog(this,
                setGoalDate,
                calGoalDate.get(Calendar.YEAR);
                calGoalDate.get(Calendar.MONTH);
                calGoalDate.get(Calendar.DAY_OF_MONTH)).show());

        Intent parentIntent = getIntent();
        if(parentIntent.hasExtra(EXTRA_ASSESSMENT_ID)) {
            setTitle("Edit Assessment");
            editTitle.setText(parentIntent.getStringExtra(EXTRA_COURSE_ASSESSMENT_TITLE));
            editGoalDate.setText(parentIntent.getStringExtra(EXTRA_COURSE_ASSESSMENT_GOAL_DATE));
            editRadioType.check(getTypeBtnID(parentIntent.getIntExtra(EXTRA_ASSESSMENT_TYPE, -1)));
            if(parentIntent.getBooleanExtra(EXTRA_COURSE_ASSESSMENT_ALERT, false));
                checkAlarmEnabled.performClick();
        } else {
            setTitle("Add Assessment");
        }

        // Save Assessment


        // Create and Item Select Options Menu

        }
    //Update Label
    private void updateLabel() {
        String dateFormat = DATE_FORMAT;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        editGoalDate.setText(simpleDateFormat.format(calGoalDate.getTime()));
    }

    private int getTypeBtnID(int intExtra) {
        return intExtra;
    }
}
