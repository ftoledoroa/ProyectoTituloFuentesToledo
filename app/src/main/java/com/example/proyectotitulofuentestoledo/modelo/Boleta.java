package com.example.proyectotitulofuentestoledo.modelo;

public class Boleta {
    private String userId;
    private String horaReseva;
    private String horaIngreso;
    private String fecha;
    private String horaSalida;

    public Boleta(String userId, String horaReseva, String horaIngreso,String fecha, String horaSalida) {
        this.userId = userId;
        this.horaReseva= horaReseva;
        this.horaIngreso = horaIngreso;
        this.fecha = fecha;
        this.horaSalida = horaSalida;
    }
    public Boleta() {
        //Default contructor required for calls to DataSnapshot.get
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHoraReseva() {
        return horaReseva;
    }

    public void setHoraReseva(String horaReseva) {
        this.horaReseva = horaReseva;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }
}
