package ejercicios.Clases1;

import java.util.Scanner;

public class Cesar2 {
    public static void main(String args[]) {
        int y2, y1, x2, x1, yd, yu, xd, xu;
        Scanner entrada = new Scanner(System.in);

        System.out.println("Recta 1");
        System.out.println("y2:");
        y2 = entrada.nextInt();
        System.out.println("y1:");
        y1 = entrada.nextInt();
        System.out.println("x2:");
        x2 = entrada.nextInt();
        System.out.println("x1:");
        x1 = entrada.nextInt();

        System.out.println("Recta 2");
        System.out.println("y2:");
        yd = entrada.nextInt();
        System.out.println("y1:");
        yu = entrada.nextInt();
        System.out.println("x2:");
        xd = entrada.nextInt();
        System.out.println("x1:");
        xu = entrada.nextInt();

        Cesar1 result = new Cesar1();
        result.recta1(y2, y1, x2, x1);
        result.recta2(yd, yu, xd, xu);
        result.resultado();
    }
}
