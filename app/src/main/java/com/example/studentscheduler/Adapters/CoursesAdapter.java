package com.example.studentscheduler.Adapters;

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

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseHolder> {
    private List<CourseModel> courses = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_course, parent, false);
        return new CourseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.CourseHolder holder, int position) {
        CourseModel currentCourse = courses.get(position);
        holder.courseTitle.setText(currentCourse.getTitle());
        holder.courseEndDate.setText(currentCourse.getEndDate());
        holder.courseStatus.setText(CourseDetailActivity.getStatus(currentCourse.getStatus()));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }

    public CourseModel getCourses(int position) {
        return courses.get(position);
    }

    class CourseHolder extends RecyclerView.ViewHolder {
        private final TextView courseTitle;
        private final TextView courseEndDate;
        private final TextView courseStatus;

        public CourseHolder(@NonNull View itemView) {
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
