package com.example.studentscheduler.Activities;

import android.annotation.SuppressLint;
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

import com.example.studentscheduler.Activities.AddEdit.AddEditCourseNotes;
import com.example.studentscheduler.Adapters.NotesAdapter;
import com.example.studentscheduler.Models.NoteModel;
import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModels.NoteVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotesListActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final String EXTRA_COURSE_ID = "com.example.studentscheduler.Activities.COURSE_ID";
    public static final String EXTRA_COURSE_TITLE = "com.example.studentscheduler.Activities.COURSE_TITLE";

    private int courseID;
    private String courseTitle;

    private NoteVM noteVM;

    @SuppressLint("NotifyDataSetChanged")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_list);

        FloatingActionButton addNoteButton = findViewById(R.id.add_note_button);
        addNoteButton.setOnClickListener(view -> {
            Intent addNote = new Intent(this, AddEditCourseNotes.class);
            startActivityForResult(addNote, ADD_NOTE_REQUEST);
        });

        Intent loadNotesList = getIntent();
        courseID = loadNotesList.getIntExtra(EXTRA_COURSE_ID, -1);
        courseTitle = loadNotesList.getStringExtra(EXTRA_COURSE_TITLE);

        setTitle(courseTitle + " Notes");

        RecyclerView recyclerView = findViewById(R.id.noteListRecyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.hasFixedSize();
        final NotesAdapter mNotesAdapter = new NotesAdapter();
        recyclerView.setAdapter(mNotesAdapter);
        mNotesAdapter.notifyDataSetChanged();

        noteVM = new ViewModelProvider(this).get(NoteVM.class);
        noteVM.getCourseNotes(courseID).observe(this, mNotesAdapter::setNote);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                NoteModel deletedNote = mNotesAdapter.getNotePosition(viewHolder.getAdapterPosition());
                noteVM.delete(deletedNote);
                Toast.makeText(NotesListActivity.this, "Note Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        mNotesAdapter.setOnItemClickListener(noteModel -> {
            Intent loadNotes = new Intent(this, NoteDetailActivity.class);
            loadNotes.putExtra(NoteDetailActivity.EXTRA_NOTE_ID, noteModel.getId());
            loadNotes.putExtra(NoteDetailActivity.EXTRA_NOTE_COURSE_ID, noteModel.getCourseID());
            loadNotes.putExtra(NoteDetailActivity.EXTRA_NOTE_COURSE_TITLE, courseTitle);
            loadNotes.putExtra(NoteDetailActivity.EXTRA_NOTE_TITLE, noteModel.getName());
            loadNotes.putExtra(NoteDetailActivity.EXTRA_NOTE_CONTENT, noteModel.getContent());
            startActivity(loadNotes);
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            int courseID = getIntent().getIntExtra(EXTRA_COURSE_ID, -1);
            assert intent != null;
            String noteTitle = intent.getStringExtra(AddEditCourseNotes.EXTRA_COURSE_NOTE_TITLE);
            String noteContent = intent.getStringExtra(AddEditCourseNotes.EXTRA_COURSE_NOTE_CONTENT);

            if(courseID == -1) throw new AssertionError("Notes List, course ID cannot be -1");
            NoteModel noteModel = new NoteModel(courseID, noteTitle, noteContent);
            noteVM.insert(noteModel);

            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
        } Toast.makeText(this, "Unable to save note", Toast.LENGTH_SHORT).show();
    }
}
