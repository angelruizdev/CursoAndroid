package com.example.angelruiz.cursoandroid.Activitys;

public class Split {

    public static void main(String[] args){

        /*int x = 10;
        String url = "https://pokeapi.co/api/v2/pokemon/3/";
        String[] urlParts = url.split("/");
        for (int i = 0; i < urlParts.length; i++) {
            System.out.print(urlParts[i]);
        }
        System.out.println();
        System.out.print(Integer.parseInt(urlParts[urlParts.length -1])); //acedemos a la ultima posision del array urlParts y la mostramos
        //number(x);*/
        factorial();
    }
    private static int number(int num){
        System.out.println(num);
        return num;
    }

    private static void factorial(){
        int z, x = 0;
        System.out.println("factorial");
        int y = 4;
        for (int i = y-1; i >= 1 ; i--) {
          y = y * i;
        }
        System.out.print(y);
    }
}
