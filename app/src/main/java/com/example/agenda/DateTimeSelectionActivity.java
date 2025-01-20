package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class DateTimeSelectionActivity extends AppCompatActivity {
    private String selectedDate;
    private String selectedTime;
    private RecyclerView recyclerViewTimes;
    private Button bntConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_selection);

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
        });

        recyclerViewTimes = findViewById(R.id.recyclerViewTimes);
        recyclerViewTimes.setLayoutManager(new GridLayoutManager(this, 3));

        List<String> times = Arrays.asList("08:00", "09:00", "10:00", "11:00", "14:00", "15:00", "16:00");
        TimeAdapter adapter = new TimeAdapter(times, this::onTimeSelected);
        recyclerViewTimes.setAdapter(adapter);

        bntConfirm = findViewById(R.id.btnConfirm);
        bntConfirm.setOnClickListener(v -> {
        if (selectedDate == null || selectedTime == null){
            Toast.makeText(this, "Por favor, selecione uma data e um Horario", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(DateTimeSelectionActivity.this, SummaryActivity.class);
            intent.putParcelableArrayListExtra("selectedServices", getIntent().getParcelableArrayListExtra("selectedServices"));
            intent.putExtra("selectedDate", selectedDate);
            intent.putExtra("selectedTime", selectedTime);
            startActivity(intent);
        }
        });
    }

    private void onTimeSelected(String time) {
        selectedTime = time;
        Toast.makeText(this, "Horário selecionado: " + time, Toast.LENGTH_SHORT).show();
    }
}
