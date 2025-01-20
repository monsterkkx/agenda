package com.example.agenda;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView textSummary = findViewById(R.id.textSummary);
        EditText etObservation = findViewById(R.id.etObservation);
        Button btnConfirm = findViewById(R.id.btnConfirm);

        // Recuperar dados da Intent
        ArrayList<Service> selectedServices = getIntent().getParcelableArrayListExtra("selectedServices");
        String selectedDate = getIntent().getStringExtra("selectedDate");
        String selectedTime = getIntent().getStringExtra("selectedTime");

        // Montar o resumo
        StringBuilder summary = new StringBuilder();
        summary.append("Serviços selecionados:\n");
        if (selectedServices != null) {
            for (Service service : selectedServices) {
                summary.append("- ").append(service.getName()).append("\n");
            }
        }
        summary.append("\nData: ").append(selectedDate)
                .append("\nHorário: ").append(selectedTime);

        // Exibir o resumo
        textSummary.setText(summary.toString());

        // Botão de confirmação
        btnConfirm.setOnClickListener(v -> {
            // Adicionar observação ao resumo
            String observation = etObservation.getText().toString().trim();
            if (!observation.isEmpty()) {
                summary.append("\n\nObservação:\n").append(observation);
            }

            // Enviar o resumo completo no WhatsApp
            openWhatsAppWeb(summary.toString());
        });
    }
    private void openWhatsAppWeb(String message) {
        try {
            // Codifica a mensagem para ser compatível com a URL
            String encodedMessage = Uri.encode(message);
            String whatsappWebUrl = "https://wa.me/+55011911014885?text=" + encodedMessage;

            // Cria um Intent para abrir o navegador
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(whatsappWebUrl));
            startActivity(browserIntent);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro ao abrir o navegador.", Toast.LENGTH_SHORT).show();
        }
    }

}
