package com.example.studentscheduler.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.studentscheduler.Models.AssessmentModel;
import com.example.studentscheduler.Database.Repo;

import java.util.List;

public class AssessmentVM extends AndroidViewModel {
    private final Repo repo;

    public AssessmentVM(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
    }

    public void insert(AssessmentModel AE) {
        repo.insert(AE);
    }

    public void update(AssessmentModel AE) {
        repo.update(AE);
    }

    public void delete(AssessmentModel AE) {
        repo.delete(AE);
    }

    public LiveData<List<AssessmentModel>> getCourseAssignments(int courseID) {
        return repo.getCourseAssignments(courseID);
    }
}
