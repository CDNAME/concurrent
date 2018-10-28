package cd23;

/*
 * 线程安全的单例模式
 * 采用下面的方法，既不用加锁，也能实现懒加载
 */

import java.util.Arrays;

public class Singleton {
    private Singleton() {
        System.out.println("single");
    }
    
    private static class Inner {
        private static Singleton s = new Singleton();
    }
    
    private static Singleton getSingle() {
        return Inner.s;
    }

    public static void main(String[] args) {
        Thread[] ths =  new Thread[200];
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(()-> {
                System.out.println(Singleton.getSingle());
            });
        }

        Arrays.asList(ths).forEach(o->o.start());
    }
}
