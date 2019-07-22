package Sebas;

public class Cajero {
    private String nombreClinte;
    private int numeroTrgeta;
    private int numerCuenta;
    private String nip;

    public Cajero(String nombreClinte, int numeroTrgeta, int numerCuenta, String nip) {
        this.nombreClinte = nombreClinte;
        this.numeroTrgeta = numeroTrgeta;
        this.numerCuenta = numerCuenta;
        this.nip = nip;
    }

    public String getNombreClinte() {
        return nombreClinte;
    }

    public void setNombreClinte(String nombreClinte) {
        this.nombreClinte = nombreClinte;
    }

    public int getNumeroTrgeta() {
        return numeroTrgeta;
    }

    public void setNumeroTrgeta(int numeroTrgeta) {
        this.numeroTrgeta = numeroTrgeta;
    }

    public int getNumerCuenta() {
        return numerCuenta;
    }

    public void setNumerCuenta(int numerCuenta) {
        this.numerCuenta = numerCuenta;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
