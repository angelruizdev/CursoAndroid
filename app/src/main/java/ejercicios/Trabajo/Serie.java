package ejercicios.Trabajo;

public class Serie extends CinemaP implements IVisualizable{

    private int numTemporadas = 1;
    public Serie(String titulo, String genero, String creador, int anio, double duracion, int numTemporadas) {
        super(titulo, genero, creador, anio, duracion);
        this.numTemporadas = numTemporadas;
    }
    public int getNumTemporadas() {
        return numTemporadas;
    }
    public void setNumTemporadas(byte numTemporadas) {
        this.numTemporadas = numTemporadas;
    }

    @Override
    public void marcarVisto() {
    visto = true;
    System.out.println("Número de temporadas :"+numTemporadas);
    }

    @Override
    public boolean esVisto() {
        System.out.println("Visto de forma constantemente");
        return false;
    }

    @Override
    public Float tiempoVisto() {
        getDuracion();
        return null;
    }
}
