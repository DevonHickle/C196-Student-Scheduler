package com.example.studentscheduler.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.studentscheduler.Entities.CourseEntity;
import com.example.studentscheduler.SQLite.Repo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CourseVM extends AndroidViewModel {

    public Repo repo;

    public CourseVM(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
    }

    public void insert(CourseEntity courseEntity) {
        repo.insert(courseEntity);
    }

    public void update(CourseEntity courseEntity) {
        repo.update(courseEntity);
    }

    public void delete(CourseEntity courseEntity) {
        repo.delete(courseEntity);
    }

    public LiveData<List<CourseEntity>> getLiveTermCourses(int termID) {
        return repo.getLiveTermCourses(termID);
    }

    public List<CourseEntity> getTermCourses(int termID) throws ExecutionException, InterruptedException {
        return repo.getTermCourses(termID);
    }
}
