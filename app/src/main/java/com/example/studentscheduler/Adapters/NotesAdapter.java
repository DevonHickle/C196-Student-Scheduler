package com.example.studentscheduler.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Models.NoteModel;
import com.example.studentscheduler.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {
    private List<NoteModel> note = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NotesAdapter.NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_detail, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NoteHolder holder, int position) {
        NoteModel currentNote = note.get(position);
        holder.textViewNoteTitle.setText(currentNote.getName());
        holder.textViewNoteContent.setText(currentNote.getContent());
    }

    @Override
    public int getItemCount() {
        return note.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setNote(List<NoteModel> note) {
        this.note = note;
        notifyDataSetChanged();
    }

    public NoteModel getNotePosition(int position) {
        return note.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNoteTitle;
        private final TextView textViewNoteContent;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewNoteTitle = itemView.findViewById(R.id.text_view_note_title);
            textViewNoteContent = itemView.findViewById(R.id.text_view_note_content);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(note.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(NoteModel noteModel);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
