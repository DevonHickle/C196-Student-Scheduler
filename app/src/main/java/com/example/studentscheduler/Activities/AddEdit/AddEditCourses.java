package com.example.studentscheduler.Activities.AddEdit;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentscheduler.R;

import java.util.Calendar;

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
    }
}
