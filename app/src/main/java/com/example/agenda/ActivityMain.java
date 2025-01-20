package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class ActivityMain extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnLogin).setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
        findViewById(R.id.btnRegister).setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
        findViewById(R.id.btnAccessWithoutLogin).setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));

    }

}