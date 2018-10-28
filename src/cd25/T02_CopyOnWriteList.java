package cd25;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * 写时复制容器copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 */

public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> lists = 
                //new ArrayList<>();  //这个会出并发问题！
                //new Vector();
                new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] ths = new Thread[100];

        for (int i = 0; i < ths.length; i++) {
            /*
            ths[i] = new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    lists.add("a" + r.nextInt(10000));
                }
            });
            */
            
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        lists.add("a" + r.nextInt(10000));
                    }
                }
            };
            ths[i] = new Thread(task);
        }
        
        runAndComputerTime(ths);
        System.out.println(lists.size());
        
    }

    static void runAndComputerTime(Thread[] ths) {
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t->t.start());
        Arrays.asList(ths).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end= System.currentTimeMillis();
        System.out.println(end - start);
    }
}
