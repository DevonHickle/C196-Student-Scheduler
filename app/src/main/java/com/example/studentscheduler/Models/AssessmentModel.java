package com.example.studentscheduler.Models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.studentscheduler.Database.UtilEntity;

@Entity(tableName = "assessments",
        foreignKeys = @ForeignKey(entity = CourseModel.class,
                parentColumns = "id",
                childColumns = "courseID",
                onDelete = CASCADE
        ))
public class AssessmentModel extends UtilEntity {
    @PrimaryKey(autoGenerate = true)

    private int id;
    private int courseID;
    private String name;
    private int type;
    private String goalDate;
    private boolean alert;

    public AssessmentModel(int courseID, String name, int type, String goalDate, boolean alert) {
        this.courseID = courseID;
        this.name = name;
        this.type = type;
        this.goalDate = goalDate;
        this.alert = alert;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getType() {
        return type;
    }

    public String getGoalDate() {
        return goalDate;
    }

    public boolean getAlert() {
        return alert;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setCourseID( int courseID) {
        this.courseID = courseID;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setGoalDate (String goalDate) {
        this.goalDate = goalDate;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }
}
