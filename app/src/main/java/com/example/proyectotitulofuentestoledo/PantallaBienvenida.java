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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        cargarIdRegistro();

        rlEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(PantallaBienvenida.this).initiateScan();
                /*Intent i = new Intent(PantallaBienvenida.this,Camara.class);
                startActivity(i);*/
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



        if(captura.equals("CheckIn")){
            dateFormat = new SimpleDateFormat("HH:mm:ss");
            dateFormatFecha = new SimpleDateFormat("dd-MM-yyyy");
            horaIngreso = dateFormat.format(calendar.getTime());
            fecha = dateFormatFecha.format(calendar.getTime());
            String userId = mAuth.getCurrentUser().getUid();
            Boleta boleta = new Boleta(userId, horaReserva, horaIngreso, fecha, horaSalida);
            /* if compararIdquetengo con todos los id de la bdd
            extraigo dato de horareserva
            else
            no encontre el id que me paso
            todo lo anterior en un for
            que va a iterar sobre el firebase

            mDB.collection("registro_reserva").whereEqualTo("userId",
                    userId).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    List<DocumentSnapshot> lista = queryDocumentSnapshots.getDocuments();
                    for(int i=0; i< lista.size(); i++){
                        RegistroReserva r = lista.get(i).toObject(RegistroReserva.class);
                        assert r != null;
                        horaReserva = r.getHoraReserva(); //cambiar para hacer funcionar
                    }
                }
            });*/
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

        if(captura.equals("CheckOut")){
            dateFormat = new SimpleDateFormat("HH:mm:ss");
            horaSalida = dateFormat.format(calendar.getTime());
            String userId = mAuth.getCurrentUser().getUid();
            Boleta boleta = new Boleta(userId, horaReserva, horaIngreso, fecha, horaSalida);
            String idTemporal = cargarIdRegistro();

            //Log.d("PRUEBA","--->" + idBoleta);


            //Toast.makeText(PantallaBienvenida.this, idRegistro, Toast.LENGTH_SHORT).show();

            mDB.collection("registro_uso").document(idTemporal).update("horaSalida", horaSalida).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(PantallaBienvenida.this, "CheckOut Correcto", Toast.LENGTH_SHORT).show();
                }
            });
            Intent i = new Intent(PantallaBienvenida.this, DetalleBoleta.class);
            startActivity(i);
        }

    }
    private String cargarIdRegistro() {
        SharedPreferences preferences=getSharedPreferences("temporal", Context.MODE_PRIVATE);
        String idTemporal = preferences.getString("idBoleta","no existe informacion");
        Log.w("IDBOLETA","--->"+ idTemporal);

        return idTemporal;
    }

    private void guardarIdRegistro() {
        SharedPreferences preferences = getSharedPreferences("temporal", Context.MODE_PRIVATE );
        String idBoleta =  idRegistro;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("idBoleta",idBoleta);
        Log.w("IDBOLETA","--->"+ idBoleta);
        editor.commit();
    }


}


/*Button btCheckIn = findViewById(R.id.btCheckIn);
        btCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                horaIngreso = dateFormat.format(calendar.getTime());
                String userId = mAuth.getCurrentUser().getUid();

                Boleta boleta = new Boleta(userId, horaReserva, horaIngreso, horaSalida);

                mDB.collection("registro_reserva").whereEqualTo("userId",
                        userId).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() { //para consultar una información especifica y acer un mach
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> lista = queryDocumentSnapshots.getDocuments();
                            for(int i=0; i< lista.size(); i++){
                                RegistroReserva r = lista.get(i).toObject(RegistroReserva.class);
                                assert r != null;
                                horaReserva = r.getHoraReserva(); //cambiar para hacer funcionar
                        }
                    }
                });


                mDB.collection("registro_uso")
                        .add(boleta)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(String.valueOf(PantallaBienvenida.this), "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(PantallaBienvenida.this, "Nuevo Registro añadido", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(String.valueOf(PantallaBienvenida.this), "Error adding document", e);
                                Toast.makeText(PantallaBienvenida.this, "Error al añadir registro", Toast.LENGTH_SHORT).show();
                            }
                        });

            }});*/

