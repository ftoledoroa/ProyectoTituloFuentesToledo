package com.example.proyectotitulofuentestoledo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PantallaBienvenida extends AppCompatActivity {

    Button rlEscanear;
    Button btCheckIn;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String horaIngreso;
    private String horaSalida;
    private String horaReserva;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore mDB = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_bienvenida);
        calendar = Calendar.getInstance();



        RelativeLayout rlEscanear = findViewById(R.id.rlEscanear);
        rlEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PantallaBienvenida.this,Camara.class);
                startActivity(i);

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

        rlEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PantallaBienvenida.this,Camara.class);
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


    }


}