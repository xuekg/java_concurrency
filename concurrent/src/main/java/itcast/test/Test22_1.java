package itcast.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuekg
 * @description
 * @date 2022/5/16 21:29
 **/
@Slf4j(topic = "c.Test22_1")
public class Test22_1 {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        lock.lock();
        try {
            log.debug("main");
            m1();
        } finally {
            lock.unlock();
        }
    }

    public static void m1() {

        lock.lock();
        try {
            log.debug("m1");
            m2();
        } finally {
            lock.unlock();
        }
    }

    public static void m2() {

        lock.lock();
        try {
            log.debug("m2");
        } finally {
            lock.unlock();
        }
    }
}
