package com.example.proyectotitulofuentestoledo.modelo;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Usuario {

    public String apellido;
    public String nombre;
    public String correo;
    public String rut;
    public Integer metodoPago;
    public Integer privilegios;

    public Usuario() {
        //Default contructor required for calls to DataSnapshot.get
    }

    public Usuario(String apellido, String nombre, String correo, String rut, Integer metodoPago, Integer privilegios) {
        this.apellido=apellido;
        this.nombre=nombre;
        this.correo=correo;
        this.rut=rut;
        this.metodoPago=metodoPago;
        this.privilegios=privilegios;
    }

}