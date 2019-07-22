package ejercicios.Clases;
//en este ejemplo vimos herencia, polimorfismo, sobrecarga de métodos, sobreescritira de métodos, super --> superClase, this --> clase donde me encuentro.
public class RopaPadre {
//variables para toda las clases hijas que las requieran...
    private int id;
    private double precio;
    private String talla;
    private String color;

//creamos nuestro método constructor para inicializar nuestras variables...
  public RopaPadre(int id, double precio, String talla, String color){
    this.id = id;
    this.precio = precio;
    this.talla = talla;
    this.color = color;
  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public void mostrarDatos(String nombreClase){//recibimos como parámetro al nombre de la clase y despues lo imprima
        System.out.println(nombreClase);
        System.out.println("Id :"+id);
        System.out.println("Precio :"+precio);
        System.out.println("Talla :"+talla);
        System.out.println("Color :"+color);
    }

}
