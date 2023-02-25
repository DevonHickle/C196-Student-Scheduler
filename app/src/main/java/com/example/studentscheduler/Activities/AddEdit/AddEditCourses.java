package com.example.studentscheduler.Activities.AddEdit;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentscheduler.R;

import java.util.Calendar;
import java.util.Objects;

public class AddEditCourses extends AppCompatActivity {
    // TODO: Add Paths
    public static final int REQUEST_ADD_COURSE = 1;
    public static final String EXTRA_COURSE_TITLE = "";
    public static final String EXTRA_COURSE_START_DATE = "";
    public static final String EXTRA_COURSE_END_DATE = "";
    public static final String EXTRA_COURSE_ALERT = "";
    public static final String EXTRA_COURSE_STATUS = "";
    public static final String EXTRA_COURSE_INSTRUCTOR_NAME = "";
    public static final String EXTRA_COURSE_INSTRUCTOR_EMAIL = "";
    public static final String EXTRA_COURSE_INSTRUCTOR_PHONE = "";
    public static final String EXTRA_COURSE_ID = "";
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final int REQUEST_EDIT_COURSE = 2;

    private Calendar startDate;
    private Calendar endDate;

    private EditText editTitle;
    private EditText editStartDate;
    private EditText editEndDate;
    private CheckBox courseAlarmCheck;
    private RadioGroup editCourseStatus;
    private EditText editInstructorName;
    private EditText editInstructorPhone;
    private EditText editInstructorEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_course);

        editTitle = findViewById(R.id.edit_course_title);
        editStartDate = findViewById(R.id.edit_course_start_date);
        editEndDate = findViewById(R.id.edit_course_end_date);
        courseAlarmCheck = findViewById(R.id.edit_course_alarm);
        editCourseStatus = findViewById(R.id.edit_course_radio_status);
        editInstructorName = findViewById(R.id.edit_course_mentor_name);
        editInstructorEmail = findViewById(R.id.edit_course_mentor_email_address);
        editInstructorPhone = findViewById(R.id.edit_course_mentor_phone_number);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_add_white_24dp);

        this.startDate = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener setStartDate = (view, year, month, dayOfMonth) -> {
            startDate.set(Calendar.YEAR, year);
            startDate.set(Calendar.MONTH, month);
            startDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(editStartDate, startDate);
        };

        editStartDate.setOnClickListener(view -> new DatePickerDialog(
                this,
                setStartDate,
                startDate.get(Calendar.YEAR),
                startDate.get(Calendar.MONTH),
                startDate.get(Calendar.DAY_OF_MONTH)).show());

        endDate = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener setEndDate = (view, year, month, dayOfMonth) -> {
            endDate.set(Calendar.YEAR, year);
            endDate.set(Calendar.MONTH, month);
            endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(editEndDate, endDate);
        };
        this.endDate = Calendar.getInstance();
        editEndDate.setOnClickListener(view -> new DatePickerDialog(
                this,
                setEndDate,
                endDate.get(Calendar.YEAR),
                endDate.get(Calendar.MONTH),
                endDate.get(Calendar.DAY_OF_MONTH)).show());

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_COURSE_ID)) {
            setTitle("Edit Course");
            editTitle.setText(intent.getStringExtra(EXTRA_COURSE_TITLE));
            editStartDate.setText(intent.getStringExtra(EXTRA_COURSE_START_DATE);;
            editEndDate.setText(intent.getStringExtra(EXTRA_COURSE_END_DATE));

            if(intent.getBooleanExtra(EXTRA_COURSE_ALERT, false)){
                courseAlarmCheck.performClick();
            }

            editCourseStatus.check(getBtnId(intent.getIntExtra(EXTRA_COURSE_STATUS, -1)));
            editInstructorName.setText(intent.getStringExtra(EXTRA_COURSE_INSTRUCTOR_NAME));
            editInstructorPhone.setText(intent.getStringExtra(EXTRA_COURSE_INSTRUCTOR_PHONE));
            editInstructorEmail.setText(intent.getStringExtra(EXTRA_COURSE_INSTRUCTOR_EMAIL));
        } setTitle("Add Course");

    private void updateLabel(EditText editText, Calendar calendar) {
    }
}

    private int getBtnId(int intExtra) {
    }
