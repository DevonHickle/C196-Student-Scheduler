package com.example.studentscheduler.Entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "assessments",
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                parentColumns = "id",
                childColumns = "courseID",
                onDelete = CASCADE))
public class AssessmentEntity {
    public AssessmentEntity(int i, String s, int i1, String s1, boolean b) {
    }
}
