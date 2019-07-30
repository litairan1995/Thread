import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * wait()方法会释放锁  notify()方法不会释放锁
 */
public class MyContainer2 {
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer2 container = new MyContainer2();
        CountDownLatch latch = new CountDownLatch(1);
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2启动");
                synchronized (lock) {
                    if (container.size() != 5) {
                        try{
                            lock.wait();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println("t2结束");
                    lock.notify();
                }
            }
        },"t2").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1启动");
                synchronized (lock){
                    for (int i=0;i<10;i++){
                        container.add(new Object());
                        System.out.println("add"+i);
                        if(container.size()==5){
                            lock.notify();
                            try{
                                lock.wait();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        try{
                            TimeUnit.SECONDS.sleep(1);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        },"t1").start();
    }
}
