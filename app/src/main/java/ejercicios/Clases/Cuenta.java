package ejercicios.Clases;

import java.util.ArrayList;

//public class Cuenta{ //Clase anterior continuar despues...
//Hilo
public class Cuenta extends Thread {

   public void run(){

       ArrayList<Integer> eventos = new ArrayList<Integer>();
       eventos.add(0);
       eventos.add(1);
       eventos.add(2);
       System.out.println("Tamaño :"+eventos.size());
       //eventos.remove(0);//elimina al elemento con indice 0
       System.out.println(eventos.contains(3));//devuelve true si en ese indice hay un elemento si no false
       eventos.set(1,5);//sustituye al elemento con indice 1 por el nuevo valor 5
       System.out.printf("Eventos :"+eventos);


       //for (int x=0; x<10; x++){
         //  System.out.println("Lista :"+x);
       //}
   }
/*
    //Ejercicio nativo
    private String nombre;
    private double saldo;

    public Cuenta(String nombre, double saldo) {
        if(nombre.equals("")){
            System.out.println("Nombre incorrecto!");
        }else{
            this.nombre = nombre;
            System.out.println("Nombre :"+getNombre());
        } if(saldo<=0){
            System.out.println("Deposito incorrecto!");
        }else{
            byte base = 100;
            this.saldo = saldo + base;
            System.out.println("Nombre :"+getSaldo());
        }
      }

    public String getNombre() {
        return nombre;
    }
    public double getSaldo() {
        return saldo;
    }
    */
}
