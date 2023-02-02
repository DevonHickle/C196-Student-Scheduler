package com.example.studentscheduler.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.studentscheduler.Models.CourseModel;
import com.example.studentscheduler.Database.Repo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CourseVM extends AndroidViewModel {

    public Repo repo;

    public CourseVM(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
    }

    public void insert(CourseModel courseEntity) {
        repo.insert(courseEntity);
    }

    public void update(CourseModel courseEntity) {
        repo.update(courseEntity);
    }

    public void delete(CourseModel courseEntity) {
        repo.delete(courseEntity);
    }

    public LiveData<List<CourseModel>> getLiveTermCourses(int termID) {
        return repo.getLiveTermCourses(termID);
    }

    public List<CourseModel> getTermCourses(int termID) throws ExecutionException, InterruptedException {
        return repo.getTermCourses(termID);
    }
}
