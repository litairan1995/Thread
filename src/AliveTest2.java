public class AliveTest2 extends Thread{

    public AliveTest2(){
        System.out.println(" Count Opreate--begin ");
        System.out.println(" Thread.currentThread().getName()= "+Thread.currentThread().getName());
        System.out.println(" Thread.currentThread().isAlive()= "+Thread.currentThread().isAlive());
        System.out.println(" this.getName()= "+this.getName());
        System.out.println(" this.isAlive()= "+this.isAlive());
        System.out.println(" Count Opreate--end ");
        System.out.println("--------------------------");
    }

    @Override
    public void run() {
        System.out.println(" run--begin ");
        System.out.println(" Thread.currentThread().getName()= "+Thread.currentThread().getName());
        System.out.println(" Thread.currentThread().isAlive()= "+Thread.currentThread().isAlive());
        System.out.println(" this.getName()= "+this.getName());
        System.out.println(" this.isAlive()= "+this.isAlive());
        System.out.println(" run--end ");
    }
    public static void main(String[] args) {
        AliveTest2 c = new AliveTest2();
        Thread thread = new Thread(c);
        System.out.println(" main begin thread isAlive = " +thread.isAlive());
        c.setName("A");
        c.start();
        System.out.println(" main end thread isAlive = " +thread.isAlive());
    }
}
