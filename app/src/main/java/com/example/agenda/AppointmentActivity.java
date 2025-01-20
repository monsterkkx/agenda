package com.example.agenda;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentActivity extends AppCompatActivity {
    private RecyclerView recyclerViewUpcoming, recyclerViewComplete;
    private AppointmentAdapter adapterUpcoming, adapterComplete;
    private List<Appointment> completeAppointments = new ArrayList<>();
    private List<Appointment> upcomingAppointments = new ArrayList<>();
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        recyclerViewUpcoming = findViewById(R.id.recyclerViewUpcoming);
        recyclerViewComplete = findViewById(R.id.recyclerViewCompleted);

        recyclerViewUpcoming.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewComplete.setLayoutManager(new LinearLayoutManager(this));

        adapterUpcoming = new AppointmentAdapter(upcomingAppointments);
        adapterComplete = new AppointmentAdapter(completeAppointments);

        recyclerViewUpcoming.setAdapter(adapterUpcoming);
        recyclerViewComplete.setAdapter(adapterComplete);

        database = FirebaseDatabase.getInstance().getReference("appointments").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        loadAppointments();

    }

    private void loadAppointments(){
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                upcomingAppointments.clear();
                completeAppointments.clear();
                String currentDateTime = new SimpleDateFormat("dd/mm/yyyy HH:mm").format(new Date());

                for (DataSnapshot ds : snapshot.getChildren()) {
                    Appointment appointment = ds.getValue(Appointment.class);
                    if (appointment != null) {
                        String appointmentDateTime = appointment.getDate() + "" + appointment.getTime();
                        if (appointmentDateTime.compareTo(currentDateTime) > 0) {
                            upcomingAppointments.add(appointment);
                        } else {
                            completeAppointments.add(appointment);
                        }
                    }

                }

                adapterUpcoming.notifyDataSetChanged();
                adapterComplete.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle erro

            }
        });

    }
}
