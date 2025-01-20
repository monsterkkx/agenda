package com.example.agenda;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agenda.ui.settings.SettingsFragment; // Certifique-se de usar o pacote correto

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); // Altere para o layout da Activity

        // Carrega o SettingsFragment na Activity
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container2, new SettingsFragment())
                    .commit();
        }
    }
}
