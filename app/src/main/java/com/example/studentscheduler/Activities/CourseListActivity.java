package com.example.studentscheduler.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Activities.AddEdit.AddEditCourses;
import com.example.studentscheduler.Adapters.CoursesAdapter;
import com.example.studentscheduler.Models.CourseModel;
import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModels.CourseVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CourseListActivity extends AppCompatActivity {
    public static final String EXTRA_COURSE_TERM_ID = "com.example.studentscheduler.Activities.TERM_ID";
    public static final String EXTRA_COURSE_TERM_TITLE = "com.example.studentscheduler.Activities.TERM_TITLE";

    private int termID;
    private CourseVM courseVM;

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_list);

        FloatingActionButton addCourseBtn = findViewById(R.id.add_course_button);
        addCourseBtn.setOnClickListener(view -> {
            Intent addCourseInt = new Intent(this, AddEditCourses.class);
            startActivityForResult(addCourseInt, AddEditCourses.REQUEST_ADD_COURSE);
        });

        Intent loadCourseList = getIntent();
        termID = loadCourseList.getIntExtra(EXTRA_COURSE_TERM_TITLE, -1);
        String termTitle = loadCourseList.getStringExtra(EXTRA_COURSE_TERM_TITLE);
        setTitle(termTitle + " Courses");

        RecyclerView recyclerView = findViewById(R.id.courseListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        CoursesAdapter coursesAdapter = new CoursesAdapter();
        recyclerView.setAdapter(coursesAdapter);

        courseVM = new ViewModelProvider(this).get(CourseVM.class);
        courseVM.getLiveTermCourses(termID).observe(this, coursesAdapter::setCourses);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                CourseModel deletedCourse = coursesAdapter.getCourses(viewHolder.getAdapterPosition());
                courseVM.delete(deletedCourse);
                Toast.makeText(CourseListActivity.this, "Course Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        coursesAdapter.setOnItemClickListener(courseModel -> {
            Intent intent = new Intent(CourseListActivity.this, CourseDetailActivity.class);
            intent.putExtra(CourseDetailActivity.EXTRA_COURSE_TERM_ID, termID);
            intent.putExtra(CourseDetailActivity.EXTRA_COURSE_ID, courseModel.getId());
            intent.putExtra(CourseDetailActivity.EXTRA_COURSE_TITLE, courseModel.getTitle());
            intent.putExtra(CourseDetailActivity.EXTRA_COURSE_START_DATE, courseModel.getStartDate());
            intent.putExtra(CourseDetailActivity.EXTRA_COURSE_END_DATE, courseModel.getEndDate());
            intent.putExtra(CourseDetailActivity.EXTRA_COURSE_ALERT, courseModel.getAlert());
            intent.putExtra(CourseDetailActivity.EXTRA_COURSE_STATUS, courseModel.getStatus());
            intent.putExtra(CourseDetailActivity.EXTRA_COURSE_INSTRUCTOR_NAME, courseModel.getInstructorName());
            intent.putExtra(CourseDetailActivity.EXTRA_COURSE_INSTRUCTOR_EMAIL, courseModel.getInstructorEmail());
            intent.putExtra(CourseDetailActivity.EXTRA_COURSE_INSTRUCTOR_PHONE, courseModel.getInstructorPhone());
            startActivity(intent);
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int termID = getIntent().getIntExtra(EXTRA_COURSE_TERM_ID, -1);
        assert data != null;
        String title = data.getStringExtra(AddEditCourses.EXTRA_COURSE_TITLE);
        String startDate = data.getStringExtra(AddEditCourses.EXTRA_COURSE_START_DATE);
        String endDate = data.getStringExtra(AddEditCourses.EXTRA_COURSE_END_DATE);
        boolean courseAlert = data.getBooleanExtra(AddEditCourses.EXTRA_COURSE_ALERT, false);
        int status = data.getIntExtra(AddEditCourses.EXTRA_COURSE_STATUS, -1);
        String instructorName = data.getStringExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_NAME);
        String instructorEmail = data.getStringExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_EMAIL);
        String instructorPhone = data.getStringExtra(AddEditCourses.EXTRA_COURSE_INSTRUCTOR_PHONE);

        if(termID == -1) throw new AssertionError("Term ID cannot be less than one");
        CourseModel courseModel = new CourseModel(termID, title, startDate, endDate, courseAlert, status, instructorName, instructorEmail, instructorPhone);
        courseVM.insert(courseModel);

        Toast.makeText(this, title + " added", Toast.LENGTH_SHORT).show();

        if(requestCode != AddEditCourses.REQUEST_ADD_COURSE && resultCode != RESULT_OK) {
            Toast.makeText(this, "Unable to add course", Toast.LENGTH_SHORT).show();
        }
    }
}
