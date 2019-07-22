package ejercicios.Clases1;

public class ClaseUno{

    public static void main(String[]args) {
        Maestro master = new Maestro("Roberto", 25, 'H', "Docente");
        Alumno alumno = new Alumno("Juanito", 18, 'H', "Estudiante", 10.5f);
        claseString letras = new claseString();
        master.datos();
        System.out.print("-----------" + "\n");
        alumno.datos();
        alumno.precios();
        //llamamos a los métodos de las interfaces mediante los objetos de cada clase implementada ya que no podemos crear objetos de una interfaz, ya que es una clase abstracta a la vez.
        System.out.println("Maestro");
        master.hacerEjercicio();
        System.out.println(master.comer());
        System.out.println("Alumno");
        alumno.hacerEjercicio();
        String com = alumno.comer();
        System.out.println(com);
        System.out.println();
        //asignamos valor a la kilos con su método get()
        alumno.setKilos(10);
        //imprimimos el valor de la variable kilos con su método get()
        System.out.println(alumno.getKilos()+" kilos");
        //como argumentos pasamos dos enteros al método, el cual retorna la suma de los 2 argumenos, al imprimir el método saldra dicha suma.
        //System.out.println(alumno.suma(1,2));//es lo mismo que el de abajo
        int sum=alumno.suma(5,0);//en este guardamos al método en una variable, e imprimimos la variable que guarda al método.
        System.out.println("suman, "+ sum);
        letras.verLetras();
        //llamamos al método split()
        letras.split();
        //llamamos al método Patrón
        letras.patron();
    }
}
