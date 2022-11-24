package com.example.proyectotitulofuentestoledo.adaptador;


import android.app.Activity;
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
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ReservaAdaptadorAdmin extends FirestoreRecyclerAdapter<Estacionamiento, ReservaAdaptadorAdmin.ViewHolder> {

    FirebaseFirestore mDB = FirebaseFirestore.getInstance();
    Activity activity;


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ReservaAdaptadorAdmin(@NonNull FirestoreRecyclerOptions<Estacionamiento> options, Activity activity) {
        super(options);
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Estacionamiento estacionamiento) {
        DocumentSnapshot dSnapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id = dSnapshot.getId();
        holder.idEst.setText(estacionamiento.getId());
        holder.status.setText(estacionamiento.getStatus());
        //Log.d("prueba",estacionamiento.getFecha());
        holder.fecha.setText(estacionamiento.getFecha());

        holder.botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {deleteEstacionamiento(id);
            }
        });
    }

    //para actualizar el estado revisar
    private void deleteEstacionamiento(String id) {
        mDB.collection("estacionamientos").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "eliminado correctamente!.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Error.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @NonNull
    @Override
    public ReservaAdaptadorAdmin.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservar_adaptador, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idEst, status, fecha;
        ImageView botonEliminar;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            idEst= itemView.findViewById(R.id.tvId);
            status = itemView.findViewById(R.id.tvStatus);
            fecha = itemView.findViewById(R.id.fecha);
            botonEliminar = itemView.findViewById(R.id.btDelete);
        }
    }

}
