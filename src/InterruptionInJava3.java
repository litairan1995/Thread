public class InterruptionInJava3 implements Runnable{
    private volatile static boolean on = false;
    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new InterruptionInJava3(),"InterruptionInJava3");
        //start thread
        testThread.start();
        Thread.sleep(1000);
        InterruptionInJava3.on = true;
        testThread.interrupt();
 
        System.out.println("main end");
 
    }
 
    @Override
    public void run() {
        System.out.println(InterruptionInJava3.on);
        while(!on){
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                System.out.println(InterruptionInJava3.on);
                System.out.println("caught exception right now: "+e);
            }
        }
    }
}