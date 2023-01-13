package com.example.studentscheduler.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.studentscheduler.Entities.TermEntity;
import com.example.studentscheduler.SQLite.Repo;

import java.util.List;

public class TermVM extends AndroidViewModel {
    private Repo repo;
    private LiveData<List<TermEntity>> allTerms;

    public TermVM(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
        allTerms = repo.getAllTerms();
    }

    public void insert(TermEntity termEntity) {
        repo.insert(termEntity);
    }

    public void update(TermEntity termEntity) {
        repo.update(termEntity);
    }

    public void delete(TermEntity termEntity) {
        repo.delete(termEntity);
    }

    public LiveData<List<TermEntity>> getAllTerms() {
        return allTerms;
    }
}
