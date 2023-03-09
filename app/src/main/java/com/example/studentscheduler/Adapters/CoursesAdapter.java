package com.example.studentscheduler.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Models.CourseModel;
import com.example.studentscheduler.R;
import com.example.studentscheduler.Activities.CourseDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {
    private List<CourseModel> courses = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_course, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseModel currentCourse = courses.get(position);
        holder.courseTitle.setText(currentCourse.getTitle());
        holder.courseEndDate.setText(currentCourse.getEndDate());
        holder.courseStatus.setText(CourseDetailActivity.getStatus(currentCourse.getStatus()));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCourses(List<CourseModel> courses) {
        this.courses = courses; notifyDataSetChanged();
    }

    public CourseModel getCourses(int position) {
        return courses.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseTitle;
        private final TextView courseEndDate;
        private final TextView courseStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.course_title);
            courseEndDate = itemView.findViewById(R.id.course_end_date);
            courseStatus = itemView.findViewById(R.id.course_status);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(courses.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CourseModel courseModel);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
