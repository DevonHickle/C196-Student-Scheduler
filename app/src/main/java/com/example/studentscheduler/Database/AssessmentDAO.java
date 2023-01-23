package com.example.studentscheduler.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.Models.AssessmentModel;
import com.example.studentscheduler.Util.UtilDAO;

import java.util.List;

@Dao
public interface AssessmentDAO extends UtilDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AssessmentModel assessmentEntity);

    @Update
    void update(AssessmentModel assessmentEntity);

    @Delete
    void delete(AssessmentModel assessmentEntity);

    @Query("SELECT * FROM AssessmentModel WHERE courseID = :courseID ORDER BY id ASC")
    LiveData<List<AssessmentModel>> getCourseAssessments(int courseID);
}
