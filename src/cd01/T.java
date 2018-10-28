package cd01;
/*
 * synchronized关键字
 * 对某个对象加锁
 */

public class T {
    private int count = 10;

    private Object o = new Object();

    public void m() {
        synchronized(o) {
            count--;
            System.out.println(Thread.currentThread().getName() + "count = " + count);
        }
    }
}