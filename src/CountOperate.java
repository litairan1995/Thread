public class CountOperate extends Thread{

    public CountOperate(){
        System.out.println(" Count Opreate--begin ");
        System.out.println(" Thread.currentThread().getName()= "+Thread.currentThread().getName());
        System.out.println(" this.getName()= "+this.getName());
        System.out.println(" Count Opreate--end ");
    }

    @Override
    public void run() {
        System.out.println(" run--begin ");
        System.out.println(" Thread.currentThread().getName()= "+Thread.currentThread().getName());
        System.out.println(" this.getName()= "+this.getName());
        System.out.println(" run--end ");
    }
    public static void main(String[] args) {
        CountOperate c = new CountOperate();
        Thread thread = new Thread(c);
        c.setName("A");
        c.start();

    }
}
