package com.example.studentscheduler.Util;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.example.studentscheduler.Entities.AssessmentEntity;
import com.example.studentscheduler.Entities.CourseEntity;
import com.example.studentscheduler.Entities.NoteEntity;
import com.example.studentscheduler.Entities.TermEntity;

public interface DAO {
    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TermEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CourseEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AssessmentEntity entity);

    // Update
    @Update void update(TermEntity entity);

    @Update void update(CourseEntity entity);

    @Update void update(NoteEntity entity);

    @Update void update(AssessmentEntity entity);

    // Delete
    @Delete void delete(TermEntity entity);

    @Delete void delete(CourseEntity entity);

    @Delete void delete(NoteEntity entity);

    @Delete void delete(AssessmentEntity entity);
}
