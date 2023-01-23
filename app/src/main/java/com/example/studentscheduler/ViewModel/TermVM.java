package com.example.studentscheduler.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.studentscheduler.Models.TermModel;
import com.example.studentscheduler.Database.Repo;

import java.util.List;

public class TermVM extends AndroidViewModel {
    private final Repo repo;
    private final LiveData<List<TermModel>> allTerms;

    public TermVM(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
        allTerms = repo.getAllTerms();
    }

    public void insert(TermModel termEntity) {
        repo.insert(termEntity);
    }

    public void update(TermModel termEntity) {
        repo.update(termEntity);
    }

    public void delete(TermModel termEntity) {
        repo.delete(termEntity);
    }

    public LiveData<List<TermModel>> getAllTerms() {
        return allTerms;
    }
}
