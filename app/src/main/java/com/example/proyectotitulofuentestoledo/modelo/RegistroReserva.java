package com.example.proyectotitulofuentestoledo.modelo;

public class RegistroReserva {
    private String userId;
    private String idEstacionamiento;
    private String horaReserva;
    private boolean activo;


    public RegistroReserva(String userId, String idEstacionamiento, String horaReserva, boolean activo) {
        this.userId = userId;
        this.idEstacionamiento = idEstacionamiento;
        this.horaReserva = horaReserva;
        this.activo = activo;
    }

    public RegistroReserva(){

    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdEstacionamiento() {
        return idEstacionamiento;
    }

    public void setIdEstacionamiento(String idEstacionamiento) {
        this.idEstacionamiento = idEstacionamiento;
    }

    public String getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }
}
