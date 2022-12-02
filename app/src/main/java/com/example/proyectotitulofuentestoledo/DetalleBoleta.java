package com.example.proyectotitulofuentestoledo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleBoleta extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_boleta);
        Button btPagar = findViewById(R.id.btPagar);

        TextView totalapagar = (TextView) findViewById(R.id.tvPrecio);
        totalapagar.setText(funcionCalcularTotal("entrada", "salida"));

        btPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetalleBoleta.this, "Muchas Gracias, pago registrado", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(DetalleBoleta.this, PantallaBienvenida.class);
                startActivity(i);
            }});



    }

    public static String funcionCalcularTotal(String horaentrada, String horasalida){
            String totalAPagar = "10000";

            //extraigo la hora de entrada y salida
            //Convierto ambos a un date
            //hago un between entre ambos
            //cargi en la variable totalAPagar el resultado de between

        return totalAPagar;

    }
}