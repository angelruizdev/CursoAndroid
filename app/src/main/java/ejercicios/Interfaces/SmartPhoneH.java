package ejercicios.Interfaces;
//implemetamos la interface con su método
public class SmartPhoneH extends TelefonoPadre implements IDevolucion{

    private String targetaSim;

    public SmartPhoneH(int id, double precio, String marca, String modelo, String targetaSim) {
    super(id, precio, marca, modelo);
    this.targetaSim = targetaSim;
}
    public String getTargetaSim() {
        return targetaSim;
    }

    public void setTargetaSim(String targetaSim) {
        this.targetaSim = targetaSim;
    }
//accedemos al método mostrarDatosTelefono de la clase padre, para mostar la targetaSim que no hereda de la clase padre
    @Override
    public void mostrarDatosTelefono(String tipoTelefono) {
        super.mostrarDatosTelefono(tipoTelefono);
        System.out.println(targetaSim);
    }

//como vemos se está sobreescribiendo el método hacerDevolucion(),ya que viene(existe) desde la interfas
    @Override
    public void hacerDevolucion() {
        System.out.println("Devolución de un SmartPhone");
        }
}
