package com.example.studentscheduler.Models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.studentscheduler.Util.UtilEntity;

@Entity(tableName = "courses",
    foreignKeys = @ForeignKey(entity = TermModel.class,
            parentColumns = "id",
            childColumns = "termID",
            onDelete = CASCADE
    ))
public class CourseModel extends UtilEntity {
    @PrimaryKey(autoGenerate = true)

    private int id;
    private int termID;
    private String title;
    private String startDate;
    private String endDate;
    private boolean alert;
    private int status;
    private String instructorName;
    private String instructorPhone;
    private String instructorEmail;

    public CourseModel(int termID, String title, String startDate, String endDate, boolean alert, int status, String instructorName, String instructorPhone, String instructorEmail) {
        this.termID = termID;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.alert = alert;
        this.status = status;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getTermID() {
        return termID;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public boolean getAlert() {
        return alert;
    }

    public int getStatus() {
        return status;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInstructorName(String instructorName) {this.instructorName = instructorName;}

    public void setInstructorPhone(String instructorPhone) {this.instructorPhone = instructorPhone;}

    public void setInstructorEmail(String instructorEmail) {this.instructorEmail = instructorEmail;}
}
