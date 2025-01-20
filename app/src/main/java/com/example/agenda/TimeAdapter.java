package com.example.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {

    private List<String> times;
    private OnTimeClickListener listener;
    private int selectedPosition = -1; // Para rastrear o horário selecionado

    public interface OnTimeClickListener {
        void onTimeSelected(String time);
    }

    public TimeAdapter(List<String> times, OnTimeClickListener listener) {
        this.times = times;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String time = times.get(position);
        holder.timeTextView.setText(time);

        // Alterar a cor para o horário selecionado
        holder.itemView.setBackgroundColor(
                position == selectedPosition ? holder.itemView.getContext().getColor(android.R.color.holo_blue_light)
                        : holder.itemView.getContext().getColor(android.R.color.transparent)
        );

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position;
            listener.onTimeSelected(time);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
        }
    }
}
