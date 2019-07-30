public class MyThread extends Thread{

    @Override
    public void run() {
        super.run();
        System.out.println("run方法运行了");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("main方法运行了");
    }
}
