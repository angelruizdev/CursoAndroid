package ejercicios.Clases1;

import java.io.*;
import java.io.IOException;

//la clase File nos permite crear directorios(carpetas) y archivos de texto, con el atributo (separator), sustituimos el slash(//) para seperar la ruta, y no tener problemas de compativilidad
public class FileArchivos {
    private String nombre, apellidoPaterno, apellidoMaterno;
    public FileArchivos(String nombre, String apellidoPaterno, String apellidoMaterno){
        this.nombre=nombre;
        this.apellidoPaterno=apellidoPaterno;
        this.apellidoMaterno=apellidoMaterno;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
//la carpeta y archivos se crean dentro de la carpeta de este proyecto,
//para crearlos el constructor basico nos pide un path(ruta), este por default toma la direccion de la carpeta del proyecto
    public static void main(String args[]) {
        System.out.println("Archivos");//como vemos primero creamosla carpeta llamada PruebaArchivos en el objeto carpeta, luego creamos el archivo pruebaArchivos.txt dentro de la carpeta
        File carpeta=new File("PruebaArchivos");//para crear una carpeta lo hacemos creando un objeto de la clase File, el cual debe acceder al metodo mkdir() para ser creada
        File file = new File(carpeta,"pruebaArchivos.txt");//para crear una carpeta lo hacemos creando un objeto de la clase File, el cual debe acceder al metodo createNewFile para ser creada
        PrintWriter printWriter;//mediante un objeto de esta clase PrintWriter, podemos escribor dentro de dicho archivo de texto
            if (!file.exists() && !carpeta.exists()) {//comprobamos si carpeta y archivo existen si no los creamos
                try {//la mayoria de los metodos de estas clase van dentro de bloques tryCatch  ya que pueden producir errores
                    carpeta.mkdir();//crea la carpeta
                    file.createNewFile();//crea el archivo
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Archivo no creado");
                }
            }else {//con el metodo getName() obtenemos el nombre del archivo o carpeta y con getAbsolutePath(), la ruta completa de donde se encuentran dichos archivos
                System.out.println(carpeta.getName()+" y "+file.getName() + " A sido creado en " + file.getAbsolutePath());
                try {
                    FileArchivos fileArchivos=new FileArchivos("Angel","Ruiz","Cruz");

                    printWriter = new PrintWriter(file, "utf-8");//le decimos que estamos escribiendo en español en el archivo file
                    printWriter.print("Nombre: "+fileArchivos.getNombre()//escribimos dentro del archivo mediante objetos
                            +" Apaterno: " +fileArchivos.getApellidoPaterno()
                            +" Amaterno: " +fileArchivos.getApellidoMaterno());
                    printWriter.close();//cerramos la escritura para que la guarde en el archivo
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
      }
}
