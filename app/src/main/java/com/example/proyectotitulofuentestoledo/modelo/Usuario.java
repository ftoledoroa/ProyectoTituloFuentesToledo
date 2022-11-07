package com.example.proyectotitulofuentestoledo.modelo;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Usuario {

    public String apellido;
    public String nombre;
    public String correo;
    public String rut;
    public String password;
    public Integer metodo_pago;
    public Integer privilegios;
    
public Usuario() {
        //Default contructor required for calls to DataSnapshot.get
    }
    public Usuario(String apellido, String nombre, String correo, String rut, String password, Integer metodo_pago, Integer privilegios) {
        this.apellido=apellido;
        this.nombre=nombre;
        this.correo=correo;
        this.rut=rut;
        this.password=password;
        this.metodoPago=metodoPago;
        this.privilegios=privilegios;
    }

}