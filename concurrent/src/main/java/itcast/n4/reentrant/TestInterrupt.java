package itcast.n4.reentrant;

import itcast.n2.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.TestInterrupt")
public class TestInterrupt {
    public static void main(String[] args) {
        test2();
    }

    private static void test2() {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            log.debug("启动...");
            lock.lock();
            try {
                log.debug("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");


        lock.lock();
        log.debug("获得了锁");
        t1.start();
        try {
            Sleeper.sleep(1);
            t1.interrupt();
            log.debug("执行打断");
            Sleeper.sleep(1);
        } finally {
            log.debug("释放了锁");
            lock.unlock();
        }
    }

    private static void test1() {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            log.debug("启动...");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("等锁的过程中被打断");
                return;
            }
            try {
                log.debug("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");


        lock.lock();
        log.debug("获得了锁");
        t1.start();
        try {
            Sleeper.sleep(1);
            t1.interrupt();
            log.debug("执行打断");
        } finally {
            lock.unlock();
        }
    }
}
