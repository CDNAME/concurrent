package cd24;

/*
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 写一个模拟程序
 * 
 * 分析下面程序可能会产生哪些问题
 * 重复销售？超量销售？
 * 
 * 使用同步容器Vector
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller2 {
    static Vector<String> tickets = new Vector<>();
    
    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号: " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                while(tickets.size() > 0) {
                    //等待一会就回出现超量销售现象，不过Vector有自我保护机制，不会出现重复售票的情况
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }
    
}
