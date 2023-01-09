package com.example.studentscheduler.databaseAccess;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.Entities.AssessmentEntity;
import com.example.studentscheduler.Util.UtilDAO;

import java.util.List;

public interface AssessmentDAO extends UtilDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AssessmentEntity assessmentEntity);

    @Update
    void update(AssessmentEntity assessmentEntity);

    @Delete
    void delete(AssessmentEntity assessmentEntity);

    @Query("SELECT * FROM assessments WHERE courseID = :courseID ORDER BY id ASC")
    LiveData<List<AssessmentEntity>> getCourseAssessments(int courseID);
}
