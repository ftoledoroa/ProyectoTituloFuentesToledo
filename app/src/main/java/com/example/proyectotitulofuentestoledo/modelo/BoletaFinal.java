package com.example.proyectotitulofuentestoledo.modelo;

public class BoletaFinal {
    private String fecha;
    private String horaReseva;
    private String horaIngreso;
    private String horaSalida;
    private String tiempoUso;
    private String totalPago;





    public BoletaFinal(String horaReseva, String horaIngreso, String fecha, String horaSalida, String tiempoUso, String totalPago) {
        this.horaReseva = horaReseva;
        this.horaIngreso = horaIngreso;
        this.fecha = fecha;
        this.horaSalida = horaSalida;
        this.tiempoUso = tiempoUso;
        this.totalPago = totalPago;

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

    public String getTiempoUso() {
        return tiempoUso;
    }

    public void setTiempoUso(String tiempoUso) {
        this.tiempoUso = tiempoUso;
    }

    public String getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(String totalPago) {
        this.totalPago = totalPago;
    }
}
