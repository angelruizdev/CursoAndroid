package Sebas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SumaPares {
    static ArrayList<Integer> numeros = new ArrayList<>();
    static  ArrayList<String> caracteres = new ArrayList<>();
    static int x;
    static int suma = 0;
    static int suma2 = 0;
    static String palabra;

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingresa una palabra: ");
        palabra = br.readLine();
        caracteres.add(palabra);

        for (int i = 0; i < 11; i++){
            numeros.add(i);
        }
        for(x = 0; x < numeros.size(); x++){
            if(numeros.get(x) % 2 == 0){
                suma+=numeros.get(x);
            }else{
                suma2+=numeros.get(x);
            }
        }
        System.out.println("Suma de pares " +suma);
        System.out.println("Suma de inpares " + suma2);
        System.out.println("la palabra: "+ palabra + " Tiene "+palabra.replace(" ", "").length() + " letras");
        System.out.println("El Array Tiene "+caracteres.size() + " letras en total");

    }
}
