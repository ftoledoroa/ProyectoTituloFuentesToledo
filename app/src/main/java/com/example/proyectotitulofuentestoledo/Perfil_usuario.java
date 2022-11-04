package com.example.proyectotitulofuentestoledo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.firestore.FirebaseFirestore;
// Access a Cloud Firestore instance from your Activity

public class Perfil_usuario extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
    }
    FirebaseFirestore DB = FirebaseFirestore.getInstance();
}