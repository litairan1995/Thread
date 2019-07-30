
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCAS {

    private AtomicInteger atomicInteger = new AtomicInteger();
    private int i = 0;

    public static void main(String[] args) {
        final TestCAS cas = new TestCAS();
        List<Thread> list = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            list.add(t);
        }
        for (Thread t : list) {
            t.start();
        }
        //等待所有线程运行完毕,main线程等待所有的子线程运行完毕后才结束
        for (Thread t : list) {
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicInteger.get());
        System.out.println(System.currentTimeMillis()-startTime);
    }

    private void safeCount() {
        for (; ; ) {
            int i = atomicInteger.get();
            boolean flag = atomicInteger.compareAndSet(i, ++i);
            if (flag) {
                break;
            }
        }
    }

    private void count() {
        i++;
    }
}
