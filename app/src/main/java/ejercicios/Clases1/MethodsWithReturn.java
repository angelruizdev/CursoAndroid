package ejercicios.Clases1;

public class MethodsWithReturn {
    public static void main(String[] args){

       if (options(4)){
           System.out.print("Mayor" + "\n");
       }else {
           System.out.print("Menor" + "\n");
       }
       String exitString = optionsString("Juancho");
       System.out.print(exitString);
    }

    //return a boolean
    public static boolean options(int number){
        if (number > 5){
            return true;
        }else {
            return false;
        }
    }

    //return a string
    public static String optionsString(String name){
        String lastName;
        lastName = name + "-Develope r".replace(" ", "");

        return lastName;
    }
}
