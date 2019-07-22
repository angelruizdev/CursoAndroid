package ejercicios.Interfaces;
//implemetamos la interface con su método
public class TelefonoHogarH extends TelefonoPadre implements IDevolucion{

    private boolean alanbrico;

    public TelefonoHogarH(int id, double precio, String marca, String modelo, boolean alanbrico) {
        super(id, precio, marca, modelo);
        this.alanbrico = alanbrico;
    }
    public boolean getAlanbrico() {
        return alanbrico;
    }

    public void setAlanbrico(boolean alanbrico) {
        this.alanbrico = alanbrico;
    }
//accedemos al método mostrarDatosTelefono de la clase padre, para mostar la alambrico/inalambrico que no hereda de la clase padre
    @Override
    public void mostrarDatosTelefono(String tipoTelefono) {
        super.mostrarDatosTelefono(tipoTelefono);
        System.out.println(alanbrico);
    }

    @Override
    public void hacerDevolucion() {
        System.out.println("Devolución de un Teléfono de casa");
    }
}
