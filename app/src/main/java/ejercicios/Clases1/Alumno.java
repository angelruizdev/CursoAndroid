package ejercicios.Clases1;

import java.util.ArrayList;
import java.util.Scanner;

public class  Alumno extends Maestro implements InterfActividades,InterfComer{
    //Emcapsulamiento de la variable kilos
    private int kilos;
    //método set para acceder y asignar valor desde otra clase.
    public void setKilos(int kilos){
        this.kilos=kilos;
    }
    //método get para acceder y mostrar su valor desde otra clase.
    public int getKilos(){
        return kilos;
    }
    //este método recibe 2 parámetros, a y b , los cuales los retorna sumandolos.
    public int suma(int a, int b){
      try {
        int div = a/b;
          System.out.println(div);
      }catch (ArithmeticException e){
          System.out.println(e.getMessage()+" No divisible");
      }finally {
       //System.exit(0);//cierra la aplicación
       return a+b;
      }
    }

    Scanner ent=new Scanner(System.in);
    private String materias[]={"Fisisca","Quimica","Matematicas"};
    protected float IVA;
    int pco;
    protected ArrayList<Integer>precio=new ArrayList<>();
    Scanner med=new Scanner(System.in);
    public Alumno(String nombre, int edad, char sexo, String ocupacion, float IVA) {
        super(nombre, edad, sexo, ocupacion);
        this.IVA=IVA;
    }

    @Override
    public void datos() {
        super.datos();
        System.out.println(getNombre()+"\n"+getEdad()+"\n"+getSexo()+"\n"+ocupacion+"\n"+IVA);
    }

    public void precios(){
        System.out.println("ingrese precio de la materia");
        for (int i = 0; i < 3; i++) {
            System.out.println(materias[i]);
            pco = Integer.parseInt(ent.next());
            float tot=pco*IVA;
            precio.add((int) tot);
            if (i == 2) {
                for (Integer valores: precio) {
                    System.out.println(valores.toString());
                }
            }
        }
    }
//pertenece a la interface InterfActividades, aquí lo implementamos.
    @Override
    public void hacerEjercicio() {
        System.out.println("Correr en las mañanas");
    }
//pertenecen a la interface InterfComer, aquí lo implementamos.
    @Override
    public String comer() {
        return "Comer fruta en las mañanas";
    }
}
