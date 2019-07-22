package ejercicios.Clases;

import java.util.Scanner;

public class ejercicios {


    public static void main (String[] args) {

      int a, c, o, b, d, e, f, g, h, m, s, t;
      int contar = 0;
      int contar1 = 0;
      int contar2 = 0;
      int contar3 = 0;
      float media;

      int sum = 0;
      int sum1 = 0;
      int sum2 = 0;
      int i = 0;
      int pro = 1;

      Scanner sc = new Scanner(System.in);
      System.out.print("opsion: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10");
      o = sc.nextInt();

      switch (o) {
          case 1:
              System.out.print("ingresa un número");
              a = sc.nextInt();
              while (a > 0) {
                  c = a * a;
                  System.out.print("es: " + c + "\n");
                  System.out.print("ingresa otro número");
                  a = sc.nextInt();
                  //a++;
              }
              System.out.print("numer no valido");

              break;

          case 2:

              System.out.print("ingrese numero");
              b = sc.nextInt();

              while (b > 0) {
                  System.out.print("el numero es positivo" + "\n");
                  System.out.print("ingrese numero");
                  b = sc.nextInt();
                  while (b < 0) {
                      System.out.print("el numero es negativo" + "\n");
                      System.out.print("ingrese numero");
                      b = sc.nextInt();
                  }

              }
              System.out.print("metiste un cero");

              break;

          case 3:
              System.out.print("ingresa numero" + "\n");
              d = sc.nextInt();

              while (d > 0) {
                  if (d % 2 == 0) {
                      System.out.print("es par" + "\n");

                  } else {

                      System.out.print("es inpar" + "\n");

                  }
                  System.out.print("ingresa numero" + "\n");
                  d = sc.nextInt();

              }
              System.out.print("metiste cero");

              break;

          case 4:

              System.out.print("ingresa numero" + "\n");
              d = sc.nextInt();

              while (d >= 0) {
                  System.out.print("otro número" + "\n");
                  d = sc.nextInt();
                  contar = contar + 1;

              }

              System.out.print("saliste con un negativo, ingresaste, " + contar + " ,numeros");

              break;

          case 5:
              do {

                  System.out.print("ingresa numero" + "\n");
                  e = sc.nextInt();
                  sum = sum + e;


              } while (e != 0);

              System.out.print("suma de los números" + sum);
              break;

          case 6:

                  System.out.print("ingresa numero");
                  f = sc.nextInt();

              while(f >= 0){

                  System.out.print("ingresa numero");
                  f = sc.nextInt();

                  sum1 = sum1 + f;   //sum1+=f; ... es lo mismo resumido
                  contar1 = contar1 + 1;   //contar1++; ... es lo mismo resumido

              }

              media = sum1 / contar1;

              if(media == 0){

              System.out.print("No se hiso la media");

              }else{

              System.out.print("la media es: " + media);

              }

              break;

          case 7:

              System.out.print("ingresa número");
              g=sc.nextInt();
              System.out.print("estos números: \n");

              while(i<g){

                  //for(int i = 0; i<g; i++){

                     //System.out.print(i + "\n");
                      //contar2++;
                      //}

                  System.out.print(i + "\n" );
                  i++;

              }

              System.out.print("caben en, " + g + "\n" );

              break;

          case 8:

              System.out.print("ingresa número");
               h=sc.nextInt();

              while (h > 0){

                  System.out.print("estos caben en. " + h + "\n");

                  for(int n = 0; n < h; n++){

                      System.out.print( n + "\n");

                  }

                  System.out.print("ingresa número");
                  h=sc.nextInt();

              }

              break;

          case 9:

              //System.out.print("ingresa número");
              //k=sc.nextInt();

              System.out.print("con while \n");
              int k = 100;

              while(k >= 0) {

                  System.out.print(k + "\n");
                  k-=7;
              }

              System.out.print("con for \n");

                   for (int j = 100; j >= 0; j-=7) {

                      System.out.print(j + "\n");

                  }

              break;

          case 10:

                System.out.print("ingesa número \n");

              for(int p = 1; p <=5; p++){

                  System.out.print("número: " + p);
                  m=sc.nextInt();

                  //sum2 = sum2 + m;
                  sum2+=m;
              }
              System.out.print("suman, " + sum2);

              break;

          case 11:

              System.out.print("***producto***\n");

              for(int q = 1; q <=30; q++){

                  if(q%2!=0){
                      contar3++;
                     if(q%2!=0 && contar3<=10) {
                         pro *= q;
                     }
                      //while() {

                          //pro = pro * q;

                      //}
                  }
              }

              System.out.print("producto \n" + pro + "sum" + contar3);
              break;

          case 12:

              int z;
              int res = 1;

              System.out.print("ingresa número");
              s=sc.nextInt();

                  for (z = s; z >= 1; z--) {

                      //res = res * z
                      res *= z;
                      //res = res + 1; ... cuando res vale cero.

                  }
                  System.out.print("Factorial de: " + s + " es: \n" + res);

              break;

          case 13:

              System.out.print("ingresa número");
              t = sc.nextInt();
               int l;
              char[] num = new char[t];

              for(l = 0; l < t; l++ ){

                  System.out.print("ingresa el número: " + (l + 1));
                   num[l] = sc.next().charAt(0);
              }
              System.out.print("metiste estos:\n ");

              for(l = 0; l < t; l++){

                  System.out.print(num[l] + "\n");

              }
              break;

          case 14:
               String nom;
              System.out.println("ingresa valor");
              nom = sc.nextLine();
              System.out.println("es: "+ nom);

              break;

          case 15:

          char letra;
          System.out.println("ingresa caracter");
              letra = sc.next().charAt(0);
              System.out.printf("ingresaste: " + letra);

           break;

          case 16:

              int x1 = 10;
              x1-=3;
              System.out.println("valor: " + x1);

              break;

          case 17:
              double x2, x3;
              System.out.println("ingresa valor");
              x2 = sc.nextDouble();
              x3 = Math.sqrt(x2);
              System.out.println("la raiz de: "+x2+ "es: " +x3);
              break;

          case 18:
              double base, exp, resu;
              System.out.println("base: ");
                base = sc.nextDouble();
              System.out.println("esponente: ");
                exp = sc.nextDouble();
              resu = Math.pow(base, exp);
              System.out.printf("el cuadrado de :" + base + "es: " + resu);

              break;
      }
   }
}
