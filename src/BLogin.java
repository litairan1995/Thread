public class BLogin implements Runnable {
    @Override
    public void run() {
        LoginThread.doPost("b", "bb");
    }

    public static void main(String[] args) {
        ALogin a = new ALogin();
        Thread thread1 = new Thread(a);
        thread1.start();
        BLogin b = new BLogin();
        Thread thread2 = new Thread(b);
        thread2.start();
    }

}
