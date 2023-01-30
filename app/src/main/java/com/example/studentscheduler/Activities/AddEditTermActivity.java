package com.example.studentscheduler.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentscheduler.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class AddEditTermActivity extends AppCompatActivity {
    public static final String EXTRA_TERM_TITLE = "com.example.studentscheduler.Activities.TERM_TITLE";
    public static final String EXTRA_TERM_START_DATE = "com.example.studentscheduler.Activities.TERM_START_DATE";
    public static final String EXTRA_TERM_END_DATE = "com.example.studentscheduler.Activities.TERM_END_DATE";
    public static final String EXTRA_TERM_ID = "com.example.studentscheduler.Activities.TERM_ID";

    public static final String DATE_FORMAT = "MM/dd/yyyy";

    private Calendar calStartDate;
    private Calendar calEndDate;

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

        editStartDate.setOnClickListener(view -> new DatePickerDialog(AddEditTermActivity.this,
                dateStart,
                calStartDate.get(Calendar.YEAR),
                calStartDate.get(Calendar.MONTH),
                calStartDate.get(Calendar.DAY_OF_MONTH)).show()
        );

        this.calEndDate = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateEnd = (view, year, month, dayOfMonth) -> {
            calEndDate.set(Calendar.YEAR, year);
            calEndDate.set(Calendar.MONTH, month);
            calEndDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(editEndDate, calEndDate);
        };

        editEndDate.setOnClickListener(view -> new DatePickerDialog(AddEditTermActivity.this,
                dateEnd,
                calEndDate.get(Calendar.YEAR),
                calEndDate.get(Calendar.MONTH),
                calEndDate.get(Calendar.DAY_OF_MONTH)).show()
        );

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_TERM_ID)) {
            setTitle("Edit Term");
            editTitle.setText(intent.getStringExtra(EXTRA_TERM_TITLE));
            editStartDate.setText(intent.getStringExtra(EXTRA_TERM_START_DATE));
            editEndDate.setText(intent.getStringExtra(EXTRA_TERM_END_DATE));
        } else {
            setTitle("Add Term");
        }
    }

    private void saveNewTerm() {
        String termTitle = editTitle.getText().toString();
        String startDate = editStartDate.getText().toString();
        String endDate = editEndDate.getText().toString();

        if(termTitle.trim().isEmpty() || startDate.trim().isEmpty() || endDate.trim().isEmpty()) {
            Toast.makeText(this, "Term Title, Start Date, and End Date are empty", Toast.LENGTH_SHORT).show();
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TERM_TITLE, termTitle);
        data.putExtra(EXTRA_TERM_START_DATE, startDate);
        data.putExtra(EXTRA_TERM_END_DATE, endDate);

        int termID = getIntent().getIntExtra(EXTRA_TERM_ID, -1);
        if(termID != -1) {
            data.putExtra(EXTRA_TERM_ID, termID);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_edit, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_add_edit_save) {
            saveNewTerm();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void updateLabel(EditText editText, Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);

        editText.setText(simpleDateFormat.format(calendar.getTime()));
    }
}
