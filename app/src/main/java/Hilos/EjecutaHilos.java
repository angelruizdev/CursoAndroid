package Hilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EjecutaHilos {

    public static synchronized void main(String[]args) throws InterruptedException {
        Thread runnable = new Thread(new Hilo1());
        Hilo2 thread = new Hilo2();
        runnable.start();
        runnable.join();
        thread.start();
        thread.join();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tab = 0;

        System.out.println("tabla");
        do {
            try {

                tab = Integer.parseInt(br.readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("número mayor a cero del 1 al 10");
        } while (tab <= 0);
        System.out.println("bien");

    }
}
