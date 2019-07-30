import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(tl.get());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                tl.set(new Person());
            }
        }).start();
    }
}
