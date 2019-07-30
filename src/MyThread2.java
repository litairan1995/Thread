public class MyThread2 extends Thread{
    int count = 5;

    @Override
    public void run() {
        super.run();
        count--;
        System.out.println("由"+this.currentThread().getName()+"计算，count="+count);
       /* while (count>0){
            count--;
            System.out.println("由"+this.currentThread().getName()+"计算，count="+count);
        }*/
    }

    public static void main(String[] args) {
        MyThread2 thread = new MyThread2();
        Thread a = new Thread(thread,"A");
        Thread b = new Thread(thread,"B");
        Thread c = new Thread(thread,"C");
        Thread d = new Thread(thread,"D");
        Thread e = new Thread(thread,"E");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
