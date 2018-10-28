package cd08;

/*
 * 对业务写方法加锁，对业务读方法不加锁
 * 容易产生脏读问题（dirtyRead）
 */

import java.util.concurrent.TimeUnit;

public class Account {
    String name;
    double balance;     //初始值为0.0
    
    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        this.balance = balance;
    }
    
    public double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        Account a = new Account();
        new Thread(()->a.set("zhangsan", 100.0)).start();
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("zhangsan"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("zhangsan"));
    }
}
