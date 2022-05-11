package itcast.test;

import itcast.n2.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j(topic = "c.Test14")
public class Test14 {

    /**
     * 18:51:24.048 c.Test14 [Thread-0] - park...
     * 18:51:25.047 c.Test14 [Thread-0] - 打断状态：true
     * 18:51:25.048 c.Test14 [Thread-0] - park...
     */
    private static void test4() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                log.debug("park...");
                LockSupport.park();
                log.debug("打断状态：{}", Thread.interrupted());
                log.debug("打断后的状态：{}", Thread.currentThread().isInterrupted());
            }
        });
        t1.start();


        Sleeper.sleep(1);
        t1.interrupt();
    }

    private static void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            //打断标记为true时，park是没用的
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();

        Sleeper.sleep(1);
        t1.interrupt();

    }

    public static void main(String[] args) throws InterruptedException {
        test3();
    }
}
