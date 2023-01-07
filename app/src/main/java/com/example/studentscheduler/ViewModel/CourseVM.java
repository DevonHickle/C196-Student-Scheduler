package com.example.studentscheduler.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.studentscheduler.SQLite.Repo;

public class CourseVM extends AndroidViewModel {

    public Repo repo;

    public CourseVM(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
    }

    // Add Insert, Update, Delete, and LiveData List
}
