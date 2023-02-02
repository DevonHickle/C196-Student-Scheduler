package com.example.studentscheduler.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.Models.TermModel;

import java.util.List;

@Dao
public interface TermDAO extends UtilDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TermModel termEntity);

    @Update
    void update(TermModel termEntity);

    @Delete
    void delete(TermModel termEntity);

    // Query terms and order by ascending ID
    @Query("SELECT * FROM terms ORDER BY id ASC")
    LiveData<List<TermModel>> getAllTerms();
}
