package ejercicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ClaseA extends ClaseB{

    public ClaseA(String nombre, int edad,String libros[][],int CONSTANTE) {
        super(nombre, edad,libros,CONSTANTE);
    }

    public static void main(String[]args){
        Scanner entrada = new Scanner(System.in);
        ArrayList<String> listaNombre=new ArrayList<>();
        for (int i = 0; i < 3 ; i++) {
            System.out.println("Ingresa el nombre:"+(i+1));
            String no= entrada.next();
            listaNombre.add(no);
        }
        for (String n: listaNombre) {
            System.out.println(n);
        }
        System.out.println("los nombres son: "+listaNombre);
        System.out.println(listaNombre.indexOf("Ángel"));//si existe Ángel en la lista me regresa su subindicesi no -1

        ArrayList <Integer> numerosPares=new ArrayList<Integer>();
            numerosPares.add(2);
            numerosPares.add(4);
            numerosPares.add(6);
        System.out.println(numerosPares.get(1));//regresa el valor del elemento con el indice que recibe como argumento
        System.out.println(numerosPares.contains(12));//busca el el OAL, si hay un elemento con ese valor, pasado como argumento, si exixte regresa true si no false
        System.out.println(numerosPares.size());//muestra la cantidad de elementos en el OAL
        System.out.println(numerosPares.indexOf(1));//busca si hay un elemento con ese valor de argumento,si no hay regresa -1
        //numerosPares.remove(1);//elimina el elemento con el subindice pasado como argumento
        //numerosPares.clear();//elimina todos los valores del objeto AL.
        System.out.println("pares: ");
        for (int i = 0; i < numerosPares.size(); i++) {
            System.out.println(numerosPares.get(i));
        }

        ArrayList<Integer>numerosImpares=new ArrayList<>();
            numerosImpares.add(1);
            numerosImpares.add(3);
            numerosImpares.add(5);
            numerosImpares.addAll(numerosPares);//copiamos el OAL numerosPares en numeros impares, coloca los elementos al final de esta colección
        System.out.println("Pares e impares: ");
        for (int i = 0; i < numerosImpares.size(); i++) {
            System.out.println(numerosImpares.get(i));
        }

    String libros [][]={{"matematicas","fisica"},{"quimica","biologia"}};
    int matriz [][]={{12,5},{11,3}};

    System.out.println("ingrese un nombre");
    String nom = entrada.next();
    ClaseB dato = new ClaseB(nom,10,libros,10);

    System.out.println(dato.datos(nom)+" - "+dato.edad);
    System.out.println("El minimo es :"+dato.array(dato.numeros));
    System.out.println("Elemeto :" + dato.elemento(dato.numeros[0]));
    System.out.println();
    imprimirMenor(matriz);//nos permite lamarlo desde aqui (main)por que es static

}
//sacamos el valor más pequeño de la matriz
private static void imprimirMenor(int matrizMenor[][]){
     int menor = 10;
     float resultado=0;
     int a,b;
     int arrayOrigen []={6,1,7,2,9};//array desordenado a ordenar//sort();
     char abc[]={'d','c','b','a'};//sort();
     int arrayNuevo[]=new int[10],llena=1;//fill();
     byte arrayA[]={1,2,3,4,5,6,7,8,9,10};//equals();
     byte arrayB[]={1,2,3,4,5,6,7,8,9,10};
     byte arrayC[]={11,12,13,14,15,16,17};

//muestra el valor menor de un array, y pone el numero con asteriscos
     for (int f = 0; f < matrizMenor.length; f++) {
        for (int c = 0; c < matrizMenor.length; c++) {
            if (matrizMenor[f][c]<=menor) {
                menor = matrizMenor[f][c];
                System.out.println("el menor es: "+menor);
                for (int i = 0; i < menor; i++) {
                    System.out.print(" * ");
                }
                System.out.println();
            }
        }
    }

    try {
        Arrays.sort(arrayOrigen, 2,4);//método sort(), le pasamos el array a ordenar como parametro
        Arrays.sort(abc);

        while (llena<=arrayNuevo.length){
            Arrays.fill(arrayNuevo, llena*5);
            llena++;
        }
        boolean compara = Arrays.equals(arrayA,arrayB);
        boolean compara1 = Arrays.equals(arrayA,arrayC);
        System.out.println("El 6 está en el sibindice: "+ Arrays.binarySearch(arrayA, (byte) 5));

        if (compara) {
            System.out.println("IGUALES...");
        }else {
            System.out.println("DIFERENTES...");
        }
        if (compara1) {
            System.out.println("IGUALES...");
        }else {
            System.out.println("DIFERENTES...");
        }

        String num = "23";
        a = Integer.parseInt(num);//parseo
        b=0;
        resultado = a/b;//division entre cero

    }catch ( NumberFormatException | ArithmeticException e) {//manda Exception si está la el casteo ó la division
        System.out.println("Error: " + e.getMessage());//capturamos en el objeto ,e, el mensaje de la Excepsion
    }finally{//este bloque es opsional ya que se ejecutara todo el código aún que haya errores.
        System.out.println("Res: "+resultado);
        System.out.println("Finalizamos...");
        for (char ori: abc) {//recorremos el array ordenado
            System.out.print(ori+",");//lo mostramos
        }
        System.out.println();
        for (int ord: arrayOrigen) {
            System.out.print(ord+",");//lo mostramos
        }
        System.out.println();
        for (int fill: arrayNuevo) {
            System.out.println(fill);
        }
        System.out.println();

        byte arrayD []={1,2,3,4,5,6,7,8,9,10};
        byte arrayE [] = Arrays.copyOf(arrayD,10);

        System.out.print("Arreglo E: ");

        for (int i = 0; i < arrayE.length; i++) {
            System.out.print(arrayE[i]);
        }
        boolean igual = Arrays.equals(arrayD,arrayE);
        if (igual){
            System.out.println(" Arreglo D y E son iguales");
        }else {
            System.out.println("Error");
        }
        System.arraycopy(arrayA, 0, arrayC,0,3);
        for (byte cop: arrayC) {
            System.out.println(cop);
        }
    }


//matriz y array
   /* System.out.println();
    Scanner entra = new Scanner(System.in);
    int calificaciones[][] = new int[2][3];
    String materias[]={"fisica","quimica","matematicas"};
    for (int f = 0; f < calificaciones.length; f++) {
        for (int c = 0; c < calificaciones.length; c++) {
            for (int i = 0; i < materias.length; i++) {
                System.out.println("amulno: Juan :"+materias[i]+(f++));
                calificaciones[f][c]=Integer.parseInt(entra.next());

            }
        }
    }
    System.out.println("");*/
}
}
