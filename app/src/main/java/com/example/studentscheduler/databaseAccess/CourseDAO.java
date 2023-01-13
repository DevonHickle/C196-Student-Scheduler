package com.example.studentscheduler.databaseAccess;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;

import com.example.studentscheduler.Entities.CourseEntity;
import com.example.studentscheduler.Util.UtilDAO;

import java.util.List;

@Dao
public interface CourseDAO extends UtilDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CourseEntity courseEntity);

    @Update void update(CourseEntity courseEntity);

    @Delete void delete(CourseEntity courseEntity);

    @Query("SELECT * FROM courses WHERE termID = :termID ORDER BY id ASC")
    LiveData<List<CourseEntity>> getActiveTermCourses(int termID);

    @Query("SELECT * FROM courses WHERE termID = :termID ORDER BY id ASC")
    List<CourseEntity> getTermCourses(int termID);
}
