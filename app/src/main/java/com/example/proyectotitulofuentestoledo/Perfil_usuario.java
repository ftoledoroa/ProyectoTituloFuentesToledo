package com.example.proyectotitulofuentestoledo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectotitulofuentestoledo.modelo.Usuario;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class Perfil_usuario extends AppCompatActivity {
    private FirebaseAuth mAuth;
    // Access a Cloud Firestore instance from your Activity

    private FirebaseFirestore mDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        FirebaseApp.initializeApp(this); //iniciar uso de firebase
        this.mAuth = FirebaseAuth.getInstance();
        this.mDB = FirebaseFirestore.getInstance();
        TextView tvUser = findViewById(R.id.tvUser);
        tvUser.setText(this.mAuth.getCurrentUser().getEmail());

}};