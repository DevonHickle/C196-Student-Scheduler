package com.example.studentscheduler.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.studentscheduler.Entities.AssessmentEntity;
import com.example.studentscheduler.SQLite.Database;

import java.util.List;

public class AssessmentVM extends AndroidViewModel {
    private Database database;

    public AssessmentVM(@NonNull Application application) {
        super(application);
        Database database = new Database(application);
    }

    public void insert(AssessmentEntity AE) {
        database.insert(AE);
    }

    public void update(AssessmentEntity AE) {
        database.update(AE);
    }

    public void delete(AssessmentEntity AE) {
        database.delete(AE);
    }

    public LiveData<List<AssessmentEntity>> getCourseAssignments(int courseID) {
        return database.getCourseAssignments(courseID);
    }
}
