package ejercicios.Interfaces;
//implemetamos la interface con su método
public class CalcetinH extends RopaPadre implements IDevolucion{
    //aquí solo heredamos elmétodo constructor ya que solo requiere los mismos atributos de la clase padre
    public CalcetinH(int id, double precio, String talla, String color) {
        super(id, precio, talla, color);
    }
    //aquí es opsional sobreescribir el métod,(ponerlo o no)ya que contiene los mismos valores
    // que la clase padre y ya lo esta heredando, ya lo tiene aunque no se vea si no lo pusieramos
    @Override
    public void mostrarDatos(String nombreClase) {
        super.mostrarDatos(nombreClase);
    }

    @Override
    public void hacerDevolucion() {
        System.out.println("Devolución de unos Clcetines");

    }
    /*Los eliminamos ya que vienen  desde la clase padre...
    private String id;
    private double precio;
    private String talla;
    private String color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void mostrarDatos(){
        System.out.println("id:" +id);
        System.out.println("precio:" +precio);
        System.out.println("talla:" +talla);
        System.out.println("color:" +color);

    }
*/
}
