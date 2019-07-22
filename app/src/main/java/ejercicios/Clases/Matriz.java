package ejercicios.Clases;

public class Matriz {
    public static void main(String args[]){
//mi trabajo
        int[][] matriz = new int[4][4];
       byte x=0;
        for (int f = 0; f<matriz.length; f++){
            for (int c = 0; c<matriz.length; c++){
                //System.out.print(matriz[f][c]+" ");
                   matriz[f][c]=(c+1)*2+(8*f);//x+=2;
                if(matriz[f][c]<=8){
                    System.out.print(" ");
                }
                System.out.print(matriz[f][c]+" ");
            }
            System.out.println("");
        }
    }
}
