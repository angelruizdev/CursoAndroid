package ejercicios.Clases1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class claseString {
   public void verLetras(){
       char letras[]={'a','b','c'};
       char datos[]=new char[10];
       String nombre = "Ángelito";
       System.out.println(letras);

       for (int i = 0; i < nombre.length(); i++) {
           System.out.print(nombre.charAt(i));
       }
       System.out.println();
       try {
           nombre.getChars(0,3,datos,0);
       //este tipo de exception es para ver si se quiere acceder a una posición del array que no exista.
       }catch (ArrayIndexOutOfBoundsException e){
           System.out.println(e.getMessage()+" Error, número no valido");
       }finally {
           System.out.println(datos);
           //combertimos ca cadena nombre en un array char
           char arrayCaracter[]=nombre.toCharArray();
           for (char ac: arrayCaracter) {
               System.out.println(ac);
           }
       }
    }
       public void split(){
         //quitaremos el & a cadena
         String cadena = "Hola&me&llamo&angel";
         //con el nombre de la cadena accedemos al método split(), le pasamos como parámetro el caracter &
         //guardamos en un array String a la cadena sin el &
         String cadenaArray[] = cadena.split("&");
         //recorremos el array que guarda la cadena sin & y la impimimos
         for (String cad: cadenaArray) {
              System.out.println(cad);
         }
         //método trim(), elimina los espacios que tenga una cadena en el inicio y fin
         String conEspacio = " angel@hmail.com";
         //con el método trim(), eliminaremos el espacio del inicio
         System.out.println(conEspacio);
         System.out.println(conEspacio.trim());
         //clase StringBuilder
         String sbr="es chingon";
         StringBuilder sb = new StringBuilder();
         //append:permite concatenar cadenas
         sb.append("Angel Davidddd").append(sbr).append(7);
         sb.delete(11,14);//elimina una parte de la cadena
         sb.indexOf("x");//busca si existe este caracter
         sb.insert(5,"_");//inserta un caracter en la cadena, en dicha posición
         sb.replace(0,1,"Á");//reemplaza un caracter por otro en dicha posición
         sb.charAt(7);//muestra el caracter en dicha posición
         sb.deleteCharAt(0);//elimina el caracter que este en la posición que se le pase

         //sb.reverse();
         System.out.println(sb);//impime el estado actual de la cadena
         //clase Character, sus métodos son static, accedemos a ellos con el nombre de la clase
         String caracter="a.co+m";
         for (int c=0; c<caracter.length(); c++){//recoremos la cadena llamada caracter
              char chr=caracter.charAt(c);//guardamos cada caracter en la variable chr
             if(Character.isLetter(chr)){//método isLetter determina si es una letra
                System.out.println("Es una letra. "+chr);
             }else if (Character.getType(chr)==Character.OTHER_PUNCTUATION){//contstante OTHER_PUNCTUATION determina si es un simbolo de puntuación
                System.out.println("Es un signo de puntuación. "+"("+chr+")");
             }else if(Character.getType(chr)==Character.MATH_SYMBOL){
                 System.out.println("Es simbolo mat."+"("+chr+")");
             }
         }
     }
         //clase Pattern y Matcher
         public void patron() {
         //Ejemplo 1
             String email = "Angeldavid29121990@gmail.com";
             Pattern patron = Pattern.compile("[\\w]+@[a-z]+\\.[a-z]+");
             Matcher coincide = patron.matcher(email);

             if (coincide.find()) {
                 System.out.println("Valido"+email.toLowerCase());//pasamos todo el email a minúsculas
             } else {
                 System.out.println("No valido");
             }

         //Ejemplo 2
             String cad = "AnaBel_tiene:5-años";
             Pattern pat = Pattern.compile("[A|B]");//el patron compara si la cadena titne A ó B
             Matcher mat = pat.matcher(cad);
             if (mat.find()) {
                 String rem = mat.replaceFirst("Á");//reemplazamos la promer letra con asento
                 //String rem=mat.replaceFirst("Á").replace("_",",");
                 //String arrRem= Arrays.toString(rem.split("[_:-]"));
                 //podemos meter un patron como parametro en el metodo plit() y eliminar varias coincidencia de caracter
                 String arrayRem[] = rem.split("[_:-]");
                 System.out.println("Coincide");
                 for (String xRem : arrayRem) {
                     System.out.print(xRem + " ");
                 }
                 } else {
                     System.out.println("No coincide");
                 }
         }
}

