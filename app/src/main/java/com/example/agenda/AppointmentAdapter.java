package com.example.agenda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder>{
    private  final List<Appointment> appointments;

    public AppointmentAdapter(List<Appointment> appointments){
        this.appointments = appointments;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Appointment appointment = appointments.get(position);
        holder.tvServiceName.setText(appointment.getServiceName());
        holder.tvDate.setText(appointment.getDate());
        holder.tvTime.setText(appointment.getTime());
    }

    @Override
    public int getItemCount(){
        return appointments.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvServiceName, tvDate, tvTime;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
