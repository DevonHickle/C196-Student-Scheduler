package com.example.studentscheduler.Views;

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

import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModels.CourseVM;
import com.example.studentscheduler.Views.AddEdit.AddEditCourses;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CourseDetailActivity extends AppCompatActivity {
    public static final int STATUS_IN_PROGRESS = 2;
    public static final int STATUS_COMPLETED = 1;
    public static final String EXTRA_COURSE_TERM_ID = "";
    public static final String EXTRA_COURSE_ID = "";
    public static final String EXTRA_COURSE_TITLE = "";
    public static final String EXTRA_COURSE_START_DATE = "";
    public static final String EXTRA_COURSE_END_DATE = "";
    public static final String EXTRA_COURSE_ALERT = "";
    public static final String EXTRA_COURSE_STATUS = "";
    public static final String EXTRA_COURSE_INSTRUCTOR_NAME = "";
    public static final String EXTRA_COURSE_INSTRUCTOR_EMAIL = "";
    public static final String EXTRA_COURSE_INSTRUCTOR_PHONE = "";

    public static final int EDIT_COURSE_REQUEST = 5;

    private AlarmManager alarmManager;
    PendingIntent startCoursePendingIntent;
    PendingIntent endCoursePendingIntent;

    private CourseVM courseVM;

    private int termID;
    private int courseID;
    private String courseTitle;
    private TextView title;
    private TextView startDate;
    private TextView endDate;
    private int status;
    private TextView courseStatus;
    private TextView instructorName;
    private TextView instructorPhone;
    private TextView instructorEmail;
    private boolean isAlarmEnabled;
    private ImageView alarmImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseVM = new ViewModelProvider(this).get(CourseVM.class);
        setContentView(R.layout.course_detail);

        title = findViewById(R.id.detailed_course_title);
        startDate = findViewById(R.id.detailed_course_start_date);
        endDate = findViewById(R.id.detailed_course_end_date);
        courseStatus = findViewById(R.id.detailed_course_status);
        alarmImage = findViewById(R.id.detailed_image_alarm);
        instructorName = findViewById(R.id.detailed_course_mentor_name);
        instructorEmail = findViewById(R.id.detailed_course_mentor_email_address);
        instructorPhone = findViewById(R.id.detailed_course_mentor_phone_number);

        Intent parentIntent = getIntent();
        termID = parentIntent.getIntExtra(EXTRA_COURSE_TERM_ID, -1);
        courseID = parentIntent.getIntExtra(EXTRA_COURSE_ID, -1);

        setTitle(parentIntent.getStringExtra(EXTRA_COURSE_TITLE));

        this.courseTitle = parentIntent.getStringExtra(EXTRA_COURSE_TITLE);

        title.setText(courseTitle);
        startDate.setText(parentIntent.getStringExtra(EXTRA_COURSE_START_DATE));
        endDate.setText(parentIntent.getStringExtra(EXTRA_COURSE_END_DATE));
        status = parentIntent.getIntExtra(EXTRA_COURSE_STATUS, -1);
        courseStatus.setText(CourseDetailActivity.getStatus(status));
        instructorName.setText(parentIntent.getStringExtra(EXTRA_COURSE_INSTRUCTOR_NAME));
        instructorPhone.setText(parentIntent.getStringExtra(EXTRA_COURSE_INSTRUCTOR_PHONE));
        instructorEmail.setText(parentIntent.getStringExtra(EXTRA_COURSE_INSTRUCTOR_EMAIL));

        isAlarmEnabled = parentIntent.getBooleanExtra(EXTRA_COURSE_ALERT, false);
        if(isAlarmEnabled == true) {
            alarmImage.setVisibility(View.VISIBLE);
        } alarmImage.setVisibility(View.INVISIBLE);

        FloatingActionButton editCourseButton = findViewById(R.id.btn_edit_course);
        editCourseButton.setOnClickListener(view -> {
            Intent editCourse = new Intent(this, AddEditCourses.class);
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_ID, courseID);
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_TITLE, title.getText().toString());
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_START_DATE, startDate.getText().toString());
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_END_DATE, endDate.getText().toString());
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_STATUS, status);
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_NAME, instructorName.getText().toString());
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_PHONE, instructorPhone.getText().toString());
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_EMAIL, instructorEmail.getText().toString());
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_ALERT, isAlarmEnabled);
            startActivity(editCourse);
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == EDIT_COURSE_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            int courseID = data.getIntExtra(AddEditCourses.EXTRA_COURSE_ID, -1);
            String title = data.getStringExtra(AddEditCourses.EXTRA_COURSE_TITLE);
            String startDate = data.getStringExtra(AddEditCourses.EXTRA_COURSE_START_DATE);
            String endDate = data.getStringExtra(AddEditCourses.EXTRA_COURSE_END_DATE);
            boolean isAlarmEnabled = data.getBooleanExtra(AddEditCourses.EXTRA_COURSE_ALERT, false);
            int status = data.getIntExtra(AddEditCourses.EXTRA_COURSE_STATUS, -1);
            String instructorName = data.getStringExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_NAME);
            String instructorPhone = data.getStringExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_PHONE);
            String instructorEmail = data.getStringExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_EMAIL);

            if(courseID == -1) {
                Toast.makeText(this, "Course Not Saved", Toast.LENGTH_LONG).show();
            }
            if(isAlarmEnabled == true) {
                alarmImage.setVisibility(View.VISIBLE);
            } alarmImage.setVisibility(View.INVISIBLE);

            // TODO: add set texts

            //TODO: add alarm notifications
        }
    }

    public static String getStatus(int status) {
        String result;
        switch (status) {
            case STATUS_IN_PROGRESS:
                result = "In Progress";
                break;
            case STATUS_COMPLETED:
                result = "Completed";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }
}
