package com.example.studentscheduler.Activities.AddEdit;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentscheduler.Activities.CourseDetailActivity;
import com.example.studentscheduler.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class AddEditCourses extends AppCompatActivity {
    public static final int REQUEST_ADD_COURSE = 1;
    public static final String EXTRA_COURSE_TITLE = "com.example.studentscheduler.Activities.COURSE_TITLE";
    public static final String EXTRA_COURSE_START_DATE = "com.example.studentscheduler.Activities.COURSE_START_DATE";
    public static final String EXTRA_COURSE_END_DATE = "com.example.studentscheduler.Activities.COURSE_END_DATE";
    public static final String EXTRA_COURSE_ALERT = "com.example.studentscheduler.Activities.COURSE_ALERT";
    public static final String EXTRA_COURSE_STATUS = "com.example.studentscheduler.Activities.COURSE_STATUS";
    public static final String EXTRA_COURSE_INSTRUCTOR_NAME = "com.example.studentscheduler.Activities.COURSE_INSTRUCTOR_NAME";
    public static final String EXTRA_COURSE_INSTRUCTOR_EMAIL = "com.example.studentscheduler.Activities.COURSE_INSTRUCTOR_EMAIL";
    public static final String EXTRA_COURSE_INSTRUCTOR_PHONE = "com.example.studentscheduler.Activities.COURSE_INSTRUCTOR_PHONE";
    public static final String EXTRA_COURSE_ID = "com.example.studentscheduler.Activities.COURSE_ID";
    public static final String DATE_FORMAT = "MM/dd/yyyy";

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
        if (intent.hasExtra(EXTRA_COURSE_ID)) {
            setTitle("Edit Course");
            editTitle.setText(intent.getStringExtra(EXTRA_COURSE_TITLE));
            editStartDate.setText(intent.getStringExtra(EXTRA_COURSE_START_DATE));
            editEndDate.setText(intent.getStringExtra(EXTRA_COURSE_END_DATE));

            if (intent.getBooleanExtra(EXTRA_COURSE_ALERT, false)) {
                courseAlarmCheck.performClick();
            }

            editCourseStatus.check(getBtnId(intent.getIntExtra(EXTRA_COURSE_STATUS, -1)));
            editInstructorName.setText(intent.getStringExtra(EXTRA_COURSE_INSTRUCTOR_NAME));
            editInstructorPhone.setText(intent.getStringExtra(EXTRA_COURSE_INSTRUCTOR_PHONE));
            editInstructorEmail.setText(intent.getStringExtra(EXTRA_COURSE_INSTRUCTOR_EMAIL));
        } setTitle("Add Course");
    }
    private void saveCourse() {
        String courseTitle = editTitle.getText().toString();
        String courseEndDate = editEndDate.getText().toString();
        String courseStartDate = editStartDate.getText().toString();
        boolean isAlarmEnabled = courseAlarmCheck.isChecked();
        int courseStatus = getRadioStatus(editCourseStatus.getCheckedRadioButtonId());
        String instructorName = editInstructorName.getText().toString();
        String instructorEmail = editInstructorEmail.getText().toString();
        String instructorPhone = editInstructorPhone.getText().toString();

        if(courseTitle.trim().isEmpty() || courseStartDate.trim().isEmpty() || courseEndDate.trim().isEmpty()
                || courseStatus == -1 || instructorEmail.trim().isEmpty() || instructorName.trim().isEmpty() || instructorPhone.trim().isEmpty()) {
            Toast.makeText(this, "Unable to save course, please check empty fields", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_COURSE_TITLE, courseTitle);
        intent.putExtra(EXTRA_COURSE_START_DATE, courseStartDate);
        intent.putExtra(EXTRA_COURSE_END_DATE, courseEndDate);
        intent.putExtra(EXTRA_COURSE_INSTRUCTOR_NAME, instructorName);
        intent.putExtra(EXTRA_COURSE_INSTRUCTOR_EMAIL, instructorEmail);
        intent.putExtra(EXTRA_COURSE_INSTRUCTOR_PHONE, instructorPhone);
        intent.putExtra(EXTRA_COURSE_ALERT, isAlarmEnabled);
        intent.putExtra(EXTRA_COURSE_STATUS, courseStatus);

        int courseID = getIntent().getIntExtra(EXTRA_COURSE_ID, -1);
        if(courseID != -1) {
            intent.putExtra(EXTRA_COURSE_ID, courseID);
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_edit_save, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_add_edit_save) {
            saveCourse();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private int getRadioStatus(int buttonID) {
        return switch (buttonID) {
            case R.id.radio_course_status_in_progress -> CourseDetailActivity.STATUS_IN_PROGRESS;
            case R.id.radio_course_status_completed -> CourseDetailActivity.STATUS_COMPLETED;
            default -> -1;
        };
    }

    private int getBtnId(int id) {
        return switch (id) {
            case CourseDetailActivity.STATUS_IN_PROGRESS -> R.id.radio_course_status_in_progress;
            case CourseDetailActivity.STATUS_COMPLETED -> R.id.radio_course_status_completed;
            default -> -1;
        };
    }

    private void updateLabel(EditText editText, Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        editText.setText(simpleDateFormat.format(calendar.getTime()));
    }
}
