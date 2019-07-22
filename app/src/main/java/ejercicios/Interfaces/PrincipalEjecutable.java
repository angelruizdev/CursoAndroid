package ejercicios.Interfaces;

public class PrincipalEjecutable {

    public static void main(String args[]){

//(RopaPadre)creamos objetos de dichas clases para pasarles los argumentos a los parámetros mediante el método constructor
        PlayeraH playera = new PlayeraH(1,100,"MM","Verde","Fino");
        JeansH jeans = new JeansH(2,200,"HH","Naranja","Basico",'F');
        CalcetinH calcetin = new CalcetinH(3,300,"HM","Rosa");
        PlayeraPersonalizadaH pyPersonal = new PlayeraPersonalizadaH(4,7000,"CC","Rojo","Viva México");
//pasamos el nombre de la clase como argumento ya que desde la clase padre la recibe como parámetro
        playera.mostrarDatos("***Playera***++");
        playera.hacerDevolucion();
        jeans.mostrarDatos("***Jeans***");
        calcetin.mostrarDatos("***Clcetin***");
        pyPersonal.mostrarDatos("***Mi playera personalizada***");//no acepta devoluciones
//(TelefonoPadre)creamos objetos de dichas clases para pasarles los argumentos a los parámetros mediante el método constructor
        SmartPhoneH smPhone = new SmartPhoneH(1,500,"Samsung","E-3","Telcel");
        TelefonoHogarH tfHogar = new TelefonoHogarH(2,1000,"Motorola","G-6",false);
//pasamos el nombre de la clase como argumento ya que desde la clase padre la recibe como parámetro
        smPhone.mostrarDatosTelefono("***SmartPhone***");
        smPhone.hacerDevolucion();
        tfHogar.mostrarDatosTelefono("***Teléfono local***");
    }
}
