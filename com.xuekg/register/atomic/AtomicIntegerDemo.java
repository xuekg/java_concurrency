package register.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuekg
 * @description
 * @date 2021/8/1 17:13
 **/
public class AtomicIntegerDemo {

    static Integer i = 0;

    static AtomicInteger j = new AtomicInteger(0);

    public static void main(String[] args) {
        synchronizedAdd();
        atomicAdd();
    }

    private static void synchronizedAdd() {
        for (int k = 0; k < 10; k++) {
            new Thread() {
                @Override
                public void run() {
                    synchronized (AtomicIntegerDemo.class) {
                        System.out.println(AtomicIntegerDemo.i++);
                    }
                }
            }.start();

        }
    }

    private static void atomicAdd() {
        for (int k = 0; k < 10; k++) {
            new Thread() {
                @Override
                public void run() {
                    synchronized (AtomicIntegerDemo.class) {
                        System.out.println(AtomicIntegerDemo.j.incrementAndGet());
                    }
                }
            }.start();

        }
    }
}
