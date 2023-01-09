package com.example.studentscheduler.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.studentscheduler.Util.UtilEntity;

@Entity(tableName = "terms")
public class TermEntity extends UtilEntity {
    @PrimaryKey(autoGenerate = true)

    private int id;
    private String title;
    private String startDate;
    private String endDate;

    public TermEntity(String title, String startDate, String endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Getters
    public int getId() {
        return id;
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

    //Setters
    public void setId(int id) {
        this.id = id;
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
}
