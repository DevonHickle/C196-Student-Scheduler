package com.example.studentscheduler.SQLite;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.studentscheduler.Entities.AssessmentEntity;
import com.example.studentscheduler.Entities.CourseEntity;
import com.example.studentscheduler.Entities.NoteEntity;
import com.example.studentscheduler.Entities.TermEntity;
import com.example.studentscheduler.databaseAccess.AssessmentDAO;
import com.example.studentscheduler.databaseAccess.CourseDAO;
import com.example.studentscheduler.databaseAccess.NoteDAO;
import com.example.studentscheduler.databaseAccess.TermDAO;

import java.util.List;

public class Repo {
    private TermDAO termDAO;
    private LiveData<List<TermEntity>> listAllTerms;

    private CourseDAO courseDAO;
    private LiveData<List<CourseEntity>> listTermCourses;

    private AssessmentDAO assessmentDAO;
    private LiveData<List<AssessmentEntity>> listAssessments;

    private NoteDAO noteDAO;
    private LiveData<List<NoteEntity>> listAllNotes;

    public Repo(Application application) {
        SQLDatabase database = SQLDatabase.getInstance(application);
        termDAO = database.termDAO();
        courseDAO = database.courseDAO();
        assessmentDAO = database.assessmentDAO();
        noteDAO = database.noteDAO();
    }

    // Insert
    public void insert(AssessmentEntity ae) {
        new InsertAsyncAssessment(assessmentDAO).execute(ae);
    }

    public void insert(TermEntity te) {
        new InsertAsyncTerm(termDAO).execute(te);
    }

    public void insert(NoteEntity ne) {
        new InsertAsyncNote(noteDAO).execute(ne);
    }

    public void insert(CourseEntity courseEntity) {
        new InsertAsyncCourse(courseDAO).execute(courseEntity);
    }

    // Update
    public void update(AssessmentEntity ae) {
        new UpdateAsyncAssessment(assessmentDAO).execute(ae);
    }

    public void update(TermEntity te) {
        new UpdateAsyncTerm(termDAO).execute(te);
    }

    public void update(NoteEntity ne) {
        new UpdateAsyncNote(noteDAO).execute(ne);
    }

    public void update(CourseEntity courseEntity) {
        new UpdateAsyncCourse(courseDAO).execute(courseEntity);
    }

    // Delete
    public void delete(AssessmentEntity ae) {
        new DeleteAsyncAssessment(assessmentDAO).execute(ae);
    }

    public void delete(TermEntity te) {
        new DeleteAsyncTerm(termDAO).execute(te);
    }

    public void delete(NoteEntity ne) {
        new DeleteAsyncNote(noteDAO).execute(ne);
    }

    public void delete(CourseEntity courseEntity) {
        new DeleteAsyncCourse(courseDAO).execute(courseEntity);
    }

    //LiveData List
    public LiveData<List<AssessmentEntity>> getCourseAssignments(int courseID) {
        return assessmentDAO.getCourseAssessments(courseID);
    }

    public LiveData<List<TermEntity>> getAllTerms() {
        return listAllTerms;
    }

    public LiveData<List<NoteEntity>> getCourseNotes(int courseID) {
        return noteDAO.getCourseNotes(courseID);
    }

    public LiveData<List<CourseEntity>> getLiveTermCourses(int termID) {
        return courseDAO.getActiveTermCourses(termID);
    }

    // ASYNC Tasks
    public List<CourseEntity> getTermCourses(int termID) {
        return new GetAsyncTermCourses(courseDAO).execute(termID).get();
    }
}
