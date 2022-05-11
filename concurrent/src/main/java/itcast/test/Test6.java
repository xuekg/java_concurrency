package itcast.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test6")
//调用 sleep 会让当前线程从 Running 进入 Timed Waiting 状态（阻塞）
public class Test6 {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        //RUNNABLE
        log.debug("t1 state: {}", t1.getState());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //TIMED_WAITING
        log.debug("t1 state: {}", t1.getState());
    }
}
