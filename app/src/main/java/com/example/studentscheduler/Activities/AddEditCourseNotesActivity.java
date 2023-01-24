package com.example.studentscheduler.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentscheduler.R;

import java.util.Objects;

public class AddEditCourseNotesActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE_ID = "com.example.studentscheduler.Activities.COURSE_NOTE_ID";
    public static final String EXTRA_COURSE_ID = "com.example.studentscheduler.Activities.EXTRA_COURSE_ID";
    public static final String EXTRA_COURSE_NOTE_TITLE = "com.example.studentscheduler.Activities.COURSE_NOTE_TITLE";
    public static final String EXTRA_COURSE_NOTE_CONTENT = "com.example.studentscheduler.Activities.COURSE_NOTE_CONTENT";

    private EditText editTextTitle;
    private EditText editTextContent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_note);

        editTextTitle = findViewById(R.id.edit_note_name);
        editTextContent = findViewById(R.id.edit_note_content);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_NOTE_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_COURSE_NOTE_TITLE));
            editTextContent.setText(intent.getStringExtra(EXTRA_COURSE_NOTE_CONTENT));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String noteTitle = editTextTitle.getText().toString();
        String noteContent = editTextContent.getText().toString();

        if(noteTitle.trim().isEmpty() || noteContent.trim().isEmpty()) {
            Toast.makeText(this, "Unable to save note, check empty fields", Toast.LENGTH_SHORT).show();
        }

        Intent data = new Intent();

        data.putExtra(EXTRA_COURSE_NOTE_TITLE, noteTitle);
        data.putExtra(EXTRA_COURSE_NOTE_CONTENT, noteContent);

        int noteID = getIntent().getIntExtra(EXTRA_NOTE_ID, -1);

        if(noteID != -1) {
            data.putExtra(EXTRA_NOTE_ID, noteID);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
