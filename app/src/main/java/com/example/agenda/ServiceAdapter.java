package com.example.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    private List<Service> services;
    private Context context;

    public ServiceAdapter(List<Service> services, Context context) {
        this.services = services;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Service service = services.get(position);
        holder.nameTextView.setText(service.getName());
        holder.descriptionTextView.setText(service.getDescription());
        holder.checkBox.setChecked(service.isSelected());

        // Atualizar o estado de seleção ao clicar no CheckBox
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            service.setSelected(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, descriptionTextView;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.serviceName);
            descriptionTextView = itemView.findViewById(R.id.service_description);
            checkBox = itemView.findViewById(R.id.serviceCheckBox); // Novo CheckBox no layout
        }
    }
}
