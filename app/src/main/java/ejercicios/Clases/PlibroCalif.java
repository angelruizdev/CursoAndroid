package ejercicios.Clases;

import java.util.Scanner;

public class PlibroCalif {
int _edad =0;
String $nombre ="a";
final int PI_NUMERO = 1;

    public static void main(String args[]){
        Scanner en=new Scanner(System.in);
        LibroCalif libro = new LibroCalif();
        PlibroCalif libro1=new PlibroCalif();

        System.out.printf("Your name :");
        String nombre = en.next();
        System.out.printf("Your age :");
        byte edad = Byte.parseByte(String.valueOf(en.nextByte()));
        libro.setNombreDelCurso(nombre);//invocando al método con parametro desde teclado.
        //libro.setNombreDelCurso("angel");invocando al método con parametro inicial.
        System.out.println("His name is :"+libro.getNombreDelCurso());
        //libro.mostrarMensaje();
        libro.setEdad1(edad);//invocando al método con parametro desde teclado.
        //libro.setEdad1(27);invocando al método con parametro inicial.
        System.out.printf("The age is :"+libro.getEdad1());

/*

        System.out.printf("Number one :");
        int x = Integer.parseInt(en.next());
        System.out.printf("Number two :");
        int y = Integer.parseInt(en.next());


       // System.out.println("Hola, "+libro.nom);


        //int ver=libro.ingresarMonto(x,y);
        //System.out.print("Total :"+ver);
        //Ó
        //System.out.print("Total :"+libro.ingresarMonto(x,y));
*/
    }
}
