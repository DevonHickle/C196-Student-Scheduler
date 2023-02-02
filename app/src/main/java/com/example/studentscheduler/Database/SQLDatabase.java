package com.example.studentscheduler.Database;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.studentscheduler.Views.CourseDetailActivity;
import com.example.studentscheduler.Models.AssessmentModel;
import com.example.studentscheduler.Models.CourseModel;
import com.example.studentscheduler.Models.NoteModel;
import com.example.studentscheduler.Models.TermModel;

@Database(version = 1, entities = {
        TermModel.class,
        CourseModel.class,
        NoteModel.class,
        AssessmentModel.class
})

public abstract class SQLDatabase extends RoomDatabase {
    private static SQLDatabase instance;

    public abstract CourseDAO courseDAO();
    public abstract TermDAO termDAO();
    public abstract NoteDAO noteDAO();
    public abstract AssessmentDAO assessmentDAO();

    public static synchronized  SQLDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SQLDatabase.class, "database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callBack)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback callBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase dataBase) {
            super.onCreate(dataBase);
            new PopulateDataBaseAsyncTask(instance).execute();
        }
    };

    private static class PopulateDataBaseAsyncTask extends AsyncTask<Void, Void, Void> {
        private final TermDAO termDAO;
        private final CourseDAO courseDAO;
        private final NoteDAO noteDAO;
        private final AssessmentDAO assessmentDAO;

        @SuppressWarnings("deprecation")
        public PopulateDataBaseAsyncTask(SQLDatabase sqlDatabase) {
            termDAO = sqlDatabase.termDAO();
            courseDAO = sqlDatabase.courseDAO();
            noteDAO = sqlDatabase.noteDAO();
            assessmentDAO = sqlDatabase.assessmentDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            termDAO.insert(new TermModel("Term 1", "07/01/2020", "12/30/2020"));
            termDAO.insert(new TermModel("Term 2", "01/01/2021", "06/30/2021"));

            courseDAO.insert(new CourseModel(1, "Course 1", "07/01/2020", "12/30/2020",
                        true, CourseDetailActivity.STATUS_IN_PROGRESS, "Devon", "123-456-7890", "devon@test.com"));
            courseDAO.insert(new CourseModel(2, "Course 2", "07/01/2020", "12/30/2020",
                        false, CourseDetailActivity.STATUS_COMPLETED, "Devon", "123-456-7890", "devon@test.com"));

            assessmentDAO.insert(new AssessmentModel(1, "test 1", 0, "01/25/2020", true));
            assessmentDAO.insert(new AssessmentModel(2, "Test 2", 0, "02/12/2020", true));

            noteDAO.insert(new NoteModel(1, "Notes for course 1", "Test content"));
            noteDAO.insert(new NoteModel(2, "Notes for Course 2", "Test contents"));
            return null;
        }
    }
}
