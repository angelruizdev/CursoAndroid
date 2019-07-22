package ejercicios.Clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ejercicios1  {


  public static void main (String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      //int a = 5;
      suma(2,3);

      /*
      String nom;
      int su=0;
      double pro = 0;
      System.out.println("Ingrese tu nombre :");
      nom = br.readLine();
      System.out.println("Hola... ,"+nom+" Ingresa la calificacion de cada materia :");
      String[] materias = {"Matematicas:","Fisica:","Quimica:"};
      float[] cali = new float[3];
      for (int c = 0; c < cali.length; c++){
          System.out.println(materias[c]);
          cali[c] = Float.parseFloat(br.readLine());
          su += cali[c];
          pro = su/cali.length;
      }
      if (cali[0]<cali[1]&&cali[2]>cali[1]) {
          System.out.println("mayor"+materias[2]+":"+cali[2]);
          System.out.println("inter"+materias[1]+":"+cali[1]);
          System.out.println("menor"+materias[0]+":"+cali[0]);
      }
      System.out.println(nom + ",tu promedio es de :"+pro);

     //arreglo de tipos String,con nombres
     int nom;
      System.out.println("teclea los nombre a registrar");
       nom = Integer.parseInt(br.readLine());
       String[] nombre = new String[nom];
        for (int z = 0; z < nombre.length; z++){
            System.out.println("nombre :"+ (z+1));
            nombre[z] = br.readLine();
        }
            System.out.println("tus nombre son :");
        for (int z1 = 0; z1 < nombre.length; z1++){
            System.out.println(nombre[z1]);
        }

      //metodo burbuga
      int nu;
          System.out.println("tamaño igual a 3");
          nu = Integer.parseInt(br.readLine());
          int[] num = new int[nu];
          int suma, suma1 = 0;
          int x, y;
          for (x = 0; x < num.length; x++) {
              System.out.println("valor" + (x + 1));
              num[x] = Integer.parseInt(br.readLine());//con esta lines pedimos los valores para el arreglo
              suma1 +=num[x];//aquí sumamos los valores de array
          }
          System.out.print("ingresaste :");
          for (y=0; y<num.length; y++){//con este bucle mostramos los valores asignados a cada posision del arreglo
          System.out.print(","+num[y]);
          }
          System.out.println();

      if (num[0] < num[1] && num[2] > num[1]) {
          System.out.println("orden :" + num[0] + "," + num[1] + "," + num[2]);
      } else if (num[1] < num[0] && num[2] < num[1]) {
          System.out.println("orden :" + num[2] + "," + num[1] + "," + num[0]);
      } else if (num[1] < num[0] && num[2] > num[0]) {
          System.out.println("orden :" + num[1] + "," + num[0] + "," + num[2]);
      } else if (num[0] < num[2] && num[1] > num[2]) {
          System.out.println("orden :" + num[0] + "," + num[2] + "," + num[1]);
      } else if (num[2] < num[0] && num[1] > num[0]) {
          System.out.println("orden :" + num[2] + "," + num[0] + "," + num[1]);
      } else if (num[1] < num[2] && num[0] > num[2]) {
          System.out.println("orden :" + num[1] + "," + num[2] + "," + num[0]);
      }
      System.out.println("suma: " + suma1);





      System.out.println("valor");
      int valor = Integer.parseInt(br.readLine());

      for(int r4 = 1; r4 <= valor; r4++){
          System.out.println("");
          for (int r5 = valor; r5 >= r4; r5--){
              System.out.print(" ");
          }
          for (int r6 = 1; r6 <= r4; r6++){
              System.out.print(" *");
          }

      }



//cuadrado contorno
      for (int r=1; r<=5; r++){
          System.out.print(" *");
      }
          System.out.println("");
      for (int r=0; r<5-2; r++){
          System.out.print(" *");
          for (int s=0; s<=5; s++){
              System.out.print(" ");
          }
          System.out.println(" *");
      }
      for (int r=1; r<=5; r++){
          System.out.print(" *");
      }
      System.out.print("\n");
//rombo

      for (int r = 1; r <= 5; r++){
        System.out.println("");
          for(int o = 0 ; o <= 5-r; o++){
            System.out.print("-");
          }
          for (int s = 1; s <= r; s++){
            System.out.print(" *");
          }
      }
      for (int r1 = 4; r1 >=1; r1-- ){
          System.out.println("");
          for (int r2 = 0; r2<=5-r1; r2++){
              System.out.print("-");
          }
          for (int r3 = 1; r3<=r1; r3++){
              System.out.print(" *");
          }
      }
      System.out.printf("\n");
      //contorno rombo
      */



/*
      //int[] numeros = new int[5];
       //numeros[0]=  2;
       //numeros[1] = 2;
       //numeros[2] = 3;
       //numeros[3] = 3;
       //numeros[4] = 4;
       //int[] numeros = {4,5,6,7,8};
////////////////////////////////////////
      int array;
      double calif;
      int suma = 0;
      System.out.printf("tamaño del arreglo");
      array = Integer.parseInt(br.readLine());
      int[] arreglo = new int[array];
      String[] materias = {"Matematicas","fisica","Quimica"};

       for (int a = 0; a<array; a++){
           System.out.println(materias[a]);
           arreglo[a] = Integer.parseInt(br.readLine());
           suma = suma + arreglo[a];
           //System.out.println("calificación: "+(a+1));
        }
       System.out.println("calificaciónes: ");
       for (int n = 0; n<array; n++){
           System.out.println(materias[n]+":"+arreglo[n]);
       }
       calif = suma/array;
       System.out.printf("tu promedio es de: "+calif);
///////////////////////////////////////////////////////////



      for(int y = 2; y <= 10; y++){
        System.out.println("tabla del: "+y);
          for(int z = 1; z <= 10; z++){
              int tabla = (y * z);
               System.out.println(tabla);
          }
      }


      System.out.println("Dime tu npmbre:");
      String nombre = br.readLine();
      System.out.println("hola: "+nombre +","+ nombre.length()+"caracteres tiene tu nombre");


      String nom = "a";
      String pass = "";
      byte intentos = 0;

      System.out.println("ingresa tu contraseña solo tienes 3 intentos");

      while (!nom.equals(pass)){//nom.equals(pass)==false...también se cumple y entra al bucle
          intentos++;

          pass = br.readLine();

          if((intentos == 3) && (!nom.equals(pass))){
          System.out.println("intenta más tarde");
             exit(0);
          }
          System.out.println("incorrecto");
          }
          System.out.println("Bienvenido");


      int tab;
      System.out.println("tabla");

    do {
        do {
            tab = Integer.parseInt(br.readLine());
            System.out.println("número mayor a cero del 1 al 10");
        } while (tab <= 0);
        int n = 1;
        while (n <= 10) {
            System.out.println(n * tab);
            n++;
        }
        System.out.println(" otra tabla");

    } while (tab <= 10);//restriccion solo numeros del 1 al 10
      System.out.println("fin...");

ejemplo con continue y ciclo for:
      for(int f = 0; f <= 10; f++){
          System.out.println("continua");
           if(f == 5){
         continue;
           }
          System.out.println("es: " +f);

           }
          System.out.println("saliste");

ejemplo con break y ciclo while

int d = 1;
      while (d <= 10){
          System.out.println("continua");
           if(d == 5){
         continue;
           }
          System.out.println("es: " +d);
          d++;
           }
          System.out.println("saliste");


      System.out.println("ingresa el dia:");
      int num4 = Integer.parseInt(br.readLine());
      System.out.println("ingresa el mes:");
      int num5 = Integer.parseInt(br.readLine());
      System.out.println("ingresa el año:");
      int num6 = Integer.parseInt(br.readLine());

      if(num4 > 0 && num4 <= 30 && num5 > 0 && num5 <= 12 && num6 >=1000 && num6 < 3000){ //no puede haber numeros negativos en una fecha
          System.out.println("fecha correcta:"+num4+"/"+num5+"/"+num6);
      }else{
          System.out.println("la fecha no exixte");
      }


      System.out.println("ingresa 3 números:");

          System.out.println("número1:");
          int num1 = Integer.parseInt(br.readLine());
          System.out.println("número2:");
          int num2 = Integer.parseInt(br.readLine());
          System.out.println("número3:");
          int num3 = Integer.parseInt(br.readLine());

          if(num1 > num2 && num2 > num3){
              System.out.println("orden:"+num1+"-"+num2+"-"+num3);
          }else{
              System.out.println("sin orden");
          }

      //String lista[] = {"angel","david","ruiz"};
      //for(int x=0; x<=6; x++){
      //  System.out.println(lista[1]);
      // }
      System.out.println("tamaño del arreglo:");
      //int num2 = Integer.parseInt(br.readLine());
      //int[] array = new int[20];
      int [] array ={11,13,23,1,31};
      int num3 = 0;
      int sum = 0;
      while (sum <=10) {
          //System.out.println("ingresa el numero:" + (i));
          //num3  = Integer.parseInt(br.readLine());
          //sum = num3;
          if(array[sum]%2==0) {
              //System.out.printf("par\t:" + array[sum] + "");
              System.out.printf("impar\t"+ array[sum] + "");
              sum++;
          }else {
              System.out.printf("par" + array[sum] + "");
              sum++;
          }
      }
..............................................
// int suma + = angel[angel.length];
          //JOptionPane.showMessageDialog(null,"tus numeros de la suerte son:"+suma);
..............................................
      int[][] matriz = new int[3][3];
      for (int i = 0; i < matriz.length; i++) {
          for (int j = 0; j < matriz.length; j++) {
              System.out.print("ingresar dato:");
              matriz[i][j] = Integer.parseInt(br.readLine());
          }
      }

System.out.println("ingresa texto");
      String caracter = br.readLine();
      Double cast = Double.parseDouble(caracter);
      byte mul = 10;
      double tot = cast * mul;
      System.out.println("es"+ tot);

      System.out.println("ingresa un numero");

      try {
          int num = Integer.parseInt(br.readLine());
          System.out.println("el numero es: "+ num);
      }catch (IOException e){
          System.out.println("el valor es incorrecto" + e);
      }
      */
  }



     public static int suma(int a,int b) {
         Scanner leer = new Scanner(System.in);
         System.out.printf("numero uno");
         a= Integer.parseInt(leer.next());
         System.out.printf("numero dos");
         b= Integer.parseInt(leer.next());
         int su=a+b;
         System.out.println("amooooor"+su);
       return su;
     }

  }
//caracter=(char)System.in.read();