package com.example.agenda.ui.settings;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatDelegate;
import com.example.agenda.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    public SettingsFragment() {
        // Construtor público vazio necessário
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Usa o ViewBinding para inflar o layout e obter a referência dos componentes
        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        // Configura o estado inicial do switch de forma segura com postDelayed
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setInitialTheme();
            }
        }, 100); // Adiciona um pequeno atraso para garantir que a UI foi completamente criada

        // Configura o listener para alternar entre modo claro e escuro
        binding.switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveThemePreference(true);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    saveThemePreference(false);
                }
            }
        });

        return binding.getRoot();  // Retorna a raiz do layout inflado
    }

    private void setInitialTheme() {
        // Verifica se o modo escuro está ativado e configura o switch
        boolean isDarkMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
        binding.switchTheme.setChecked(isDarkMode);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Libere a referência ao binding para evitar memory leaks
        binding = null;
    }

    private void saveThemePreference(boolean isDarkMode) {
        // Salva o estado do tema em SharedPreferences
        getActivity().getSharedPreferences("settings", getContext().MODE_PRIVATE)
                .edit()
                .putBoolean("dark_mode", isDarkMode)
                .apply();
    }
}
