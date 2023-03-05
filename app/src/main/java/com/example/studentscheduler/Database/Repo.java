package com.example.studentscheduler.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.studentscheduler.Models.AssessmentModel;
import com.example.studentscheduler.Models.CourseModel;
import com.example.studentscheduler.Models.NoteModel;
import com.example.studentscheduler.Models.TermModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Repo {
    private final TermDAO termDAO;
    private final LiveData<List<TermModel>> listAllTerms;

    private final CourseDAO courseDAO;
    private LiveData<List<CourseModel>> listTermCourses;

    private final AssessmentDAO assessmentDAO;
    private LiveData<List<AssessmentModel>> listAssessments;

    private final NoteDAO noteDAO;
    private LiveData<List<NoteModel>> listAllNotes;

    public Repo(Application application) {
        SQLDatabase database = SQLDatabase.getInstance(application);
        termDAO = database.termDAO();
        listAllTerms = termDAO.getAllTerms();

        courseDAO = database.courseDAO();

        assessmentDAO = database.assessmentDAO();

        noteDAO = database.noteDAO();
    }

    // Insert
    public void insert(AssessmentModel ae) {
        new InsertAsyncAssessment(assessmentDAO).execute(ae);
    }

    public void insert(TermModel te) {
        new InsertAsyncTerm(termDAO).execute(te);
    }

    public void insert(NoteModel ne) {
        new InsertAsyncNote(noteDAO).execute(ne);
    }

    public void insert(CourseModel courseEntity) {
        new InsertAsyncCourse(courseDAO).execute(courseEntity);
    }

    // Update
    public void update(AssessmentModel ae) {
        new UpdateAsyncAssessment(assessmentDAO).execute(ae);
    }

    public void update(TermModel te) {
        new UpdateAsyncTerm(termDAO).execute(te);
    }

    public void update(NoteModel ne) {
        new UpdateAsyncNote(noteDAO).execute(ne);
    }

    public void update(CourseModel courseEntity) {
        new UpdateAsyncCourse(courseDAO).execute(courseEntity);
    }

    // Delete
    public void delete(AssessmentModel ae) {
        new DeleteAsyncAssessment(assessmentDAO).execute(ae);
    }

    public void delete(TermModel te) {
        new DeleteAsyncTerm(termDAO).execute(te);
    }

    public void delete(NoteModel ne) {
        new DeleteAsyncNote(noteDAO).execute(ne);
    }

    public void delete(CourseModel courseEntity) {
        new DeleteAsyncCourse(courseDAO).execute(courseEntity);
    }

    //LiveData List
    public LiveData<List<AssessmentModel>> getCourseAssignments(int courseID) {
        return assessmentDAO.getCourseAssessments(courseID);
    }

    public LiveData<List<TermModel>> getAllTerms() {
        return listAllTerms;
    }

    public LiveData<List<NoteModel>> getCourseNotes(int courseID) {
        return noteDAO.getCourseNotes(courseID);
    }

    public LiveData<List<CourseModel>> getLiveTermCourses(int termID) {
        return courseDAO.getActiveTermCourses(termID);
    }

    // ASYNC Tasks
    public List<CourseModel> getTermCourses(int termID) throws ExecutionException, InterruptedException {
        return new GetAsyncTermCourses(courseDAO).execute(termID).get();
    }

    private static class GetAsyncTermCourses extends AsyncTask<Integer, Void, List<CourseModel>> {
        private final CourseDAO courseDAO;

        private GetAsyncTermCourses(CourseDAO courseDAO) {
            this.courseDAO = courseDAO;
        }
        @Override
        protected List<CourseModel> doInBackground(Integer... Integers) {
            return courseDAO.getTermCourses(Integers[0]);
        }
    }

    // Insert Async Tasks
    private static class InsertAsyncTerm extends AsyncTask<TermModel, Void, Void> {
        private final TermDAO termDAO;

        private InsertAsyncTerm(TermDAO termDAO) {
            this.termDAO = termDAO;
        }

        @Override
        protected Void doInBackground(TermModel... termEntities) {
            termDAO.insert(termEntities[0]);
            return null;
        }
    }
    private static class InsertAsyncCourse extends AsyncTask<CourseModel, Void, Void> {
        private final CourseDAO courseDAO;

        private InsertAsyncCourse(CourseDAO courseDAO) {
            this.courseDAO = courseDAO;
        }

        @Override
        protected Void doInBackground(CourseModel... courseEntities) {
            courseDAO.insert(courseEntities[0]);
            return null;
        }
    }
    private static class InsertAsyncAssessment extends AsyncTask<AssessmentModel, Void, Void> {
        private final AssessmentDAO assessmentDAO;

        private InsertAsyncAssessment(AssessmentDAO assessmentDAO) {
            this.assessmentDAO = assessmentDAO;
        }

        @Override
        protected Void doInBackground(AssessmentModel... assessmentEntities) {
            assessmentDAO.insert(assessmentEntities[0]);
            return null;
        }
    }
    private static class InsertAsyncNote extends AsyncTask<NoteModel, Void, Void> {
        private final NoteDAO noteDAO;

        private InsertAsyncNote(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(NoteModel... noteEntities) {
            noteDAO.insert(noteEntities[0]);
            return null;
        }
    }

    // Update Async Tasks
    private static class UpdateAsyncTerm extends AsyncTask<TermModel, Void, Void> {
        private final TermDAO termDAO;

        private UpdateAsyncTerm(TermDAO termDAO) {
            this.termDAO = termDAO;
        }

        @Override
        protected Void doInBackground(TermModel... termEntities) {
            termDAO.update(termEntities[0]);
            return null;
        }
    }

    private static class UpdateAsyncCourse extends AsyncTask<CourseModel, Void, Void> {
        private final CourseDAO courseDAO;

        private UpdateAsyncCourse(CourseDAO courseDAO) {
            this.courseDAO = courseDAO;
        }

        @Override
        protected Void doInBackground(CourseModel... courseEntities) {
            courseDAO.update(courseEntities[0]);
            return null;
        }
    }

    private static class UpdateAsyncAssessment extends AsyncTask<AssessmentModel, Void, Void> {
        private final AssessmentDAO assessmentDAO;

        private UpdateAsyncAssessment(AssessmentDAO assessmentDAO) {
            this.assessmentDAO = assessmentDAO;
        }

        @Override
        protected Void doInBackground(AssessmentModel... assessmentEntities) {
            assessmentDAO.update(assessmentEntities[0]);
            return null;
        }
    }

    private static class UpdateAsyncNote extends AsyncTask<NoteModel, Void, Void> {
        private final NoteDAO noteDAO;

        private UpdateAsyncNote(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(NoteModel... noteEntities) {
            noteDAO.update(noteEntities[0]);
            return null;
        }
    }

    // Delete Async Tasks
    private static class DeleteAsyncTerm extends AsyncTask<TermModel, Void, Void> {
        private final TermDAO termDAO;

        private DeleteAsyncTerm(TermDAO termDAO) {
            this.termDAO = termDAO;
        }

        @Override
        protected Void doInBackground(TermModel... termEntities) {
            termDAO.delete(termEntities[0]);
            return null;
        }
    }

    private static class DeleteAsyncCourse extends AsyncTask<CourseModel, Void, Void> {
        private final CourseDAO courseDAO;

        private DeleteAsyncCourse(CourseDAO courseDAO) {
            this.courseDAO = courseDAO;
        }

        @Override
        protected Void doInBackground(CourseModel... courseEntities) {
            courseDAO.delete(courseEntities[0]);
            return null;
        }
    }

    private static class DeleteAsyncAssessment extends AsyncTask<AssessmentModel, Void, Void> {
        private final AssessmentDAO assessmentDAO;

        private DeleteAsyncAssessment(AssessmentDAO assessmentDAO) {
            this.assessmentDAO = assessmentDAO;
        }

        @Override
        protected Void doInBackground(AssessmentModel... assessmentEntities) {
            assessmentDAO.delete(assessmentEntities[0]);
            return null;
        }
    }

    private static class DeleteAsyncNote extends AsyncTask<NoteModel, Void, Void> {
        private final NoteDAO noteDAO;

        private DeleteAsyncNote(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(NoteModel... noteEntities) {
            noteDAO.delete(noteEntities[0]);
            return null;
        }
    }
}
