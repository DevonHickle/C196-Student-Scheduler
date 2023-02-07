package com.example.studentscheduler.Views;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModels.CourseVM;

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

        // TODO: Set layout IDs and other stuffs. Mimic after other Detail views
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
