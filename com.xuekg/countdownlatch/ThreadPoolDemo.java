package countdownlatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuekg
 * @description
 * @date 2021/9/11 21:01
 **/
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程池异步执行任务..." + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();

        /**
         * 适合短时间内有大量的任务需要处理
         * 任务完成后，达到一定时间后会自动销毁
         */
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("lo"+ Thread.currentThread().getName());
                }
            });
        }

        cachedThreadPool.shutdown();
    }
}
