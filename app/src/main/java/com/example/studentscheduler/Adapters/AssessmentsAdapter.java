package com.example.studentscheduler.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Models.AssessmentModel;
import com.example.studentscheduler.R;

import java.util.ArrayList;
import java.util.List;

public class AssessmentsAdapter extends RecyclerView.Adapter<AssessmentsAdapter.AssessmentHolder> {
    private List<AssessmentModel> assessments = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public AssessmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_assessment, parent, false);
        return new AssessmentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentsAdapter.AssessmentHolder holder, int position) {
        AssessmentModel currentAssessment = assessments.get(position);
        holder.assessmentTitle.setText(currentAssessment.getName());
        holder.assessmentGoalDate.setText(currentAssessment.getGoalDate());
    }

    @Override
    public int getItemCount() {
        return assessments.size();
    }

    public AssessmentModel getAssessments(int position) {
        return assessments.get(position);
    }

    public void setAssessments(List<AssessmentModel> assessments) {
        this.assessments = assessments;
    }

    class AssessmentHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentTitle;
        private final TextView assessmentGoalDate;

        public AssessmentHolder(@NonNull View itemView) {
            super(itemView);
            assessmentTitle = itemView.findViewById(R.id.assessment_title);
            assessmentGoalDate = itemView.findViewById(R.id.assessment_goal_date);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(assessments.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(AssessmentModel assessmentModel);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
