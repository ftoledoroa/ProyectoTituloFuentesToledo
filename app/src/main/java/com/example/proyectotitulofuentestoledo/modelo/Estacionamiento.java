package com.example.proyectotitulofuentestoledo.modelo;
import com.google.firebase.firestore.IgnoreExtraProperties;
@IgnoreExtraProperties

public class Estacionamiento {
    private String numero;
    private String status;
    private Boolean isChecked;

    public Estacionamiento() {
    }

    public Estacionamiento(String numero, String status, Boolean isChecked) {
        this.numero = numero;
        this.status = status;
        this.isChecked = isChecked;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}

