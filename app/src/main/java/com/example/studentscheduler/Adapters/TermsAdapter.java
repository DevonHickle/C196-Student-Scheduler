package com.example.studentscheduler.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Models.TermModel;
import com.example.studentscheduler.R;

import java.util.ArrayList;
import java.util.List;

public class TermsAdapter extends RecyclerView.Adapter<TermsAdapter.TermHolder> {
    private List<TermModel> terms = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public TermHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.term_item, parent, false);
        return new TermHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermHolder holder, int position) {
        TermModel currentTerm = terms.get(position);
        holder.textViewTermTitle.setText(currentTerm.getTitle());
        holder.textViewTermStartDate.setText(currentTerm.getStartDate());
        holder.getTextViewTermEndDate.setText(currentTerm.getEndDate());
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setTerms(List<TermModel> terms) {
        this.terms = terms;
        notifyDataSetChanged();
    }

    public TermModel getTermAt(int position) {
        return terms.get(position);
    }

    class TermHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTermTitle;
        private final TextView textViewTermStartDate;
        private final TextView getTextViewTermEndDate;

        public TermHolder(@NonNull View itemView) {
            super(itemView);
            textViewTermTitle = itemView.findViewById(R.id.text_view_term_title);
            textViewTermStartDate = itemView.findViewById(R.id.text_view_term_start_date);
            getTextViewTermEndDate = itemView.findViewById(R.id.text_view_term_end_date);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(terms.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TermModel termEntity);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}