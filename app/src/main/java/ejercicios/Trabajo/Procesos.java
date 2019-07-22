package ejercicios.Trabajo;

import java.util.Scanner;

public class Procesos {
    public static void main(String args[]){

        Scanner ent = new Scanner(System.in);

        Pelicula  pla,pla1,pla2,pla3,pla4 = null;
        Serie sre,sre1,sre2,sre3,sre4 = null;
        String peliculas[]={"El clon","El gladiador","La mascara","Avengers","Transformers"};
        String series[]={"Los simson","Señor de los cielos","Pockemon","Caballeros del sodiaco","Teresa"};

        System.out.println("******Peliculas******");

        for (String x:peliculas) {
            System.out.println(" - " + x);
        }

        System.out.println("Seleccione una pelicula, del 1 al 5");
        int opsion=ent.nextInt();
        switch (opsion) {
            case 1:
                String a =    peliculas[0];
                pla = new Pelicula(a,"Drama","Pedro c",1990,2.00);
                pla.marcarVisto();
                pla.verDetalles();
                System.out.println("***Detalles***");
                pla.todo();
                //System.out.println(a);
                break;
            case 2:
                String b=    peliculas[1];
                 pla1 = new Pelicula(b,"aventura","Mario m",2000,1.00);
                pla1.marcarVisto();
                pla1.verDetalles();
                System.out.println("***Detalles***");
                pla1.todo();
                break;
            case 3:
                String c=    peliculas[2];
                 pla2 = new Pelicula(c,"animación","Pablo r",2005,50.00);
                pla2.marcarVisto();
                pla2.verDetalles();
                System.out.println("***Detalles***");
                pla2.todo();
            break;
            case 4:
                String d=    peliculas[3];
                 pla3 = new Pelicula(d,"animación","Pablo r",2005,50.00);
                pla3.marcarVisto();
                pla3.verDetalles();
                System.out.println("***Detalles***");
                pla3.todo();
                break;
            case 5:
                String e=    peliculas[4];
                 pla4 = new Pelicula(e,"animación","Pablo r",2005,50.00);
                pla4.marcarVisto();
                pla4.verDetalles();
                System.out.println("***Detalles***");
                pla4.todo();
                break;
        }
//Series
        System.out.println();
        System.out.println("******Series******");

        for (String z:series) {
            System.out.println(" - "+z);
        }
        System.out.println("Selecciona una serie, del 1 al 5");
        int opsio1 = ent.nextInt();

        switch (opsio1) {
            case 1:
                String a = series[0];
                sre = new Serie(a, "Caricatura", "Juan s", 1890, 2.30, 1);
                sre.marcarVisto();
                sre.verDetalles();
                System.out.println("***Detalles***");
                sre.todo();
                break;
            case 2:
                String b = series[1];
                sre1 = new Serie(b, "Fantastico", "Manuel c", 2010, 2.00, 1);
                sre1.marcarVisto();
                sre1.verDetalles();
                System.out.println("***Detalles***");
                sre1.todo();
                break;
            case 3:
                String c = series[2];
                sre2 = new Serie(c, "Caricatura", "Ernesto c", 2008, 1.50, 1);
                sre2.marcarVisto();
                sre2.verDetalles();
                System.out.println("***Detalles***");
                sre2.todo();
                break;
            case 4:
                String d = series[3];
                sre3 = new Serie(d, "Caricatura", "Sandra c", 2000, 1.00, 1);
                sre3.marcarVisto();
                sre3.verDetalles();
                System.out.println("***Detalles***");
                sre3.todo();
                break;
            case 5:
                String e = series[4];
                sre4 = new Serie(e, "Comedia", "Patricia c", 2001, 4.30, 1);
                sre4.marcarVisto();
                sre4.verDetalles();
                System.out.println("***Detalles***");
                sre4.todo();
                break;

        }
    }
}
