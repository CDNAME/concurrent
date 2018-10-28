package cd26;

/*
 * 线程池WorkStealingPool偷工作的线程池，工作窃取，主动找任务完成
 */

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T11_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());     //cpu核数
        
        service.execute(new R(1000));   //Daemon Thread精灵线程，会偷偷在后台运行
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        
        //由于产生的是精灵线程（守护线程，后台线程），主线程不阻塞的话，看不到输出
        System.in.read();
        
    }
    
    static class R implements Runnable {
        int time;
        
        R(int t) {
            this.time = t;
        }
        
        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(time + " " + Thread.currentThread().getName());
        }
    }
    
}
