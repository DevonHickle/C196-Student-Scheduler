package com.example.studentscheduler.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.studentscheduler.Entities.NoteEntity;
import com.example.studentscheduler.SQLite.Repo;

import java.util.List;

public class NoteVM extends AndroidViewModel {
    private Repo repo;

    public NoteVM(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
    }

    public void insert(NoteEntity noteEntity) {
        repo.insert(noteEntity);
    }

    public void update(NoteEntity noteEntity) {
        repo.update(noteEntity);
    }

    public void delete(NoteEntity noteEntity) {
        repo.delete(noteEntity);
    }

    public LiveData<List<NoteEntity>> getCourseNotes(int courseID) {
        return repo.getCourseNotes(courseID);
    }
}
