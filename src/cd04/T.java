package cd04;
/*
 * synchronized关键字
 * 对某个对象加锁
 */

public class T {
    private static int count = 10;
    
    public synchronized static void m() {   //这里等同于synchronized（cd04.T.class）
        count--;
        System.out.println(Thread.currentThread().getName() + "count = " + count);
    }

    public void mm() {
        synchronized(T.class) { //考虑一下这里写synchronized(this)是否可以？N,静态属性或方法没有this引用的存在
            count--;
        }
    }
}