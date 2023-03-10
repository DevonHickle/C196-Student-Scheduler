package com.example.studentscheduler.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Activities.AddEdit.AddEditTerms;
import com.example.studentscheduler.Adapters.TermsAdapter;
import com.example.studentscheduler.Models.TermModel;
import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModels.CourseVM;
import com.example.studentscheduler.ViewModels.TermVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.ExecutionException;

public class TermListActivity extends AppCompatActivity {
    public static final int ADD_TERM_REQ = 1;
    private TermVM termVM;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_list);

        FloatingActionButton buttonAddTerm = findViewById(R.id.btn_add_term);
        buttonAddTerm.setOnClickListener(view -> {
            Intent intent = new Intent(TermListActivity.this, AddEditTerms.class);
            startActivityForResult(intent, ADD_TERM_REQ);
        });

        RecyclerView mRecyclerView = findViewById(R.id.termsListRecyclerView);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        final TermsAdapter mTermsAdapter = new TermsAdapter();
        mRecyclerView.setAdapter(mTermsAdapter);
        mTermsAdapter.notifyDataSetChanged();

        termVM = new ViewModelProvider(this).get(TermVM.class);
        termVM.getAllTerms().observe(this, mTermsAdapter::setTerms);

        CourseVM courseVM = new ViewModelProvider(this).get(CourseVM.class);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                TermModel deletedTerm = mTermsAdapter.getTermAt(viewHolder.getBindingAdapterPosition());

                int relatedCourse = 0;
                try {
                    relatedCourse = courseVM.getTermCourses(deletedTerm.getId()).size();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

                if (relatedCourse > 0) {
                    Toast.makeText(TermListActivity.this, "Courses are still attached to this term, cannot delete term", Toast.LENGTH_SHORT).show();
                } else {
                    termVM.delete(deletedTerm);
                    Toast.makeText(TermListActivity.this, "Term successfully deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mTermsAdapter.setOnItemClickListener(termEntity -> {
            Intent intent = new Intent(TermListActivity.this, TermDetailActivity.class);
            intent.putExtra(TermDetailActivity.EXTRA_ID, termEntity.getId());
            intent.putExtra(TermDetailActivity.EXTRA_TITLE, termEntity.getTitle());
            intent.putExtra(TermDetailActivity.EXTRA_START_DATE, termEntity.getStartDate());
            intent.putExtra(TermDetailActivity.EXTRA_END_DATE, termEntity.getEndDate());
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode,intent);
        if(requestCode == ADD_TERM_REQ && resultCode == RESULT_OK) {
            assert intent != null;
            String title = intent.getStringExtra(AddEditTerms.EXTRA_TERM_TITLE);
            String startDate = intent.getStringExtra(AddEditTerms.EXTRA_TERM_START_DATE);
            String endDate = intent.getStringExtra(AddEditTerms.EXTRA_TERM_END_DATE);

            TermModel termModel = new TermModel(title, startDate, endDate);
            termVM.insert(termModel);

            Toast.makeText(this, "Term added!", Toast.LENGTH_SHORT).show();
        } Toast.makeText(this, "Unable to add term", Toast.LENGTH_SHORT).show();
    }
}
