package com.example.studentscheduler.Entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments",
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                parentColumns = "id",
                childColumns = "courseID",
                onDelete = CASCADE
        ))
public class AssessmentEntity extends com.example.studentscheduler.Util.Entity {
    @PrimaryKey(autoGenerate = true)

    private int id;
    private int courseID;
    private String name;
    private int type;
    private String goalDate;
    private boolean alert;

    public AssessmentEntity(int courseID, String name, int type, String goalDate, boolean alert) {
        this.courseID = courseID;
        this.name = name;
        this.type = type;
        this.goalDate = goalDate;
        this.alert = alert;
    }

    // Getters
    public int getID() {
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
    public void setID(int id) {
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
