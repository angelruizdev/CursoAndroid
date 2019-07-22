package ejercicios.Clases1;

public class Cesar1 {
    private double r1,r2,r3,r4,r5,r6;

    public void recta1(int y2, int y1, int x2, int x1){
        r1=y2-y1;
        r2=x2-x1;
        r3=r1/r2;
    }

    public void recta2(int y2, int y1, int x2, int x1){
        r4=y2-y1;
        r5=x2-x1;
        r6=r4/r5;
    }

    public void resultado(){
        if(r3 == r6){
            System.out.println("Recta 1 y Recta 2 son paralelas");
            System.out.println("Pendiente recta 1: "+r3+"\n"+"Pendiente recta 2: "+r6);

        }else {
            System.out.println("Recta 1 y Recta 2 no son paralelas");
            System.out.println("Pendiente recta 1: "+r3+"\n"+"Pendiente recta 2: "+r6);
        }
    }
}
