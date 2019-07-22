package ejercicios.Trabajo;

public class CinemaP {

    private String titulo,genero,creador;
    private int anio;
    private double duracion;
    public boolean visto;

    public CinemaP(String titulo, String genero, String creador, int anio, double duracion) {
        this.titulo = titulo;
        this.genero = genero;
        this.creador = creador;
        this.anio = anio;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(Float duracion) {
        this.duracion = duracion;
    }
public void verDetalles(){
    System.out.println("Titulo :"+titulo);
    System.out.println("Genero :"+genero);
    System.out.println("Creador :"+creador);
    System.out.println("Duracion :"+duracion);
}
    public void todo(){
        int x=5;
        System.out.println("Más vista :"+titulo);
        System.out.println("Más reciente :"+genero);
        System.out.println("Temporadas :"+x);
        System.out.println("Duracion :"+duracion);
    }


}
