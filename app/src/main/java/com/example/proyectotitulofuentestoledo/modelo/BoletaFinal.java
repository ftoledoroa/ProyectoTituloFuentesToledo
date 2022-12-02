package com.example.proyectotitulofuentestoledo.modelo;

public class BoletaFinal {
    private String fecha;
    private String horaReseva;
    private String horaIngreso;
    private static String horaSalida;
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

    public static String getHoraReserva() {

        return getHoraReserva();
    }

    public static String getFecha() {
        return getFecha();
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

    public static String getHoraIngreso() {
        return getHoraIngreso();
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public static String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public static String getTiempoUso() {
        return getTiempoUso();
    }

    public void setTiempoUso(String tiempoUso) {
        this.tiempoUso = tiempoUso;
    }

    public static String getTotalPago() {
        return getTotalPago();
    }

    public void setTotalPago(String totalPago) {
        this.totalPago = totalPago;
    }

    @Override
    public String toString() {
        return "BoletaFinal: \n" +
                "  id=" + fecha + "\n" +
                "  horaReserva=" + horaReseva + "\n" +
                "  horaIngreso=" + horaIngreso + "\n" +
                "  horaSalida=" + horaSalida + "\n" +
                "  tiempoUso=" + tiempoUso + "\n" +
                "  totalPago=" + totalPago + "\n";
    }
}
