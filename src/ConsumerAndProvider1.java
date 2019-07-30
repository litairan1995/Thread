import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 生产者  消费者
 * @param <T>
 */
public class ConsumerAndProvider1<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        while (lists.size() == MAX) {
            try {
                this.wait();//wait()要和while()一起使用,使用while()因为每次唤醒后都会再次进行判断是否满足条件
                // 而if()不会
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        this.notifyAll();//唤醒所有线程  这里不能使用notify()方法,
        // 因为只唤醒一个线程唤醒的可能是生产者,可能会导致线程无法继续向下进行
    }

    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        ConsumerAndProvider1 provider1 = new ConsumerAndProvider1();
        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        System.out.println(provider1.get());
                    }
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 25; j++) {
                        provider1.put(Thread.currentThread().getName() + " " + j);
                    }
                }
            }, "p" + i).start();
        }
    }
}
