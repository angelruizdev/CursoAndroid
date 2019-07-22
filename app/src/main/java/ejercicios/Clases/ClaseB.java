package ejercicios.Clases;

 public class ClaseB {
        //Atributos, privados
        private String nombre;
        private int edad;
        private float peso;
        //Método constructor
 public ClaseB(String nombre, int edad, float peso){//constructor con parámetros,inicializando variables mediante los parámetros

        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }
 public ClaseB(){//constructor sin parámetros,inicializando variables directamente

        this.nombre="Armando islas";
        this.edad=50;
        this.peso=89.4f;
    }
        //Métodos get y set para la variable edad
 public int getEdad() {//método get, muestra el valor de la variable
        return edad;
    }

 public void setEdad(int edad) {//método set, recibe, establece, modifica el valor para la variable edad
        this.edad = edad;
    }

 public void verDatosUsiario() {//método para imprimir los valores guardados en las variables
         System.out.println("Nombre :" + nombre);
     if (edad>=27){
         System.out.println("Edad :"+getEdad());//muestra lo que tiene edad, mediante metodo get
     }else {
         System.out.println("error!");
     }
         System.out.println("Peso :" + peso);
     }
}
