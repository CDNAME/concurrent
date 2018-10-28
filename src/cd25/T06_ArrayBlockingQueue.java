package cd25;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
    
    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);;
        }

        //strs.put("aaa");    //满了就会等待，程序阻塞
        //strs.add("aaa");    //满了不会等待，会直接放入，报异常Queue full
        //boolean b = strs.offer("aaa");      //满了不会放入，也不报异常
        //System.out.println(b);
        strs.offer("aaa", 3, TimeUnit.SECONDS);    //等待时间，再放入
        
        System.out.println(strs);
    }
}
