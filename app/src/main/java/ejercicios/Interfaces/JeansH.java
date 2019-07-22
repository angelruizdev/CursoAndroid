package ejercicios.Interfaces;
//implemetamos la interface con su método
public class JeansH extends RopaPadre implements IDevolucion{
    /* de igual forma también
    private String id;
    private double precio;
    private String talla;
    private String color;*/
    private String corte;
    private char genero;
//de igual forma heredamos el metodo constructor de la clase padre, este solo trae 4 parametros aquí le agregamos dos nuevos y los inicializamos(corte y genero)
    public JeansH(int id, double precio, String talla, String color, String corte, char genero) {
        super(id, precio, talla, color);
        this.corte = corte;
        this.genero = genero;
    }

    /*
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
    */
    public String getCorte() {
        return corte;
    }

    public void setCorte(String corte) {
        this.corte = corte;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }
//accedemos al método mostrarDatos()de la clase padre para mostrar los datos genero y corte que no heredan desde la clase padre
    @Override
    public void mostrarDatos(String nombreClase) {
        super.mostrarDatos(nombreClase);
        System.out.println("Corte :" +corte);
        System.out.println("Genero:" +genero);
    }

    @Override
    public void hacerDevolucion() {
        System.out.println("Devolución de unos Jeans");
        }
    /*Lo eliminamos ya que viene ya desde la clase padre...
    public void mostrarDatos(){
        System.out.println("id:" +id);
        System.out.println("precio:" +precio);
        System.out.println("talla:" +talla);
        System.out.println("color:" +color);
        System.out.println("corte:" +corte);
        System.out.println("corte:" +genero);
      }
*/
}
