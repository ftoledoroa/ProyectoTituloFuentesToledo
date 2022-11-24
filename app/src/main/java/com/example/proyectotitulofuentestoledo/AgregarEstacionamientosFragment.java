package com.example.proyectotitulofuentestoledo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarEstacionamientosFragment extends DialogFragment {
    FirebaseFirestore mDB;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.agregar_estacionamientos_fragment, container, false);

        mDB = FirebaseFirestore.getInstance();

        EditText numero = (EditText) v.findViewById(R.id.idEstacionamiento);
        EditText status = (EditText) v.findViewById(R.id.etEstado);
        EditText fecha = (EditText) v.findViewById(R.id.fecha);
        Button agregar = (Button) v.findViewById(R.id.btAgregar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeroE = numero.getText().toString();
                String statusE = status.getText().toString();
                String fechaG = fecha.getText().toString();

                if(numeroE.isEmpty() || statusE.isEmpty() || fechaG.isEmpty()){
                    Toast.makeText(getContext(), "Por favor, ingresa información del estacionamiento.", Toast.LENGTH_SHORT).show();
                }else{
                    agregarEstacionamiento(numeroE, statusE, fechaG);
                }
            }
        });

        return v;
    }

    public void agregarEstacionamiento(String idEstacionamiento, String status, String fecha){
        Map<String, Object> map = new HashMap<>();
        map.put("id", idEstacionamiento);
        map.put("status", status);
        map.put("fecha", fecha);

        mDB.collection("estacionamientos").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(), "Estacionamiento agregado correctamente!.", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al agregar estacionamiento.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}