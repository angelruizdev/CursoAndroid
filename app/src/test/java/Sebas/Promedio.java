package Sebas;

public class Promedio {

    private String nombre;
    private int semestre;

    public Promedio(String nombre, int semestre) {
        this.nombre = nombre;
        this.semestre = semestre;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = nombre;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
}
