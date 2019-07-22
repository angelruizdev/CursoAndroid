package ejercicios.Clases1;
import java.util.Scanner;
public class ClaseB {
String nombre;
private final int CONSTANTE;
int edad,suma,f,c;
public  int numeros [] = {5,2,1,4,5};
public String libros [][];
    Scanner entrada = new Scanner(System.in);

  public ClaseB(String nombre,int edad,String libros[][], int CONSTANTE){
      this.nombre = nombre;
      this.edad=edad;
      this.libros=libros;
      this.CONSTANTE=CONSTANTE;
  }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String datos(String nombre){
        System.out.println("Nombre :"+nombre);

        int s = 0;
        for(int dato: numeros){
            System.out.println(dato);
            s = s + dato;
        }
        System.out.println(s);

        return nombre;
    }
    //recibimos un array completo
    public int array(int matriz[]){
      int min = matriz[0];
        for (int i = 1; i < numeros.length; i++) {
            if (matriz[i]<min) {
                min=matriz[i];
            }
        }
        return min;
    }
    //recivimos un elemento del arreglo
    public int elemento(int elm){
      elm = elm*5;
        int matrizNumeros[][]=new int[2][2];
           //inicializamos la matriz
           matrizNumeros[0][0]=5;
           matrizNumeros[0][1]=2;
           matrizNumeros[1][0]=4;
           matrizNumeros[1][1]=10;
//creamos un for anidado para llenar la matriz
        for ( f = 0; f < matrizNumeros.length; f++) {
            for ( c = 0; c < matrizNumeros.length; c++) {
                System.out.println("valor: "+f+"-"+c);
                //llenamos la matriz por teclado
                matrizNumeros[f][c]=Integer.parseInt(entrada.next());
                suma += matrizNumeros[f][c];//igual suma los valores pero esta resumido
                //suma = suma + matrizNumeros[f][c];//sumamos los valores ingresados de la matriz
            }
        }

//creamos otro for anidado para recorrer e imprimir la matriz
        for ( f = 0; f < matrizNumeros.length; f++) {
            for ( c = 0; c < matrizNumeros.length; c++) {
                //imprimimos la matriz
                System.out.println("["+f+" "+c+"]="+matrizNumeros[f][c]);
            }
        }
        System.out.println("suman: "+suma);

            String matrizCadena[][] = {{"juan","ana"},{"mario","moy"}};
            //muestran los nombre juan y ana segun los subindices donde se encuentran
            //System.out.println(matrizCadena[0][0]+","+matrizCadena[0][1]);

        for (int i = 0; i < matrizCadena.length; i++) {
            for (int j = 0; j < matrizCadena.length; j++) {
                System.out.print(matrizCadena[i][j]+" ");
                if (j==1){
                    System.out.println( );
                }
            }
        }
        return elm;
    }
}
