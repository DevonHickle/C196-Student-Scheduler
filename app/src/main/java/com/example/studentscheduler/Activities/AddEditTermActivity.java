package com.example.studentscheduler.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentscheduler.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEditTermActivity extends AppCompatActivity {
    public static final String EXTRA_TERM_TITLE = "com.example.studentscheduler.Activities.TERM_TITLE";
    public static final String EXTRA_TERM_START_DATE = "com.example.studentscheduler.Activities.TERM_START_DATE";
    public static final String EXTRA_TERM_END_DATE = "com.example.studentscheduler.Activities.TERM_END_DATE";
    public static final String EXTRA_TERM_ID = "com.example.studentscheduler.Activities.TERM_ID";

    public static final String DATE_FORMAT = "MM/dd/yyyy";

    private Calendar calStartDate;
    private Calendar calEndDat;

    private EditText editTitle;
    private EditText editStartDate;
    private EditText editEndDate;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_term);

        editTitle = findViewById(R.id.edit_term_title);
        editStartDate = findViewById(R.id.edit_term_start_date);
        editEndDate = findViewById(R.id.edit_term_end_date);

        this.calStartDate = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateStart = (view, year, month, dayOfMonth) -> {
            calStartDate.set(Calendar.YEAR, year);
            calStartDate.set(Calendar.MONTH, month);
            calStartDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(editStartDate, calStartDate);
        };
    }

    private void updateLabel(EditText editText, Calendar calendar) {
        String dateFormat = DATE_FORMAT;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

        editText.setText(simpleDateFormat.format(calendar.getTime()));
    }
}
