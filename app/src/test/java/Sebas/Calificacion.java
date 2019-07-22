package Sebas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calificacion {
    static String nom;
    static int sem;
    static int sumaCalif = 0;
    static String[] materias = {"Matematicas", "Fisica", "Quimica", "Electronica"};
    static int [] calif = new int[4];

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String opcion;
        int eval;
        System.out.println("\n" +"****EVALUACIONES TECNM****");
        System.out.println("Ingresa tu nombre: ");
        nom = br.readLine();

        System.out.println("Ingresa tu semestre: ");
        sem = Integer.parseInt( br.readLine());

        while (sem != 2){
            System.out.println("Solo para alumnos de 2do semestre: ");
            sem = Integer.parseInt( br.readLine());
        }
    do {
        for (int i = 0; i < materias.length; i++) {
            System.out.println("Ingresa la calificación de: " + materias[i]);
            calif[i] = Integer.parseInt(br.readLine());
            if (i >= 3) {
                System.out.println("ESTAS SON TUS CALIFICACIONES");
            }
        }
        for (int i = 0; i < materias.length; i++) {
            System.out.println(materias[i]);
            System.out.println(calif[i]);

            sumaCalif += calif[i];

        int promedio = sumaCalif / 4;
        if (i >= 3) {
            Promedio detalles = new Promedio(nom, sem);
            System.out.println("DETALLES DEL ALUMNO" + "\n" + "Nombre: " +
                    detalles.getNombre() + "\n" + "Semestre:" + detalles.getSemestre() + "\n" +
                    "Tu promedio es de: " + promedio);
        }
    }
        System.out.println("¿Ingresar calificaciones de nuevo presiona 'si', si no cualquier letra?");
        opcion = br.readLine();
     }while (opcion.equalsIgnoreCase("si"));

        System.out.println();
        System.out.println("¿Te a gustado la evaluación? --> 1 - si , 2 - no");
        eval = Integer.parseInt(br.readLine());

            switch (eval){
                case 1:
                    System.out.println("Gracias por tu evaluacion");
                    break;
                case 2:
                    System.out.println("Gracias seguiremos mejorando");
                    break;
            }
        }
    }
