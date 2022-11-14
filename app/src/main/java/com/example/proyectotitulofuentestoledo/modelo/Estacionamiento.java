package com.example.proyectotitulofuentestoledo.modelo;

import com.google.firebase.firestore.IgnoreExtraProperties;
@IgnoreExtraProperties

public class Estacionamiento {

    public String disponibilidad;
    public String numero;


    public void Estacionamiento() {
        //Default contructor required for calls to DataSnapshot.get
    }

    public Estacionamiento(String disponibilidad, String numero) {
        this.disponibilidad=disponibilidad;
        this.numero=numero;
    }

}
