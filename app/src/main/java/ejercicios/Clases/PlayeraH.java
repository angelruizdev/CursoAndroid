package ejercicios.Clases;

public class PlayeraH extends RopaPadre{
    /*elminamos estos atributos ya que son repetidos y los pasaremos desde la clase padre
    private String id; //junto con sus métodos get y set
    private double precio;
    private String talla;
    private String color;
    */
    private String corte;
//sobreescribimos al método contructor, añadiendole el corte, ya que no lo trae desde la clase padre
    public PlayeraH(int id, double precio, String talla, String color, String corte) {
        super(id, precio, talla, color);//super nos indica que este método viene osea es heredado de la clase padre
        this.corte = corte;//al comensar a modificar metiendole nuevo codigo a esté método, ya estamos haciendo polimorfismo
    }
    /*este es nuestro método constructor normal
    public PlayeraH(int id, double precio, String talla, String color) {
        super(id, precio, talla, color);
    }*/

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
//heredamos el método de la clase padre que imprime los valores

    @Override
//el método esta sobreescrito ya que pertenece a la clase padre y lo modificamos con nuevos valores
    public void mostrarDatos(String nombreClase) {
        super.mostrarDatos(nombreClase);//con super nos indica que este método proviene de la clase padre
        System.out.println("Corte :" +corte);//imprimimos la variable que no biene desde la clase padre, ya que si no la pasamos al método solo imprimira los que trae desde la clase padre
    }

/*//Lo eliminamos ya que viene ya desde la clase padre...
    public void mostrarDatos(){
        System.out.println("id:" +id);
        System.out.println("precio:" +precio);
        System.out.println("talla:" +talla);
        System.out.println("color:" +color);
        System.out.println("corte:" +corte);
      }
*/

}
