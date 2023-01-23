package com.example.studentscheduler.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.Models.NoteModel;

import java.util.List;

@Dao
public interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteModel noteEntity);

    @Update
    void update(NoteModel noteEntity);

    @Delete
    void delete(NoteModel noteEntity);

    @Query("SELECT * FROM NoteModel WHERE courseID= :courseID ORDER BY id ASC")
    LiveData<List<NoteModel>> getCourseNotes(int courseID);
}
