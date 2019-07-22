package ejercicios.Trabajo;

public class Pelicula extends CinemaP implements IVisualizable{

    public Pelicula(String titulo, String genero, String creador, int anio, double duracion) {
        super(titulo, genero, creador, anio, duracion);
    }
    @Override
    public void marcarVisto() {
        visto = true;
        System.out.println("Visto :"+visto);
    }
    @Override
    public boolean esVisto() {
        System.out.println("Visto de forma regular");
        return false;
    }
    @Override
    public Float tiempoVisto() {
        getDuracion();
        return null;
    }
}
