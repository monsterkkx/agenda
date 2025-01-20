package com.example.agenda;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText etFullName, etBirthDate, etCPF, etPhone, etEmail, etPassword;
    private FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializando os campos
        etFullName = findViewById(R.id.etFullName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etCPF = findViewById(R.id.etCPF);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        Button btnRegister = findViewById(R.id.btnRegister);

        // Inicializando Firebase
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference("users");

        // Lógica do botão de registro
        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String fullName = etFullName.getText().toString();
        String birthDate = etBirthDate.getText().toString();
        String cpf = etCPF.getText().toString();
        String phone = etPhone.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (fullName.isEmpty() || birthDate.isEmpty() || cpf.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Criando o usuário no Firebase Authentication
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Salvar os dados do usuário no Firebase Realtime Database
                String userId = auth.getCurrentUser().getUid();
                User user = new User(userId, fullName, birthDate, cpf, phone, email);
                database.child(userId).setValue(user).addOnCompleteListener(dbTask -> {
                    if (dbTask.isSuccessful()) {
                        Toast.makeText(this, "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Erro ao salvar dados: " + dbTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Erro no registro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Classe para modelar os dados do usuário
    public static class User {
        public String userId, fullName, birthDate, cpf, phone, email;

        public User() {
        } // Construtor vazio para o Firebase

        public User(String userId, String fullName, String birthDate, String cpf, String phone, String email) {
            this.userId = userId;
            this.fullName = fullName;
            this.birthDate = birthDate;
            this.cpf = cpf;
            this.phone = phone;
            this.email = email;
        }
    }
}
