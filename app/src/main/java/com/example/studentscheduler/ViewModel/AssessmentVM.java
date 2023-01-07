package com.example.studentscheduler.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.studentscheduler.Entities.AssessmentEntity;
import com.example.studentscheduler.SQLite.Repo;

import java.util.List;

public class AssessmentVM extends AndroidViewModel {
    private Repo repo;

    public AssessmentVM(@NonNull Application application) {
        super(application);
        Repo repo = new Repo(application);
    }

    public void insert(AssessmentEntity AE) {
        repo.insert(AE);
    }

    public void update(AssessmentEntity AE) {
        repo.update(AE);
    }

    public void delete(AssessmentEntity AE) {
        repo.delete(AE);
    }

    public LiveData<List<AssessmentEntity>> getCourseAssignments(int courseID) {
        return repo.getCourseAssignments(courseID);
    }
}
