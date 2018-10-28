package cd15;

/*
 * 解决同样的问题的更高效的方法，使用AtomXX类
 * AtomXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T {
//    volatile int count = 0;
    AtomicInteger count = new AtomicInteger(0);
    
    /*synchronized*/ void m() {
        for (int i = 0; i < 10000; i++) {
            //if (count.get()< 10000)    //不能保证多个方法连续调用是原子性的,之间不能对count值进行修改 判定操作
            count.incrementAndGet();    //相当于原子性的count++
        }
    }

    public static void main(String[] args) {
        T t = new T();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread"+i));
        }

        threads.forEach((o)->o.start());

        //等待所有线程结束
        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}
