package cd26;

/*
 * 线程池CachedThreadPool缓存线程池，根据需要启动新线程
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T08_CachedPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 3; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);
        
        TimeUnit.SECONDS.sleep(70);

        System.out.println(service);
        
        service.shutdown();
        
    }
}
