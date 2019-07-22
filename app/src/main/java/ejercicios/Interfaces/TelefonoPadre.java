package ejercicios.Interfaces;

public class TelefonoPadre {
//creamos nuestras variables
    private int id;
    private double precio;
    private String marca;
    private String modelo;

//creamos nuestro constructor parainicializar nuestras variables
    public TelefonoPadre(int id, double precio, String marca, String modelo) {
        this.id = id;
        this.precio = precio;
        this.marca = marca;
        this.modelo = modelo;
    }

//creamos sus métodos get y set de cada una
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

    public String getMara() {
        return marca;
    }

    public void setMara(String mara) {
        this.marca = mara;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void mostrarDatosTelefono(String tipoTelefono){
    System.out.println(tipoTelefono);
    System.out.println("id :"+id);
    System.out.println("precio :"+precio);
    System.out.println("marca :"+marca);
    System.out.println("modelo :"+modelo);
}

}
