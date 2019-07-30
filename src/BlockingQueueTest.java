import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞式队列
 * LinkedBlockingQueue 链表实现的无界的阻塞队列
 */
public class BlockingQueueTest {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        for (int i = 0; i < 10; i++) {
            try{
                queue.put("a"+i);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
