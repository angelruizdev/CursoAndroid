package ejercicios.Clases;

public class PrincipalEjecutable {

    public static void main(String args[]){

//creamos objetos de dichas clases para pasarles los argumentos a los parámetros mediante el método constructor
        PlayeraH playera = new PlayeraH(1,100,"MM","Verde","Fino");
        JeansH jeans = new JeansH(2,200,"HH","Naranja","Basico",'F');
        CalcetinH calcetin = new CalcetinH(2,300,"HM","Rosa");
//pasamos el nombre de la clase como argumento ya que desde la clase padre la recibe como parámetro
        playera.mostrarDatos("***Playera***");
        jeans.mostrarDatos("***Jeans***");
        calcetin.mostrarDatos("***Clcetin***");
    }
}
