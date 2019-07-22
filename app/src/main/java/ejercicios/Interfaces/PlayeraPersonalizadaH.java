package ejercicios.Interfaces;
//esta clase no admite devoluciones por lo tanto no implementamos la interfaz ni su método
public class PlayeraPersonalizadaH extends RopaPadre{

    private String playeraPersinalizada;
//esté método constructor inicializa las variables de la clase padre y asu ves aplica polimorfismo al agregar un nuevo parametro al costructor(String playeraPersinalizada)
    public PlayeraPersonalizadaH(int id, double precio, String talla, String color, String playeraPersinalizada) {
        super(id, precio, talla, color);
        this.playeraPersinalizada = playeraPersinalizada;
    }
//creamos sus métodos get y set de la nueva variable que no hereda de la clase padre
    public String getPlayeraPersinalizada() {
        return playeraPersinalizada;
    }

    public void setPlayeraPersinalizada(String playeraPersinalizada) {
        this.playeraPersinalizada = playeraPersinalizada;
    }
//accedemos al método mostrarDatosTelefono de la clase padre, para mostar la playeraPersinalizada que no hereda de la clase padre
    @Override
    public void mostrarDatos(String nombreClase) {
        super.mostrarDatos(nombreClase);
        System.out.println(playeraPersinalizada);
    }
}
