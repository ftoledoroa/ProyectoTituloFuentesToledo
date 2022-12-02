package com.example.proyectotitulofuentestoledo.modelo;

public class BoletaFinal {
    private String fecha;
    private String horaReseva;
    private String horaIngreso;
    private String horaSalida;
    private String userId;


    public BoletaFinal () {}

    public BoletaFinal(String fecha, String horaReseva, String horaIngreso, String horaSalida, String userId) {
        this.fecha = fecha;
        this.horaReseva = horaReseva;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.userId = userId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
