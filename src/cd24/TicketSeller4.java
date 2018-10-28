package cd24;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 写一个模拟程序
 * 
 * 分析下面程序可能会产生哪些问题
 * 重复销售？超量销售？
 * 
 * 使用队列解决该问题
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();
    
    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号: " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                while(true) {
                    String s = tickets.poll();
                    if(s == null) break;
                    else System.out.println("销售了--" + s);
                }
            }).start();
        }
    }
    
}
