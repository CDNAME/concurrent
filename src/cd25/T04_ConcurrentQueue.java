package cd25;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T04_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            strs.offer("a" + i);    //入队
        }

        System.out.println(strs);
        
        System.out.println(strs.size());

        System.out.println(strs.poll());    //出队-1
        System.out.println(strs.size());
        
        System.out.println(strs.peek());    //找到-0
        System.out.println(strs.size());
        
        //双端队列Deque
    }
}
