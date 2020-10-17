package concurrency_5;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/*
    CyclicBarrier
 */
public class MyTest2 {
    public static void main(String[] agrs) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            //屏障动作 所有线程到达后会执行此屏障动作 barrierAction
            System.out.println("hello world");
        });

        for (int i = 0; i < 2; ++i) {
            for (int n = 0; n < 3; ++n) {
                new Thread(() -> {
                    try {
                        Thread.sleep((long) (Math.random() * 2000));

                        int randomInt = new Random().nextInt(500);

                        System.out.println("hello " + randomInt);

                        cyclicBarrier.await();

                        System.out.println("world " + randomInt);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        }
    }
}