package com.example.proyectotitulofuentestoledo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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


public class Camara extends AppCompatActivity {

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
        setContentView(R.layout.activity_camara);


        new IntentIntegrator(this).initiateScan();


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView resultado = findViewById(R.id.tvResultado);
        calendar = Calendar.getInstance();
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        String datos = result.getContents();
        resultado.setText(datos);

        String captura = resultado.getText().toString();

        if(captura.equals("CheckIn")){
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
                            Log.d(String.valueOf(Camara.this), "DocumentSnapshot added with ID: " + documentReference.getId());
                            Toast.makeText(Camara.this, "Nuevo Registro añadido", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(String.valueOf(Camara.this), "Error adding document", e);
                            Toast.makeText(Camara.this, "Error al añadir registro", Toast.LENGTH_SHORT).show();
                        }
                    });
            Intent i = new Intent(Camara.this, PantallaBienvenida.class);
            startActivity(i);
        }
        /*if(captura.equals("CheckOut")){
            dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            horaIngreso = dateFormat.format(calendar.getTime());

            mDB.collection("registro_uso").document(id).update("status", "No Disponible").addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(activity, "Reservado Correctamente!, Te esperamos en el local.", Toast.LENGTH_SHORT).show();
        }*/


    }



}

    /*Button btnCamara;
    ImageView visor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        btnCamara = findViewById(R.id.btnCamara);
        visor = findViewById(R.id.iv_visor);

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camara();
            }
        });
    }
    // abre o activa la camara
    private void camara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,1);
        }
    }
 //este bloque captura la imagen
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            visor.setImageBitmap(imgBitmap);

        }
    }
}*/


