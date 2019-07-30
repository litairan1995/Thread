import java.util.concurrent.TimeUnit;

public class ThreadLocal1 {
    volatile static Person p = new Person();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(p.name);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                p.name = "lisi";
            }
        }).start();
    }
}

class Person {
    String name = "zhangsan";
}
