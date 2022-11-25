package com.example.proyectotitulofuentestoledo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaBienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_bienvenida);

        RelativeLayout rlEscanear = findViewById(R.id.rlEscanear);
        rlEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PantallaBienvenida.this,Camara.class);
                startActivity(i);
                Toast.makeText(PantallaBienvenida.this, "Acá ase añadirá la funcion de Camara", Toast.LENGTH_SHORT).show();

            }
        });


        RelativeLayout rlReservar = findViewById(R.id.rlReserva);
        rlReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PantallaBienvenida.this,ActivityReservar.class);
                startActivity(i);

            }
        });

        Button btAdmin = (Button) findViewById(R.id.btAdministrador);

        btAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PantallaBienvenida.this, ActivityReservarAdmin.class);
                startActivity(i);
            }});
    }
}