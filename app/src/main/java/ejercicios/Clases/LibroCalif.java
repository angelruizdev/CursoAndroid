package ejercicios.Clases;

public class LibroCalif{

private String nombreDelCurso;
private int edad1;
//nom="Bienvenido!";

    int edad,suma;

    public String getNombreDelCurso() {
        return nombreDelCurso;
    }
    public void setNombreDelCurso(String nombreDelCurso) {
        this.nombreDelCurso = nombreDelCurso;
    }

    public int getEdad1() {
        return edad1;
    }

    public void setEdad1(int edad1) {
        this.edad1 = edad1;
    }

    public void mostrarMensaje(){
    }
    /*
    public int ingresarMonto(int x , int y){
        suma = x+y;
        int total = suma/2;
        //System.out.println("Total :" +total);
        return total;
    }
*/
}

 /*
        LibroCalif ins = new LibroCalif();
        LibroCalif ins1 = new LibroCalif();
        ins.nombre="'Angel";
        ins.edad=27;
        System.out.printf("Nombre :"+ins.nombre+"\n"+"Edad :"+ins.edad+"\n");

        ins1.nombre="Sergio";
        ins1.edad=20;
        System.out.printf("Nombre :"+ins1.nombre+"\n"+"Edad :"+ins1.edad);

  //Ejercicio Matriz
        int matriz[][]  = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};

        for (int f = 0; f<matriz.length; f++){
            for (int c = 0; c<matriz.length; c++){
                //System.out.print(matriz[f][c]+" ");
                int mul = matriz[f][c]*2;
                if(mul <= 8){
                    System.out.printf(" ");
                }
                System.out.print(mul+" ");
            }
            System.out.println("");
        }
..................................................
      //ejmplo de for each(), con ArrayList
        List<Integer> nombre = new ArrayList();
        nombre.add(1);
        nombre.add(2);
        nombre.add(3);
        nombre.add(4);
        for (Integer nombres: nombre) {
            System.out.println(nombres);
        }
        ..................................
     //operador prefijo
        int p = 0;
        while (p<10) {
            System.out.println(++p);
        }
           //salida: 12345678910
        .....................
        //operador NOT (!)
        boolean v=false;
        System.out.printf("es :"+(!v));
        ...............................
        Scanner leer = new Scanner(System.in);
        System.out.printf("valor :");
        int val = Integer.parseInt(leer.next());
        int x=1;
        double y=0;
          while (x<=val){
              y=+x; //y=y+x;
              x+=1;
          }
        System.out.println("ciclo :"+y);

        String nom = "el amor de mi vida";
        System.out.printf("corte :"+ nom.startsWith("e"));

        int a=1;
        double b=2.5;
        a=(int)b;
        //cast
        double c=25.1;
        int x = (int) c;
        String text = "10.1";
        double numero = Double.parseDouble(text);
        //System.out.printf("cast: "+numero);
        int valor=10;
        String cast= String.valueOf(valor);
        double cad = Double.parseDouble("2.1");
        //System.out.printf("cast: "+cad*3);

        System.out.printf("tu nombre:");
        String nombre = leer.next();
        System.out.println("tu nombre es:"+nombre);
        System.out.printf("Quieres cambiar tu contraseña :");
        int op = leer.nextInt();
        if(op>1){
            System.out.printf("cambiar :"+nombre+"por :");
            String nuevo = leer.next();
            //System.out.println("cambiaste :"+nombre+"por :"+nombre.replaceAll(nombre,nuevo));
            nombre=nuevo;
            System.out.printf("nuevo :"+nombre);

        }




        String verdad = "false";
        boolean v = Boolean.parseBoolean(verdad);
        if (verdad.equals(valor)){
            System.out.printf("Excelent!"+v);
        }else {
            System.out.printf("Error 401!"+v);
        }

        System.out.println("Bienvenido al libro caficaciones! "+nom);
        int a=10;
        int b=10;
        int suma = a+b;
        int resta = a-b;
        int mult = a*b;
        System.out.printf("calis: "+suma+","+resta+","+mult);
        */

