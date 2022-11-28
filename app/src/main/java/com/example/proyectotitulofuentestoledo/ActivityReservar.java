package com.example.proyectotitulofuentestoledo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectotitulofuentestoledo.adaptador.ReservaAdaptador;
import com.example.proyectotitulofuentestoledo.modelo.Estacionamiento;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ActivityReservar extends AppCompatActivity {

    FirebaseAuth mAuth;
    RecyclerView recyclerView;
    ReservaAdaptador rAdaptador;
    FirebaseFirestore mDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);
        mAuth = FirebaseAuth.getInstance();
        mDB = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.reciclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Query query = mDB.collection(("estacionamientos"));

        FirestoreRecyclerOptions<Estacionamiento> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Estacionamiento>().setQuery(query, Estacionamiento.class).build();

        rAdaptador = new ReservaAdaptador(firestoreRecyclerOptions, this);
        rAdaptador.notifyDataSetChanged();
        recyclerView.setAdapter(rAdaptador);

    }

    @Override
    protected void onStart() {
        super.onStart();
        rAdaptador.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        rAdaptador.stopListening();
    }
}