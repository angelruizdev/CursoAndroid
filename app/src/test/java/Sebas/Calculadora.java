package Sebas;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculadora {
    public static void main(String args[]) {
        Scanner ent = new Scanner(System.in);
        String op;
        /*int a,b,c,d;
        int i;
        int array[]= {a = 0, b = 1, c = 0 , d = 0};
        String let[]= {"a","b","c","d"};

        //variables
        for (i = 0; i < 4; i++){
            System.out.println("ingrese operación: "+let[i]);
            array[i] = ent.nextInt();
        }
        for (int j = 0; j < array.length; j++) {
            System.out.println(array[0] + array[1] - (array[2] * array[3]) / array[1]);

        }*/


        System.out.println("ingrese operación: ");
        op = ent.next();
        if (op.startsWith("-")){

        }
        //final String texto = "1.3+5/6.0+1-8*2.0";
//variables para el regex
        final String regex = "[\\d.]+|[-+*/]";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(op);
//usamos este tipo para el resultado, pero puede ser el que prefieras
        final ArrayList<String> resultado = new ArrayList<String>();

//buscamos todas las coincidencias
        while (matcher.find()) {
            //agregando una por una a la lista
            //  matcher.group() devuelve la coincidencia del último matcher.find()
            resultado.add(matcher.group());
//podemos imprimir la lista
        }
        System.out.println(resultado);

    }
}
