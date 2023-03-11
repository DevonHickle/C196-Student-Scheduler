package com.example.studentscheduler.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentscheduler.Activities.AddEdit.AddEditCourses;
import com.example.studentscheduler.Alarms.CoursesAlarm;
import com.example.studentscheduler.Models.CourseModel;
import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModels.CourseVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class CourseDetailActivity extends AppCompatActivity {
    public static final int STATUS_IN_PROGRESS = 2;
    public static final int STATUS_COMPLETED = 1;

    // TODO: Set Paths

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
    private static final int ALARM_COURSE_START = 50;
    private static final int ALARM_COURSE_END = 100;

    private AlarmManager alarmManager;
    PendingIntent startCoursePendingIntent;
    PendingIntent endCoursePendingIntent;
    private CourseVM courseVM;
    private int termID;
    private int courseID;
    private int status;
    private TextView tvTitle;
    private TextView tvStartDate;
    private TextView tvEndDate;
    private TextView tvCourseStatus;
    private TextView tvInstructorName;
    private TextView tvInstructorPhone;
    private TextView tvInstructorEmail;
    private ImageView ivAlarmImage;
    private boolean isAlarmEnabled;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseVM = new ViewModelProvider(this).get(CourseVM.class);
        setContentView(R.layout.course_detail);

        tvTitle = findViewById(R.id.detailed_course_title);
        tvStartDate = findViewById(R.id.detailed_course_start_date);
        tvEndDate = findViewById(R.id.detailed_course_end_date);
        tvCourseStatus = findViewById(R.id.detailed_course_status);
        ivAlarmImage = findViewById(R.id.detailed_image_alarm);
        tvInstructorName = findViewById(R.id.detailed_course_mentor_name);
        tvInstructorEmail = findViewById(R.id.detailed_course_mentor_email_address);
        tvInstructorPhone = findViewById(R.id.detailed_course_mentor_phone_number);

        Intent parentIntent = getIntent();
        termID = parentIntent.getIntExtra(EXTRA_COURSE_TERM_ID, -1);
        courseID = parentIntent.getIntExtra(EXTRA_COURSE_ID, -1);

        setTitle(parentIntent.getStringExtra(EXTRA_COURSE_TITLE));

        String courseTitle = parentIntent.getStringExtra(EXTRA_COURSE_TITLE);

        tvTitle.setText(courseTitle);
        tvStartDate.setText(parentIntent.getStringExtra(EXTRA_COURSE_START_DATE));
        tvEndDate.setText(parentIntent.getStringExtra(EXTRA_COURSE_END_DATE));
        status = parentIntent.getIntExtra(EXTRA_COURSE_STATUS, -1);
        tvCourseStatus.setText(CourseDetailActivity.getStatus(status));
        tvInstructorName.setText(parentIntent.getStringExtra(EXTRA_COURSE_INSTRUCTOR_NAME));
        tvInstructorPhone.setText(parentIntent.getStringExtra(EXTRA_COURSE_INSTRUCTOR_PHONE));
        tvInstructorEmail.setText(parentIntent.getStringExtra(EXTRA_COURSE_INSTRUCTOR_EMAIL));

        isAlarmEnabled = parentIntent.getBooleanExtra(EXTRA_COURSE_ALERT, false);
        if(isAlarmEnabled) {
            ivAlarmImage.setVisibility(View.VISIBLE);
        } ivAlarmImage.setVisibility(View.INVISIBLE);

        FloatingActionButton editCourseButton = findViewById(R.id.btn_edit_course);
        editCourseButton.setOnClickListener(view -> {
            Intent editCourse = new Intent(this, AddEditCourses.class);
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_ID, courseID);
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_TITLE, tvTitle.getText().toString());
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_START_DATE, tvStartDate.getText().toString());
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_END_DATE, tvEndDate.getText().toString());
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_STATUS, status);
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_NAME, tvInstructorName.getText().toString());
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_PHONE, tvInstructorPhone.getText().toString());
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_EMAIL, tvInstructorEmail.getText().toString());
            editCourse.putExtra(AddEditCourses.EXTRA_COURSE_ALERT, isAlarmEnabled);
            startActivity(editCourse);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDIT_COURSE_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            int courseID = data.getIntExtra(AddEditCourses.EXTRA_COURSE_ID, -1);
            String title = data.getStringExtra(AddEditCourses.EXTRA_COURSE_TITLE);
            String startDate = data.getStringExtra(AddEditCourses.EXTRA_COURSE_START_DATE);
            String endDate = data.getStringExtra(AddEditCourses.EXTRA_COURSE_END_DATE);
            boolean isAlarmEnabled = data.getBooleanExtra(AddEditCourses.EXTRA_COURSE_ALERT, false);
            int courseStatus = data.getIntExtra(AddEditCourses.EXTRA_COURSE_STATUS, -1);
            String instructorName = data.getStringExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_NAME);
            String instructorPhone = data.getStringExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_PHONE);
            String instructorEmail = data.getStringExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_EMAIL);

            if(courseID == -1) {
                Toast.makeText(this, "Course Not Saved", Toast.LENGTH_LONG).show();
            }

            if(isAlarmEnabled) {
                ivAlarmImage.setVisibility(View.VISIBLE);
            } ivAlarmImage.setVisibility(View.INVISIBLE);

            tvTitle.setText(title);
            tvStartDate.setText(startDate);
            tvEndDate.setText(endDate);
            tvCourseStatus.setText(getStatus(courseStatus));
            tvInstructorName.setText(instructorName);
            tvInstructorEmail.setText(instructorEmail);
            tvInstructorPhone.setText(instructorPhone);

            if (isAlarmEnabled) {
                alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
                Intent startCourseAlarm = new Intent(this, CoursesAlarm.class);
                startCourseAlarm.putExtra(CoursesAlarm.ALARM_NOTIFICATION_TITLE, title + " Alert!");
                startCourseAlarm.putExtra(CoursesAlarm.ALARM_NOTIFICATION_TEXT, title + " will be starting soon (" + startDate + ")");

                Intent endCourseAlarm = new Intent(this, CoursesAlarm.class);
                endCourseAlarm.putExtra(CoursesAlarm.ALARM_NOTIFICATION_TITLE, title + " Alert!");
                endCourseAlarm.putExtra(CoursesAlarm.ALARM_NOTIFICATION_TEXT, title + " will be ending soon (" + endDate + ")");

                Calendar startCourseCalendarAlert = Calendar.getInstance();
                Calendar endCourseCalendarAlert = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat(AddEditCourses.DATE_FORMAT, Locale.ENGLISH);

                try {
                    startCourseCalendarAlert.setTime(Objects.requireNonNull(dateFormat.parse(startDate)));
                    startCourseCalendarAlert.set(Calendar.HOUR, 8);
                    endCourseCalendarAlert.setTime(Objects.requireNonNull(dateFormat.parse(endDate)));
                    endCourseCalendarAlert.set(Calendar.HOUR, 8);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                startCoursePendingIntent = PendingIntent.getBroadcast(this, ALARM_COURSE_START, startCourseAlarm, PendingIntent.FLAG_IMMUTABLE);
                endCoursePendingIntent = PendingIntent.getBroadcast(this, ALARM_COURSE_END, endCourseAlarm, PendingIntent.FLAG_IMMUTABLE);

                alarmManager.set(AlarmManager.RTC, startCourseCalendarAlert.getTimeInMillis(), startCoursePendingIntent);
                alarmManager.set(AlarmManager.RTC, endCourseCalendarAlert.getTimeInMillis(), endCoursePendingIntent);
            } else {
                if(alarmManager != null) {
                    alarmManager.cancel(startCoursePendingIntent);
                    alarmManager.cancel(endCoursePendingIntent);
                    startCoursePendingIntent.cancel();
                    endCoursePendingIntent.cancel();
                }
            }

            CourseModel courseModel = new CourseModel(termID, title, startDate, endDate, isAlarmEnabled, courseStatus, instructorName, instructorPhone, instructorEmail);
            courseModel.setId(courseID);
            courseVM.update(courseModel);
            Toast.makeText(this, "Course updated successfully", Toast.LENGTH_SHORT).show();
        } Toast.makeText(this, "Unable to update course", Toast.LENGTH_SHORT).show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.course_detail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.course_detail_menu_notes) {
            Intent loadCourseNotes = new Intent(this, CourseNotesActivity.class);
            loadCourseNotes.putExtra(CourseNotesActivity.EXTRA_COURSE_ID, courseID);
            loadCourseNotes.putExtra(CourseNotesActivity.EXTRA_COURSE_TITLE, (CharSequence) tvTitle);
            startActivity(loadCourseNotes);
            return true;
        } else if (menuItem.getItemId() == R.id.course_detail_menu_assessments) {
            Intent loadCourseAssessments = new Intent(this, AssessmentDetailActivity.class);
            loadCourseAssessments.putExtra(AssessmentListActivity.EXTRA_COURSE_ID, courseID);
            loadCourseAssessments.putExtra(AssessmentListActivity.EXTRA_COURSE_TITLE, (CharSequence) tvTitle);
            startActivity(loadCourseAssessments);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public static String getStatus(int status) {
        return switch (status) {
            case STATUS_IN_PROGRESS -> "In Progress";
            case STATUS_COMPLETED -> "Completed";
            default -> "";
        };
    }
}
