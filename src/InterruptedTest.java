public class InterruptedTest {

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            System.out.println("begin"+i);
            Thread.currentThread().interrupt();
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("end"+i);
        }
    }

}
