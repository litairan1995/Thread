import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantLock用于代替synchronized
 * reentrantLock是手动锁,必须要手动释放锁
 * 使用synchronized锁定的话,如果遇到异常,jvm会自动释放锁,但是Lock必须手动释放锁,因此经常在finally中进行锁的释放
 */

public class ReentrantLock1 {
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用tryLock进行尝试锁定,不管锁定与否,方法都将继续执行
     * 可以根据tryLock的返回值来判定是否锁定
     * 也可以指定tryLock的时间,由于tryLock(time)抛出异常,所以要注意unlock的处理,必须放到finally中
     */
    void m2() {
//        boolean locked = lock.tryLock();
//        System.out.println("m2..."+locked);
//        if(locked) lock.unlock();
        boolean locked = false;
        try {
            locked = lock.tryLock(5,TimeUnit.SECONDS);
            System.out.println("m2..."+locked);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(locked)lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock1 lock1 = new ReentrantLock1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock1.m1();
            }
        }, "m1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock1.m2();
            }
        }, "m2").start();
    }
}
