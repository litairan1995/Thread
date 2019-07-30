import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 门栓机制
 */
public class MyContainer {
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer container = new MyContainer();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2启动");
                if(container.size()!=5){
                    try{
                        latch.await();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");
            }
        },"t2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1启动");
                for (int i=0;i<10;i++){
                    container.add(new Object());
                    System.out.println("add"+i);
                    if(container.size()==5){
                        //打开门栓 让线程t2执行
                        latch.countDown();
                    }
                    try{
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        },"t1").start();
    }

}
