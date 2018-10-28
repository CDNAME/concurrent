package cd22;

/*
 * TreadLocal线程局部变量,在线程的作用域里声明就可以使用，不声明便不能使用
 * TreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在于ThreadLocal中，避免synchronized的使用
 */

import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //tl.set(new Person());
            System.out.println((tl.get()).name);
            tl.remove();
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
            tl.remove();
        }).start();
    }

    static class Person {
        String name = "zhangsan";
    }
}