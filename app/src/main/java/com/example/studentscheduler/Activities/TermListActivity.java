package com.example.studentscheduler.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Adapters.TermsAdapter;
import com.example.studentscheduler.R;
import com.example.studentscheduler.ViewModel.CourseVM;
import com.example.studentscheduler.ViewModel.TermVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        //Add item touch helper, on swiped, and click listeners
    }
}
