package ejercicios.Clases;
import java.util.Scanner;

public class ClaseA {
    public static void main(String args[]){
        Scanner entrada = new Scanner(System.in);
        System.out.printf("ingresa tu nombre :");
        String nombre = entrada.next();
        //podemos crear n objetos para recibir distintos argumento utilizando las mismas variables
        ClaseB verDatos = new ClaseB(nombre,27,80f);//en este objeto recibimos el argumento nombre desde teclado
        ClaseB verDato2 = new ClaseB("samuel",90,100f);//objetos para constructor que recibe parametros
        verDatos.setEdad(27);//también podemos recibir la edad como parametro, mediante elmétodo set
        verDatos.verDatosUsiario();
        verDato2.verDatosUsiario();
        ClaseB verDatos1 = new ClaseB();//creamos obj para constructor que no recibe parametros
        verDatos1.verDatosUsiario();//mediante este obj invocamos al método que imprime los resultados
    }
}
