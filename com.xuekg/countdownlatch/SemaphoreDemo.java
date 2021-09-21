package countdownlatch;

import java.util.concurrent.Semaphore;

/**
 * @author xuekg
 * @description
 * @date 2021/9/7 20:48
 **/
public class SemaphoreDemo {

    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(0);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("一个线程完成了任务");
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


        semaphore.acquire(1);
        System.out.println("等等一个线程完成任务即可。。。");
    }
}
