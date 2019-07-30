public class InterruptTest extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            System.out.println(" i= " + (i+1));
        }
    }

    public static void main(String[] args) {
        try{
            InterruptTest test = new InterruptTest();
            test.start();
            Thread.sleep(1000);
            test.interrupt();
            System.out.println("线程停止了1"+test.interrupted());//是指调用该方法的当前线程 即main线程
            System.out.println("线程停止了2"+test.interrupted());
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("main end");
    }

}
