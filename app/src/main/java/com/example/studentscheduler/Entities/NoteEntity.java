package com.example.studentscheduler.Entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.studentscheduler.Util.UtilEntity;

@Entity(tableName = "notes",
    foreignKeys = @ForeignKey(entity = CourseEntity.class,
        parentColumns = "id",
            childColumns = "courseID",
            onDelete = CASCADE
    ))
public class NoteEntity  extends UtilEntity {
    @PrimaryKey(autoGenerate = true)

    private int id;
    private int courseID;
    private String name;
    private String content;

    public NoteEntity(int couseID, String name, String content) {
        this.courseID = courseID;
        this.name = name;
        this.content = content;
    }

    // Getters
    public int getCourseID() {
        return courseID;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    //Setters
    public void setID(int id) {
        this.id = id;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }
}