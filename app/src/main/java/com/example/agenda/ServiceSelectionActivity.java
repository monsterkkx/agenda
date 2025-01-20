package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ServiceSelectionActivity extends AppCompatActivity {
    private RecyclerView recyclerViewServices;
    private List<Service> serviceList;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_selection);

        recyclerViewServices = findViewById(R.id.recyclerViewServices);
        recyclerViewServices.setLayoutManager(new LinearLayoutManager(this));

        btnContinue = findViewById(R.id.btnContinue);
        serviceList = new ArrayList<>();

        serviceList.add(new Service("nome do serviço", "Descrição", R.drawable.ic_service_icon1));
        serviceList.add(new Service("nome do serviço 2", "Descrição", R.drawable.ic_service_icon2));
        serviceList.add(new Service("nome do serviço 3","Descrição",R.drawable.ic_service_icon1));

        ServiceAdapter adapter = new ServiceAdapter(serviceList, this);
        recyclerViewServices.setAdapter(adapter);

        btnContinue.setOnClickListener(v -> {
            // Filtrar os serviços selecionados
            ArrayList<Service> selectedServices = new ArrayList<>();
            for (Service service : serviceList) {
                if (service.isSelected()) {
                    selectedServices.add(service);
                }
            }

            if (!selectedServices.isEmpty()) {
                // Passar os serviços selecionados para a próxima Activity
                Intent intent = new Intent(this, DateTimeSelectionActivity.class);
                intent.putParcelableArrayListExtra("selectedServices", selectedServices);
                startActivity(intent);
            } else {
                // Exibir mensagem de erro
                Toast.makeText(this, "Selecione pelo menos um serviço!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
