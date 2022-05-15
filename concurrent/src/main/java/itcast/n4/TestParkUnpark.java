package itcast.n4;

import itcast.n2.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j(topic = "c.TestParkUnpark")
public class TestParkUnpark {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("start...");
            Sleeper.sleep(1);
            log.debug("park...");
            LockSupport.park();
            log.debug("resume...");
        }, "t1");
        t1.start();

        Sleeper.sleep(2);
        log.debug("unpark...");
        //unpark可以在park之前调用，park unpark 是以线程为单位来阻塞和唤醒线程的
        //wait notify 不能先notify
        LockSupport.unpark(t1);
    }
}
