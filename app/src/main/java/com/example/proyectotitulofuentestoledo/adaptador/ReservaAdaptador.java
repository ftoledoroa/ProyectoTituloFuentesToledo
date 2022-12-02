package com.example.proyectotitulofuentestoledo.adaptador;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectotitulofuentestoledo.R;
import com.example.proyectotitulofuentestoledo.modelo.Estacionamiento;
import com.example.proyectotitulofuentestoledo.modelo.RegistroReserva;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReservaAdaptador extends FirestoreRecyclerAdapter<Estacionamiento, ReservaAdaptador.ViewHolder> {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore mDB = FirebaseFirestore.getInstance();
    Activity activity;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String horaReserva;
    public String reserva;
    public String idReserva;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView numero, status, fecha;
        ImageView selectEstacionamiento;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            numero= itemView.findViewById(R.id.tvNumero);
            status = itemView.findViewById(R.id.tvStatus);
            //fecha = itemView.findViewById(R.id.fecha);
            selectEstacionamiento = itemView.findViewById(R.id.ivCheck);

        }
    }

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ReservaAdaptador(@NonNull FirestoreRecyclerOptions<Estacionamiento> options, Activity activity) {
        super(options);
        this.activity = activity;
    }
//CREA LOS ELEMENTOS A MOSTRAR EN EL RECICLERVIEW
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Estacionamiento estacionamiento) {
        DocumentSnapshot dSnapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id = dSnapshot.getId();
        String idEstacionamiento = id;
        calendar = Calendar.getInstance();
        holder.numero.setText(estacionamiento.getNumero());
        holder.status.setText(estacionamiento.getStatus());
        //Log.d("prueba",estacionamiento.getFecha());
        //holder.fecha.setText(estacionamiento.getFecha());
        holder.selectEstacionamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {selectEstacionamiento(id,idEstacionamiento);}
        });

    }
//CREAR NUEVO REGISTRO DE RESERVA
    private void selectEstacionamiento(String id, String idEstacionamiento) {
        dateFormat = new SimpleDateFormat("HH:mm:ss");
        horaReserva = dateFormat.format(calendar.getTime());
        String userId = mAuth.getCurrentUser().getUid();
        //ACA SE CAMBIA LA DISPONIBILIDAD DEL ESTACIONAMIENTO
        mDB.collection("estacionamientos").document(id).update("status", "No Disponible").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Reservado Correctamente!, Te esperamos en el local.", Toast.LENGTH_SHORT).show();
                RegistroReserva registroReserva = new RegistroReserva(userId, idEstacionamiento, horaReserva, true);

                //ESTA OPERACION CAMBIA EL ESTADO DE LAS RESERVAS ANTERIORES A FALSE
                mDB.collection("registro_reserva").whereEqualTo("userId", userId)
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String idReserva = document.getId();
                                        //POR CADA DOCUMENTO ENCONTRADO ACTUALIZA EL ESTADO
                                        mDB.collection("registro_reserva").document(idReserva).update("activo", false)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Log.w("DOCUMENTO ACTUALIZADO", "---->" + idReserva);
                                                    }
                                                });
                                    }
                                    //CREA NUEVO REGISTRO DE RESERVA
                                    mDB.collection("registro_reserva").add(registroReserva).
                                            addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    reserva = documentReference.getId();
                                                    Log.w(String.valueOf(ReservaAdaptador.this), "DocumentSnapshot added with ID: " + documentReference.getId());
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w(String.valueOf(ReservaAdaptador.this), "Error adding document", e);
                                                }
                                            });
                                }
                            }
                        });

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, "Error.", Toast.LENGTH_SHORT).show();
                    }
        });

    }


    @NonNull
    @Override
    public ReservaAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservar_adaptador, parent, false);
        return new ViewHolder(view);
    }








    /*public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked

        switch(view.getId()) {
            case R.id.:
                if (checked)
                // Put some meat on the sandwich
            else
                // Remove the meat
                break;
            case R.id.checkbox_cheese:
                if (checked)
                // Cheese me
            else
                // I'm lactose intolerant
                break;
            // TODO: Veggie sandwich
        }
    }*/
// se necesita al activar el checkbox crear un nuevo registro en la BD con la hora  y que el estado del estacionamiento cambie a no disponible.
    // ademas se necesita asociar al usuario con el estacionamiento reservado.
//para actualizar el estado revisar para seleccionar el estado



}
