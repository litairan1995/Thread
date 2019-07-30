public class InterruptionInJava1 implements Runnable{
 
    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new InterruptionInJava1(),"InterruptionInJava1");
        //启动线程
        testThread.start();
        Thread.sleep(1000);
        //中断线程
        testThread.interrupt();
 
        System.out.println("main end");
 
    }
 
    @Override
    public void run() {
        while(true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Yes,I am interrupted,but I am still " +
                        "running");
                return;
            }else{
                System.out.println("not yet interrupted");
            }
        }
    }
}