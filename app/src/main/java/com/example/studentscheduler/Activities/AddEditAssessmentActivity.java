package com.example.studentscheduler.Activities;

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

import com.example.studentscheduler.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class AddEditAssessmentActivity extends AppCompatActivity {
    public static final String EXTRA_ASSESSMENT_ID = "";
    public static final String EXTRA_COURSE_ASSESSMENT_TITLE = "";
    public static final String EXTRA_COURSE_ASSESSMENT_GOAL_DATE = "";
    public static final String EXTRA_COURSE_ASSESSMENT_ALERT = "";
    public static final String EXTRA_ASSESSMENT_TYPE = "";

    public static final String DATE_FORMAT = "MM/dd/yyyy";

    private Calendar calGoalDate;

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

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

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
                calGoalDate.get(Calendar.YEAR),
                calGoalDate.get(Calendar.MONTH),
                calGoalDate.get(Calendar.DAY_OF_MONTH)).show()
        );

        Intent parentIntent = getIntent();
        if(parentIntent.hasExtra(EXTRA_ASSESSMENT_ID)) {
            setTitle("Edit Assessment");
            editTitle.setText(parentIntent.getStringExtra(EXTRA_COURSE_ASSESSMENT_TITLE));
            editGoalDate.setText(parentIntent.getStringExtra(EXTRA_COURSE_ASSESSMENT_GOAL_DATE));
            editRadioType.check(getTypeBtnID(parentIntent.getIntExtra(EXTRA_ASSESSMENT_TYPE, -1)));
            parentIntent.getBooleanExtra(EXTRA_COURSE_ASSESSMENT_ALERT, false);
            checkAlarmEnabled.performClick();
        } else {
            setTitle("Add Assessment");
        }
    }

    private void saveNewAssessment() {
        String assessmentTitle = editTitle.getText().toString();
        String assessmentGoalDate = editGoalDate.getText().toString();
        boolean alarmEnabled = checkAlarmEnabled.isChecked();

        if (assessmentTitle.trim().isEmpty() || assessmentGoalDate.trim().isEmpty() || getTypeBtnID(editRadioType.getCheckedRadioButtonId()) != -1) {
            Toast.makeText(this, "Unable to save assessment, fields are empty", Toast.LENGTH_SHORT).show();
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_COURSE_ASSESSMENT_TITLE, assessmentTitle);
        data.putExtra(EXTRA_ASSESSMENT_TYPE, getRadioType(editRadioType.getCheckedRadioButtonId()));
        data.putExtra(EXTRA_COURSE_ASSESSMENT_GOAL_DATE, assessmentGoalDate);
        data.putExtra(EXTRA_COURSE_ASSESSMENT_ALERT, alarmEnabled);
        int assessmentID = getIntent().getIntExtra(EXTRA_ASSESSMENT_ID, -1);

        if(assessmentID != -1) {
            data.putExtra(EXTRA_ASSESSMENT_ID, assessmentID);
        }

        setResult(RESULT_OK, data);
        finish();
        // Rest of Data puts go here
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_edit, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_add_edit_save) {
            saveNewAssessment();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    //Update Label
    private void updateLabel() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        editGoalDate.setText(simpleDateFormat.format(calGoalDate.getTime()));
    }

    private int getTypeBtnID(int id1) {
        int buttonID;
        if (id1 == AssessmentListActivity.TYPE_PA) {
            buttonID = R.id.radio_assessment_type_pa;
        } else if (id1 == AssessmentListActivity.TYPE_OA) {
            buttonID = R.id.radio_assessment_type_oa;
        } else {
            buttonID = -1;
        }
        return buttonID;
    }

    private int getRadioType(int id2) {
        int typeID;
        if (id2 == R.id.radio_assessment_type_pa) {
            typeID = AssessmentListActivity.TYPE_PA;
        } else if (id2 == R.id.radio_assessment_type_oa) {
            typeID = AssessmentListActivity.TYPE_OA;
        } else {
            typeID = -1;
        }
        return typeID;
    }
}
