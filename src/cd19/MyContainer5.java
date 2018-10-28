package cd19;

/*
 * 实现一个容器，提供两种方法，add, size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2提示并结束
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，该怎么做呢？
 * 
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听可以
 * 
 * 阅读下面的程序，并分析输出信息
 * 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才接受到通知而退出
 * 想想这是为什么？
 * 
 * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 * 整个通信过程比较繁琐
 * 
 * 使用latch(门闩)替代wait,notify来进行通信
 * 好处是通信方式比较简单，同时也可以指定等待时间
 * 使用await和countdown方法替代wait和notify
 * CountDownLatch不涉及锁定，当count的值为0时当前线程继续执行
 * 当不涉及同步，只是设计线程通信的时候，用synchronized + wait/notify就显得太重了
 * 这是应该考虑countdownlatch/cyclicbarrier/semaphore
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyContainer5 {
    //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();
    
    public void add(Object o) {
        lists.add(o);
    }
    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer5 c = new MyContainer5();

        CountDownLatch latch = new CountDownLatch(1);
        
        new Thread(()-> {
            System.out.println("t2启动");
            if(c.size() != 5) {
                try{
                    latch.await();
                    
                    //也可以指定等待时间,指定等待时间结束后，不管latch如何，都会继续执行
                    //latch.await(1000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2结束");
            //通知t1继续执行
        }, "t2").start();
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);

                if(c.size() == 5) {
                    //打开门闩，让t2得以执行
                    latch.countDown();
                }
                
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
    }
}
