package com.example.agenda;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity  extends AppCompatActivity{
    private EditText etFullname, etBirthDate, etCPF, etPhone, etEmail;
    private Button bntUpdate;
    private DatabaseReference database;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etFullname = findViewById(R.id.etFullName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etCPF = findViewById(R.id.etCPF);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        bntUpdate = findViewById(R.id.bntUpdate);

        auth = FirebaseAuth.getInstance();
        String userId = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance().getReference("users").child(userId);

        loadUserProfile();

        bntUpdate.setOnClickListener(v -> updateUserProfile());

    }
private void loadUserProfile(){
  database.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
          if (snapshot.exists()) {
              etFullname.setText(snapshot.child("fullName").getValue(String.class));
              etBirthDate.setText(snapshot.child("birthDate").getValue(String.class));
              etCPF.setText(snapshot.child("cpf").getValue(String.class));
              etPhone.setText(snapshot.child("phone").getValue(String.class));
              etEmail.setText(snapshot.child("email").getValue(String.class));

          } else {
              Toast.makeText(ProfileActivity.this, "Erro ao carregar perfil", Toast.LENGTH_SHORT).show();
          }
              }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(ProfileActivity.this, "Erro: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        }
  });
}
    private void updateUserProfile(){
        String fullName = etFullname.getText().toString();
        String birthDate = etBirthDate.getText().toString();
        String cpf = etCPF.getText().toString();
        String phone = etPhone.getText().toString();

        if (fullName.isEmpty() || birthDate.isEmpty() || cpf.isEmpty() || phone.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }
    database.child("fullName").setValue(fullName);
    database.child("birthDate").setValue(birthDate);
    database.child("cpf").setValue(cpf);
    database.child("phone").setValue(phone).addOnCompleteListener(task -> {
        if (task.isSuccessful()){
            Toast.makeText(ProfileActivity.this, "Perfil atualizado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ProfileActivity.this, "Erro ao atualizar perfil", Toast.LENGTH_SHORT).show();
        }
    });

    }

}


