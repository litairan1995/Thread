public class AliveTest extends Thread{

    @Override
    public void run() {
        System.out.println("isAlive="+this.isAlive());
    }

    public static void main(String[] args) {
        AliveTest a = new AliveTest();
        System.out.println(" begin= "+a.isAlive());
        a.start();
        System.out.println(" end= "+a.isAlive());
    }
}
