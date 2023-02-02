package com.example.studentscheduler.Database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.example.studentscheduler.Models.AssessmentModel;
import com.example.studentscheduler.Models.CourseModel;
import com.example.studentscheduler.Models.NoteModel;
import com.example.studentscheduler.Models.TermModel;

public interface UtilDAO {
    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TermModel entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CourseModel entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteModel entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AssessmentModel entity);

    // Update
    @Update void update(TermModel entity);

    @Update void update(CourseModel entity);

    @Update void update(NoteModel entity);

    @Update void update(AssessmentModel entity);

    // Delete
    @Delete void delete(TermModel entity);

    @Delete void delete(CourseModel entity);

    @Delete void delete(NoteModel entity);

    @Delete void delete(AssessmentModel entity);
}
