package com.example.proyectotitulofuentestoledo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectotitulofuentestoledo.modelo.Boleta;
import com.example.proyectotitulofuentestoledo.modelo.RegistroReserva;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class PantallaBienvenida extends AppCompatActivity {
    Button btCheckIn;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat dateFormatFecha;
    private String fecha;
    private String horaIngreso;
    private String horaSalida;
    private String horaReserva;
    public String idRegistro;
    public String idReserva;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore mDB = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_bienvenida);
        calendar = Calendar.getInstance();
        RelativeLayout rlEscanear = findViewById(R.id.rlEscanear);
        RelativeLayout rlReservar = findViewById(R.id.rlReserva);
        Button btAdmin = (Button) findViewById(R.id.btAdministrador);
        //cargarIdRegistro();
        //cargarHoraReserva();
        TextView tvTitulo = findViewById(R.id.tvTitulo);
        tvTitulo.setText(this.mAuth.getCurrentUser().getEmail());

        rlEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(PantallaBienvenida.this).initiateScan();
            }
        });

        rlReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PantallaBienvenida.this,ActivityReservar.class);
                startActivity(i);
            }
        });

        btAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PantallaBienvenida.this, ActivityReservarAdmin.class);
                startActivity(i);
            }});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        calendar = Calendar.getInstance();

        TextView tvResultadoCamara = findViewById(R.id.tvResultadoCamara);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String datos = result.getContents();
        tvResultadoCamara.setText(datos);
        String captura = tvResultadoCamara.getText().toString();
        //CAPTURAR EL CHECKIN
        if(captura.equals("CheckIn")){
            dateFormat = new SimpleDateFormat("HH:mm:ss");
            dateFormatFecha = new SimpleDateFormat("dd-MM-yyyy");
            horaIngreso = dateFormat.format(calendar.getTime());
            fecha = dateFormatFecha.format(calendar.getTime());
            String userId = mAuth.getCurrentUser().getUid();

            tvResultadoCamara.setText("");

            mDB.collection("registro_reserva").whereEqualTo("userId",
                    userId).whereEqualTo("activo",
                    true).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    List<DocumentSnapshot> lista = queryDocumentSnapshots.getDocuments();
                    for(int i=0; i< lista.size(); i++){
                        RegistroReserva registro = lista.get(i).toObject(RegistroReserva.class);
                        assert registro != null;
                        horaReserva = registro.getHoraReserva();
                        guardarHoraReserva();
                        String horaTemporal = cargarHoraReserva();
                        Log.w("REGISTRO RESERVA","---->"+ horaTemporal);//preguntar como obtener la ultima hora
                        //preguntar por como hacer diferencia de horas en minutos
                    }

                    //CREAR NUEVO REGISTRO DE USO EN LA BASE DE DATOS
                    String horaTemporal = cargarHoraReserva();
                    Boleta boleta = new Boleta(userId, horaTemporal, horaIngreso, fecha, "");
                    mDB.collection("registro_uso")
                            .add(boleta)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    //Log.d(this, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    idRegistro = documentReference.getId();
                                    guardarIdRegistro();
                                    Toast.makeText(PantallaBienvenida.this, "CheckIn Correcto", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(String.valueOf(PantallaBienvenida.this), "Error adding document", e);
                                    Toast.makeText(PantallaBienvenida.this, "Error al añadir registro", Toast.LENGTH_SHORT).show();
                                }
                            });

                    Intent i = new Intent(PantallaBienvenida.this, PantallaBienvenida.class);
                    startActivity(i);
                }
            });

        }

        if(captura.equals("CheckOut")){
            dateFormat = new SimpleDateFormat("HH:mm:ss");
            horaSalida = dateFormat.format(calendar.getTime());
            String idTemporal = cargarIdRegistro();
            tvResultadoCamara.setText("");

            mDB.collection("registro_uso").document(idTemporal).update("horaSalida", horaSalida).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(PantallaBienvenida.this, "CheckOut Correcto", Toast.LENGTH_SHORT).show();
                }

            });
            Intent i = new Intent(PantallaBienvenida.this, DetalleBoleta.class);
            startActivity(i);
        }
        onRestart();

    }
    private String cargarIdRegistro() {
        SharedPreferences preferences=getSharedPreferences("temporal", Context.MODE_PRIVATE);
        String idTemporal = preferences.getString("idBoleta","no existe informacion");
        Log.w("cargarIdRegistro","--->"+ idTemporal);
        return idTemporal;
    }

    private void guardarIdRegistro() {
        SharedPreferences preferences = getSharedPreferences("temporal", Context.MODE_PRIVATE );
        String idBoleta =  idRegistro;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("idBoleta",idBoleta);
        Log.w("guardarIdRegistro","--->"+ idBoleta);
        editor.commit();
    }
    private String cargarHoraReserva() {
        SharedPreferences preferences=getSharedPreferences("temporal", Context.MODE_PRIVATE);
        String horaTemporal = preferences.getString("horaTemporal","Sin Reserva");
        Log.w("cargarHoraReserva","--->"+ horaTemporal);
        return horaTemporal;
    }
    private void guardarHoraReserva() {
        SharedPreferences preferences = getSharedPreferences("temporal", Context.MODE_PRIVATE );
        String horaTemporal =  horaReserva;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("horaTemporal",horaTemporal);
        Log.w("guardarHoraReserva","--->"+ horaTemporal);
        editor.commit();
    }

}
