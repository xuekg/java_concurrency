package itcast.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuekg
 * @description
 * @date 2022/5/16 21:52
 **/
@Slf4j(topic = "c.Test24_1")
public class Test24_1 {

    static ReentrantLock room = new ReentrantLock();

    static boolean flag1 = false;

    static Condition condition1 = room.newCondition();

    static boolean flag2 = false;

    static Condition condition2 = room.newCondition();

    public static void main(String[] args) {

        new Thread(() -> {
            room.lock();
            try {
                log.debug("有烟没？{}", flag1);
                while (!flag1) {
                    log.debug("没烟，先歇会！");
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟了，开始干活了");
            } finally {
                room.unlock();
            }
        }, "小南").start();

        new Thread(() -> {
            room.lock();
            try {
                log.debug("有外卖没？{}", flag2);
                while (!flag2) {
                    log.debug("没外卖，先歇会！");
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有外卖了，开始干活了");
            } finally {
                room.unlock();
            }
        }, "小女").start();

        new Thread(() -> {
            room.lock();
            try {
                flag1 = true;
                condition1.signal();
            } finally {
                room.unlock();
            }
        }, "送烟的").start();

        new Thread(() -> {
            room.lock();
            try {
                flag2 = true;
                condition2.signal();
            } finally {
                room.unlock();
            }
        }, "送外卖的").start();
    }

}
