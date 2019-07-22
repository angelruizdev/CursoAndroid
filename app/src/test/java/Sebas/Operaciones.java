package Sebas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Operaciones {
 public static void main(String [] args) throws IOException {
     ArrayList<Integer> saldaso = new ArrayList<>();
     String nombreC = "";
     int numeroT = 0;
     int numeroC = 0;
     String nip = "";
     int saldo = 3000;
     int saldoNuevo;
     int sumaSaldos = 3000;
     boolean selecOper = false;
     int cont = 0;
     cont++;

     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

         System.out.println("*****Banamex*****");
         System.out.println("Ingrese su nombre: ");
         nombreC = br.readLine();
         String otraOpsion;
         do {
         System.out.println("¡Hola, "+nombreC+ " Bienvenido a tu cajero automatico!"
                 +"\n"+ "¿Qué operación deceas realizar?" +"\n"+ "presiona el número del Menú."
                 +"\n"+ "1 - Consultar saldo" +"\n"+ "2 - Deposita a tu cuenta" +"\n"+ "3 - Retirar de tu cuenta");

         int opcion = Integer.parseInt(br.readLine());

         switch(opcion){
             case 1:
                 System.out.println("Hola, "+nombreC+ ", Tu saldo es de: $"+ sumaSaldos + " pesos"+"\n");
                 break;
                 case 2:

                     selecOper = true;
                     System.out.println("Ingrese su NIP a 4 digitos: ");
                     nip = br.readLine();

                     while (nip.length() != 4){
                         System.out.println("NIP incorrecto debe tener 4 digitos: ");
                         nip = br.readLine();
                     }
                         System.out.println("Numero de tarjeta: ");
                         numeroT = Integer.parseInt(br.readLine());
                         System.out.println("Numero de cuenta: ");
                         numeroC = Integer.parseInt(br.readLine());

                         System.out.println("Ingresa el monto a depositar mayor a 0 pesos: ");
                         saldoNuevo = Integer.parseInt(br.readLine());

                         while (saldoNuevo <= 0){
                             System.out.println("Ingresa el monto a depositar mayor a 0 pesos: ");
                             saldoNuevo = Integer.parseInt(br.readLine());
                         }

                     for (int s = 0; s < cont; s++) {
                         saldaso.add(sumaSaldos);
                     }
                     for (int d = 0; d < saldaso.size(); d++) {
                          sumaSaldos = saldaso.get(saldaso.size()-1) + saldoNuevo ;
                     }
                     System.out.println("Saldo inicial: " + saldo + "\n" + "Nuevo saldo: " + sumaSaldos
                             + "\n" + "Deposito de :" + saldoNuevo);
                     break;

                 case 3:

                     System.out.println("Ingrese su NIP a 4 digitos: ");
                     nip = br.readLine();

                     while (nip.length() != 4){
                         System.out.println("NIP incorrecto debe tener 4 digitos: ");
                         nip = br.readLine();
                     }
                     System.out.println("Numero de tarjeta: ");
                     numeroT = Integer.parseInt(br.readLine());
                     System.out.println("Numero de cuenta: ");
                     numeroC = Integer.parseInt(br.readLine());

                     System.out.println("Ingresa el monto a retirar mayor a 0 pesos: ");
                     saldoNuevo = Integer.parseInt(br.readLine());

                     while (saldoNuevo <= 0){
                         System.out.println("Ingresa el monto a retirar mayor a 0 pesos: ");
                         saldoNuevo = Integer.parseInt(br.readLine());
                     }
                     for (int s = 0; s < cont; s++) {
                         saldaso.add(sumaSaldos);
                     }
                     for (int d = 0; d < saldaso.size(); d++) {
                         sumaSaldos = saldaso.get(saldaso.size()-1) - saldoNuevo ;
                     }
                     System.out.println("Saldo inicial: " + saldo + "\n" + "Nuevo saldo: " + sumaSaldos
                             + "\n" + "Retiro de: " + saldoNuevo);
                     break;
                     default:
                         System.out.println("Opsion incorrecta");
         }
             System.out.println("¿Realizar otra operación?, presiona 'si', si no cualquier tecla para salir.");
             otraOpsion = br.readLine();
        }while (otraOpsion.equalsIgnoreCase("si"));

     System.out.println("Gracias por usar cajero Banamex"  +"\n"+ "***Estos son los detalles***");

     Cajero datos = new Cajero(nombreC, numeroT, numeroC, nip);
     ArrayList detalles = new ArrayList();
      detalles.add(datos.getNombreClinte());
      detalles.add(datos.getNip());
      detalles.add(datos.getNumeroTrgeta());
      detalles.add(datos.getNumerCuenta());


      if(selecOper){
          detalles.add("Deposito");
      }else {
          detalles.add("Retiro");
      }

     String [] det = {"Cliente: ","Nip: ","Numero tarjeta: ","Numero cuenta: ","Operación: "};

     for (int x = 0; x < detalles.size(); x++) {
         System.out.println(det[x] +"\n"+ detalles.get(x));
     }
   }
}
