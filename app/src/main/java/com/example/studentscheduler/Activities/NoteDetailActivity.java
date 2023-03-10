package com.example.studentscheduler.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentscheduler.Activities.AddEdit.AddEditCourseNotes;
import com.example.studentscheduler.Models.NoteModel;
import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModels.NoteVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteDetailActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE_COURSE_ID =
            "com.example.studentscheduler.Activities.NOTE_COURSE_ID";
    public static final String EXTRA_NOTE_COURSE_TITLE =
            "com.example.studentscheduler.Activities.NOTE_COURSE_TITLE";
    public static final String EXTRA_NOTE_ID =
            "com.example.studentscheduler.Activities.NOTE_ID";
    public static final String EXTRA_NOTE_TITLE =
            "com.example.studentscheduler.Activities.NOTE_TITLE";
    public static final String EXTRA_NOTE_CONTENT =
            "com.example.studentscheduler.Activities.NOTE_CONTENT";

    public static final int EDIT_NOTE_REQUEST = 2;

    private NoteVM noteVM;

    private int courseID;
    private int noteID;
    private TextView textViewTitle;
    private TextView textViewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteVM = new ViewModelProvider(this).get(NoteVM.class);
        setContentView(R.layout.note_detail);

        textViewTitle = findViewById(R.id.detailed_note_title);
        textViewContent = findViewById(R.id.detailed_note_content);

        Intent parentIntent = getIntent();
        courseID = parentIntent.getIntExtra(EXTRA_NOTE_COURSE_ID, -1);
        noteID = parentIntent.getIntExtra(EXTRA_NOTE_ID, -1);
        String noteTitle = parentIntent.getStringExtra(EXTRA_NOTE_TITLE);
        String courseTitle = parentIntent.getStringExtra(EXTRA_NOTE_COURSE_TITLE);

        setTitle(courseTitle + " | " + noteTitle);

        textViewTitle.setText(noteTitle);
        textViewContent.setText(parentIntent.getStringExtra(EXTRA_NOTE_CONTENT));

        FloatingActionButton buttonEditNote = findViewById(R.id.btn_edit_note);
        buttonEditNote.setOnClickListener(v -> {
            Intent editNoteIntent = new Intent(NoteDetailActivity.this, AddEditCourseNotes.class);
            editNoteIntent.putExtra(AddEditCourseNotes.EXTRA_NOTE_ID, noteID);
            editNoteIntent.putExtra(AddEditCourseNotes.EXTRA_COURSE_ID, courseID);
            editNoteIntent.putExtra(AddEditCourseNotes.EXTRA_COURSE_NOTE_TITLE, noteTitle);
            editNoteIntent.putExtra(AddEditCourseNotes.EXTRA_COURSE_NOTE_CONTENT, textViewContent.getText().toString());
            startActivityForResult(editNoteIntent, EDIT_NOTE_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            int noteID = data.getIntExtra(AddEditCourseNotes.EXTRA_NOTE_ID, -1);
            String noteName = data.getStringExtra(AddEditCourseNotes.EXTRA_COURSE_NOTE_TITLE);
            String noteContent = data.getStringExtra(AddEditCourseNotes.EXTRA_COURSE_NOTE_CONTENT);

            if(noteID == -1) {
                Toast.makeText(this, "Error, unable to save note", Toast.LENGTH_SHORT).show();
                return;
            }

            textViewTitle.setText(noteName);
            textViewContent.setText(noteContent);

            NoteModel noteEntity = new NoteModel(courseID,
                    noteName, noteContent);

            noteEntity.setId(noteID);
            noteVM.update(noteEntity);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Unable to update note", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_share_text) {
            shareNote();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void shareNote() {
        Intent shareNote = new Intent(Intent.ACTION_SEND);
        shareNote.putExtra(Intent.EXTRA_TITLE, textViewTitle.getText().toString());
        shareNote.putExtra(Intent.EXTRA_TEXT, textViewContent.getText().toString());
        shareNote.setType("text/plain");
        startActivity(shareNote);
    }
}
