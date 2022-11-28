package com.example.proyectotitulofuentestoledo.modelo;

public class RegistroReserva {
    private String userId;
    private String idEstacionamiento;
    private String horaReserva;

    public RegistroReserva(String userId, String idEstacionamiento, String horaReserva) {
        this.userId = userId;
        this.idEstacionamiento = idEstacionamiento;
        this.horaReserva = horaReserva;
    }

    public RegistroReserva(){

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
