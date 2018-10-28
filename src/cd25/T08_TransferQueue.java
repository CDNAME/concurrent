package cd25;

import java.util.concurrent.LinkedTransferQueue;

public class T08_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
        new Thread(()->{
            try {
                System.out.println("1" + strs.take());  //空，阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        strs.transfer("caaa");    //不放入队列中，直接给消费者
        System.out.println("2" + strs);
        
        strs.put("baaa");   //放入队列中
        
        new Thread(()->{
            try {
                System.out.println("3" + strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
