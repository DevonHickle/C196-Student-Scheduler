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

import com.example.studentscheduler.Adapters.TermsAdapter;
import com.example.studentscheduler.Entities.TermEntity;
import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModel.CourseVM;
import com.example.studentscheduler.ViewModel.TermVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.ExecutionException;

public class TermListActivity extends AppCompatActivity {
    public static final int ADD_TERM_REQ = 1;

    private TermVM termVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_list);

        FloatingActionButton buttonAddTerm = findViewById(R.id.btn_add_term);
        buttonAddTerm.setOnClickListener(v -> {
            Intent intent = new Intent(TermListActivity.this, AddEditTermActivity.class);
            startActivityForResult(intent, ADD_TERM_REQ);
        });

        RecyclerView recyclerView = findViewById(R.id.termListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TermsAdapter adapter = new TermsAdapter();
        recyclerView.setAdapter(adapter);

        termVM = new ViewModelProvider(this).get(TermVM.class);
        termVM.getAllTerms().observe(this, adapter::setTerms);

        CourseVM courseVM = new ViewModelProvider(this).get(CourseVM.class);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @SuppressLint("NotifyDataSetChanged")
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                TermEntity deletedTerm = adapter.getTermAt(viewHolder.getAdapterPosition());

                int relatedCourse = 0;
                try {
                    relatedCourse = courseVM.getTermCourses(deletedTerm.getId()).size();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

                if(relatedCourse > 0) {
                    Toast.makeText(TermListActivity.this, "Courses are still attached to this term, cannot delete term", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                } else {
                    termVM.delete(deletedTerm);
                    Toast.makeText(TermListActivity.this, "Term successfully deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(termEntity -> {
            Intent intent = new Intent(TermListActivity.this, TermDetailActivity.class);
            intent.putExtra(TermDetailActivity.EXTRA_ID, termEntity.getId());
            intent.putExtra(TermDetailActivity.EXTRA_TITLE, termEntity.getTitle());
            intent.putExtra(TermDetailActivity.EXTRA_START_DATE, termEntity.getStartDate());
            intent.putExtra(TermDetailActivity.EXTRA_END_DATE, termEntity.getEndDate());
            startActivity(intent);
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_TERM_REQ && resultCode == RESULT_OK) {
            assert data != null;
            String title = data.getStringExtra(AddEditTermActivity.EXTRA_TERM_TITLE);
            String startDate = data.getStringExtra(AddEditTermActivity.EXTRA_TERM_START_DATE);
            String endDate = data.getStringExtra(AddEditTermActivity.EXTRA_TERM_END_DATE);

            TermEntity termEntity = new TermEntity(title, startDate, endDate);
            termVM.insert(termEntity);

            Toast.makeText(this, "Term added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Unable to add term", Toast.LENGTH_SHORT).show();
        }
    }
}
