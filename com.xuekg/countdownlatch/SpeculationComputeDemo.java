package countdownlatch;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SpeculationComputeDemo {

    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(0);

        for (int i = 0; i < 3; i++) {
            new Thread() {

                public void run() {
                    try {
                        Thread.sleep((new Random().nextInt(100) + 1) * 100);
                        System.out.println(Thread.currentThread() + "分配同一个计算任务给不同的机器......");
                        semaphore.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                ;

            }.start();
        }

        semaphore.acquire(1);

        System.out.println("一台机器已经先执行成功了，此时就可以收拢计算结果了");

        // 一下子对同一个数据不同机器上的多个副本，全都分配一个计算任务，谁先计算成功
        // 就采用谁的计算结果就可以了，避免某一台机器因为cpu、内存、磁盘慢读写，导致整体计算进度太慢
    }

}
