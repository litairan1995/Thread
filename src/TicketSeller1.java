import java.util.ArrayList;
import java.util.List;

public class TicketSeller1 {
    static List<String> tickets = new ArrayList<>();
    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票 编号"+i);
        }
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (tickets){
                        while (tickets.size()!=0){
                            System.out.println("销售了"+tickets.remove(0));
                        }
                    }
                }
            }).start();
        }
    }

}
