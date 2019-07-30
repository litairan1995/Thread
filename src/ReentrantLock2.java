import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantLock用于代替synchronized
 * reentrantLock是手动锁,必须要手动释放锁,还可以作为公平锁
 * 使用synchronized锁定的话,如果遇到异常,jvm会自动释放锁,但是Lock必须手动释放锁,因此经常在finally中进行锁的释放
 */

public class ReentrantLock2 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    lock.lock();
                    System.out.println("t1 start");
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                    System.out.println("t2 end");
                }catch (Exception e){
                    System.out.println("interrupted1");
                }finally {
                    lock.unlock();
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();//响应线程的interrupt()方法
                    System.out.println("t2 start");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("t2 end");
                }catch (Exception e){
                    System.out.println("interrupted2");
                }
            }
        }) ;
        t2.start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        t2.interrupt();//打断线程的等待
    }
}
