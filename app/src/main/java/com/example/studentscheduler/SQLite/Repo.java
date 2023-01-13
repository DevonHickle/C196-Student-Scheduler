package com.example.studentscheduler.SQLite;

import android.app.Application;
import android.os.AsyncTask;

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
import java.util.concurrent.ExecutionException;

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
    public List<CourseEntity> getTermCourses(int termID) throws ExecutionException, InterruptedException {
        return new GetAsyncTermCourses(courseDAO).execute(termID).get();
    }

    private static class GetAsyncTermCourses extends AsyncTask<Integer, Void, List<CourseEntity>> {
        private CourseDAO courseDAO;

        private GetAsyncTermCourses(CourseDAO courseDAO) {
            this.courseDAO = courseDAO;
        }
        @Override
        protected List<CourseEntity> doInBackground(Integer... Integers) {
            return courseDAO.getTermCourses(Integers[0]);
        }
    }

    // Insert Async Tasks
    private static class InsertAsyncTerm extends AsyncTask<TermEntity, Void, Void> {
        private TermDAO termDAO;

        private InsertAsyncTerm(TermDAO termDAO) {
            this.termDAO = termDAO;
        }

        @Override
        protected Void doInBackground(TermEntity... termEntities) {
            termDAO.insert(termEntities[0]);
            return null;
        }
    }
    private static class InsertAsyncCourse extends AsyncTask<CourseEntity, Void, Void> {
        private CourseDAO courseDAO;

        private InsertAsyncCourse(CourseDAO courseDAO) {
            this.courseDAO = courseDAO;
        }

        @Override
        protected Void doInBackground(CourseEntity... courseEntities) {
            courseDAO.insert(courseEntities[0]);
            return null;
        }
    }
    private static class InsertAsyncAssessment extends AsyncTask<AssessmentEntity, Void, Void> {
        private AssessmentDAO assessmentDAO;

        private InsertAsyncAssessment(AssessmentDAO assessmentDAO) {
            this.assessmentDAO = assessmentDAO;
        }

        @Override
        protected Void doInBackground(AssessmentEntity... assessmentEntities) {
            assessmentDAO.insert(assessmentEntities[0]);
            return null;
        }
    }
    private static class InsertAsyncNote extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDAO noteDAO;

        private InsertAsyncNote(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDAO.insert(noteEntities[0]);
            return null;
        }
    }

    // Update Async Tasks
    private static class UpdateAsyncTerm extends AsyncTask<TermEntity, Void, Void> {
        private TermDAO termDAO;

        private UpdateAsyncTerm(TermDAO termDAO) {
            this.termDAO = termDAO;
        }

        @Override
        protected Void doInBackground(TermEntity... termEntities) {
            termDAO.update(termEntities[0]);
            return null;
        }
    }

    private static class UpdateAsyncCourse extends AsyncTask<CourseEntity, Void, Void> {
        private CourseDAO courseDAO;

        private UpdateAsyncCourse(CourseDAO courseDAO) {
            this.courseDAO = courseDAO;
        }

        @Override
        protected Void doInBackground(CourseEntity... courseEntities) {
            courseDAO.update(courseEntities[0]);
            return null;
        }
    }

    private static class UpdateAsyncAssessment extends AsyncTask<AssessmentEntity, Void, Void> {
        private AssessmentDAO assessmentDAO;

        private UpdateAsyncAssessment(AssessmentDAO assessmentDAO) {
            this.assessmentDAO = assessmentDAO;
        }

        @Override
        protected Void doInBackground(AssessmentEntity... assessmentEntities) {
            assessmentDAO.update(assessmentEntities[0]);
            return null;
        }
    }

    private static class UpdateAsyncNote extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDAO noteDAO;

        private UpdateAsyncNote(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDAO.update(noteEntities[0]);
            return null;
        }
    }

    // Delete Async Tasks
    private static class DeleteAsyncTerm extends AsyncTask<TermEntity, Void, Void> {
        private TermDAO termDAO;

        private DeleteAsyncTerm(TermDAO termDAO) {
            this.termDAO = termDAO;
        }

        @Override
        protected Void doInBackground(TermEntity... termEntities) {
            termDAO.delete(termEntities[0]);
            return null;
        }
    }

    private static class DeleteAsyncCourse extends AsyncTask<CourseEntity, Void, Void> {
        private CourseDAO courseDAO;

        private DeleteAsyncCourse(CourseDAO courseDAO) {
            this.courseDAO = courseDAO;
        }

        @Override
        protected Void doInBackground(CourseEntity... courseEntities) {
            courseDAO.delete(courseEntities[0]);
            return null;
        }
    }

    private static class DeleteAsyncAssessment extends AsyncTask<AssessmentEntity, Void, Void> {
        private AssessmentDAO assessmentDAO;

        private DeleteAsyncAssessment(AssessmentDAO assessmentDAO) {
            this.assessmentDAO = assessmentDAO;
        }

        @Override
        protected Void doInBackground(AssessmentEntity... assessmentEntities) {
            assessmentDAO.delete(assessmentEntities[0]);
            return null;
        }
    }

    private static class DeleteAsyncNote extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDAO noteDAO;

        private DeleteAsyncNote(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDAO.delete(noteEntities[0]);
            return null;
        }
    }
}
