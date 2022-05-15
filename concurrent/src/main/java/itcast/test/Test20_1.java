package itcast.test;

import itcast.n2.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuekg
 * @description
 * @date 2022/5/15 10:22
 **/
@Slf4j(topic = "c.Test20_1")
public class Test20_1 {

    /**
     * 线程1 等待 线程2 的下载结果
     *
     * @param args
     */
    public static void main(String[] args) {
        GuardedObject1 guardedObject = new GuardedObject1();
        new Thread(() -> {
            log.debug("begin");
            Object o = guardedObject.get(2000);
            log.debug("结果是：{}",o);
        }, "t1").start();

        new Thread(() -> {
            log.debug("begin");
            Sleeper.sleep(1);
            guardedObject.complete(null);
        }, "t2").start();
    }
}

class GuardedObject1 {
    // 结果
    private Object response;

    // 获取结果
    // timeout 表示要等待多久 2000
    public Object get(long timeout) {
        synchronized (this) {
            // 开始时间 15:00:00
            long begin = System.currentTimeMillis();
            // 经历的时间
            long passedTime = 0;
            while (response == null) {
                // 这一轮循环应该等待的时间
                long waitTime = timeout - passedTime;
                // 经历的时间超过了最大等待时间时，退出循环
                if (timeout - passedTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitTime); // 虚假唤醒 15:00:01
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 求得经历时间
                passedTime = System.currentTimeMillis() - begin; // 15:00:02  1s
            }
            return response;
        }
    }

    // 产生结果
    public void complete(Object response) {
        synchronized (this) {
            // 给结果成员变量赋值
            this.response = response;
            this.notifyAll();
        }
    }
}
