package Labo1;

public class Timer{
    

    public static void countingDown(int time){
        while(time >= 0){
            try{
                Thread.sleep(1000);
                System.out.println(time);
            }
            catch (InterruptedException ie){
                System.out.println(ie);
            }
            time--;
        }
    }
}
