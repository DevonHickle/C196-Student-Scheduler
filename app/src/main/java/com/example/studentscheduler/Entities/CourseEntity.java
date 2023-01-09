package com.example.studentscheduler.Entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.studentscheduler.Util.UtilEntity;

@Entity(tableName = "courses",
    foreignKeys = @ForeignKey(entity = TermEntity.class,
            parentColumns = "id",
            childColumns = "termID",
            onDelete = CASCADE
    ))
public class CourseEntity extends UtilEntity {
    @PrimaryKey(autoGenerate = true)

    private int id;
    private int termID;
    private String title;
    private String startDate;
    private String endDate;
    private boolean alert;
    private int status;
    private String MentorName;
    private String MentorPhone;
    private String MentorEmail;

    public CourseEntity(int termID, String title, String startDate, String endDate, boolean alert, int status, String MentorName, String MentorPhone, String MentorEmail) {
        this.termID = termID;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.alert = alert;
        this.status = status;
        this.MentorName = MentorName;
        this.MentorPhone = MentorPhone;
        this.MentorEmail = MentorEmail;
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

    public String getMentorName() {
        return MentorName;
    }

    public String getMentorPhone() {
        return MentorPhone;
    }

    public String getMentorEmail() {
        return MentorEmail;
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

    public void setMentorName(String MentorName) {
        this.MentorName = MentorName;
    }

    public void setMentorPhone(String MentorPhone) {
        this.MentorPhone = MentorPhone;
    }

    public void setMentorEmail(String MentorEmail) {
        this.MentorEmail = MentorEmail;
    }
}
