package itcast.n4;

import itcast.n2.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test8Locks")
public class Test8Locks {
    public static void main(String[] args) {
        Number n1 = new Number();
        Number n2 = new Number();
        new Thread(() -> {
            log.debug("begin");
            n1.a();
        }).start();
        new Thread(() -> {
            log.debug("begin");
            n2.b();
        }).start();
    }
}
@Slf4j(topic = "c.Number")
class Number{
    public synchronized void a() {
        Sleeper.sleep(1);
        log.debug("1");
    }
    public synchronized void b() {
        log.debug("2");
    }
}
