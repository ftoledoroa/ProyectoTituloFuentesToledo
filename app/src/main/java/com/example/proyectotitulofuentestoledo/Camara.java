package com.example.proyectotitulofuentestoledo;

import android.app.Dialog;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Camara extends AppCompatActivity {

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String horaIngreso;
    private String horaSalida;
    private String horaReserva;
    public String idRegistro;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore mDB = FirebaseFirestore.getInstance();
    Dialog mDialog; }
    /*

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
            //Boleta boleta = new Boleta(userId, horaReserva, horaIngreso, fecha, horaSalida);
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
            });
            mDB.collection("registro_uso")
                    .add(boleta)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            //Log.d(Camara.this, "DocumentSnapshot added with ID: " + documentReference.getId());
                            idRegistro = documentReference.getId();
                            Toast.makeText(Camara.this, idRegistro, Toast.LENGTH_SHORT).show();
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
        if(captura.equals("CheckOut")){
            dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            horaSalida = dateFormat.format(calendar.getTime());
            String userId = mAuth.getCurrentUser().getUid();
            Boleta boleta = new Boleta(userId, horaReserva, horaIngreso, horaSalida);

            Toast.makeText(Camara.this, idRegistro, Toast.LENGTH_SHORT).show();

            /*mDB.collection("registro_uso").document(idRegistro).update("horaSalida", horaSalida).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    /*mDialog = new Dialog(Camara.this);
                    Log.d("CHECKOUT", "success");
                    mDialog.setContentView(R.layout.popup_checkout);
                    Toast.makeText(Camara.this, "pOBANDO", Toast.LENGTH_SHORT).show();*/





        //between entre los tiempos















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


