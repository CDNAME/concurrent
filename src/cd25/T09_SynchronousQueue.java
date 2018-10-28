package cd25;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T09_SynchronousQueue { //容量为0
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();
        
        new Thread(()-> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        
        strs.put("aaa");    //阻塞等待消费者消费
        //strs.add("aaa");    //不能放入，因为队列没有容量，报异常Queue full，因此不能这么写
        System.out.println(strs.size());
        
    }
}
