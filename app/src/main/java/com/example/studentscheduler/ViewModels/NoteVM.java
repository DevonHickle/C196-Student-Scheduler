package com.example.studentscheduler.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.studentscheduler.Models.NoteModel;
import com.example.studentscheduler.Database.Repo;

import java.util.List;

public class NoteVM extends AndroidViewModel {
    private Repo repo;

    public NoteVM(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
    }

    public void insert(NoteModel noteEntity) {
        repo.insert(noteEntity);
    }

    public void update(NoteModel noteEntity) {
        repo.update(noteEntity);
    }

    public void delete(NoteModel noteEntity) {
        repo.delete(noteEntity);
    }

    public LiveData<List<NoteModel>> getCourseNotes(int courseID) {
        return repo.getCourseNotes(courseID);
    }
}
