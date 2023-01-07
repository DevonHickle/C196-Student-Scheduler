package com.example.studentscheduler.SQLite;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.studentscheduler.Entities.AssessmentEntity;

import java.util.List;

public class Repo {
    public Repo(Application application) {
    }

    public void insert(AssessmentEntity ae) {
    }

    public void update(AssessmentEntity ae) {
    }

    public void delete(AssessmentEntity ae) {
    }

    public LiveData<List<AssessmentEntity>> getCourseAssignments(int courseID) {
        return null;
    }
}
