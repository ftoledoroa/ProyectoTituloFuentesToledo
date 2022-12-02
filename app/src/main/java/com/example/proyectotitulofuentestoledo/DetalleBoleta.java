package com.example.proyectotitulofuentestoledo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectotitulofuentestoledo.modelo.Boleta;
import com.example.proyectotitulofuentestoledo.modelo.BoletaFinal;
import com.example.proyectotitulofuentestoledo.modelo.RegistroReserva;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class DetalleBoleta extends AppCompatActivity {

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat dateFormatFecha;
    private String fecha;
    private String horaIngreso;
    private String horaSalida;
    private String horaReserva;
    private String tiempoUso;
    private String totalPago;

    public String idRegistro;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore mDB = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_boleta);
        Button btPagar = findViewById(R.id.btPagar);
        calendar = Calendar.getInstance();
       // cargarIdRegistro();
        String userId = mAuth.getCurrentUser().getUid();
        String idTemporal = String.valueOf(mAuth.getCurrentUser());

        DocumentReference docRef = mDB.collection("registro_uso").document(idTemporal);docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                BoletaFinal boleta = documentSnapshot.toObject(BoletaFinal.class);
                Log.w("DETALLE BOLETA", String.valueOf(boleta));

            }});


            }
        };


/*
        TextView totalapagar = (TextView) findViewById(R.id.tvPrecio);
        totalapagar.setText(DetalleBoleta.funcionCalcularTotal("entrada", "salida"));

        public static String funcionCalcularTotal(String horaIngreso, String horaSalida);{
            Boleta boleta = new Boleta(userId, horaReserva, horaIngreso, fecha, horaSalida, tiempoUso, totalPago);
            String totalAPagar = "10000";


            //extraigo la hora de entrada y salida
            //Convierto ambos a un date
            //hago un between entre ambos
            //cargi en la variable totalAPagar el resultado de between

            return totalAPagar;

        }

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

    private String cargarIdRegistro() {
        SharedPreferences preferences=getSharedPreferences("temporal", Context.MODE_PRIVATE);
        String idTemporal = preferences.getString("idBoleta","no existe informacion");
        Log.w("IDBOLETA","--->"+ idTemporal);

        return idTemporal;
    }*/





         /*       mDB.collection("registro_uso").whereEqualTo("userId",
                        userId).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
@Override
public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
        Boleta boleta = new Boleta(userId, horaReserva, horaIngreso, fecha, horaSalida);
        List<DocumentSnapshot> lista = queryDocumentSnapshots.getDocuments();
        for(int i=0; i< lista.size(); i++){
        RegistroReserva registro = lista.get(i).toObject(RegistroReserva.class);
        assert registro != null;
        horaReserva = registro.getHoraReserva();
        Log.w("REGISTRO RESERVA","---->"+ horaReserva);//cambiar para hacer funcionar
        }
        }
        });*/
