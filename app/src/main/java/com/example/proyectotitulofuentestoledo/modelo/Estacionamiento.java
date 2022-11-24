package com.example.proyectotitulofuentestoledo.modelo;
import com.google.firebase.firestore.IgnoreExtraProperties;
@IgnoreExtraProperties

public class Estacionamiento {
    private String id;
    private String status;
    private String fecha;
    private Boolean isChecked;

    public Estacionamiento() {
    }

    public Estacionamiento(String id, String status, String fecha, Boolean isChecked) {
        this.id = id;
        this.status = status;
        this.fecha = fecha;
        this.isChecked = isChecked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}

