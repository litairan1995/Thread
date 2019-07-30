import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingQueue1 {
    static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    static Random r = new Random();

    public static void main(String[] args) {
        //生产者线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        queue.put("a" + i);//如果满了就会等待
                        TimeUnit.SECONDS.sleep(r.nextInt(1));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "p1").start();
        //消费者线程
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (;;){
                        try{
                            System.out.println(Thread.currentThread().getName()+" take "+queue.take());
                            //如果空了就会等待
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            },"c"+i).start();
        }
    }
}
