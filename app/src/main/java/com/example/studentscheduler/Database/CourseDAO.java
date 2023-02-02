package com.example.studentscheduler.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;

import com.example.studentscheduler.Models.CourseModel;

import java.util.List;

@Dao
public interface CourseDAO extends UtilDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CourseModel courseEntity);

    @Update void update(CourseModel courseEntity);

    @Delete void delete(CourseModel courseEntity);

    @Query("SELECT * FROM courses WHERE termID = :termID ORDER BY id ASC")
    LiveData<List<CourseModel>> getActiveTermCourses(int termID);

    @Query("SELECT * FROM courses WHERE termID = :termID ORDER BY id ASC")
    List<CourseModel> getTermCourses(int termID);
}
