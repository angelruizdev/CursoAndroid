package ejercicios.Clases;

public class Usuario{
    public static int precio = 10;
    public static void main(String args[]){

    //Hilo
        Cuenta miHilo = new Cuenta();
        miHilo.start();
/*

        //Ejercicio nativo libro
        Scanner ent=new Scanner(System.in);

        System.out.println("Nombre :");
        String nombre = ent.nextLine();
        System.out.println("Saldo :");
        double saldo = ent.nextDouble();
        Cuenta op1=new Cuenta(nombre,saldo);
        //System.out.println("Nombre :"+op1.getNombre());
        //System.out.println("Saldo :"+op1.getSaldo());
        */

    }
}
