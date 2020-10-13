package concurrency_4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示volatile 不保证原子性
 */
public class VolatileAtomicity {

    public static volatile int number = 0;

    public static AtomicInteger atomicInteger = new AtomicInteger();

    public static void increase() {
        //在执行number++这行代码时，即使使用volatile修饰number变量，在执行期间，还是有可能被其他线程修改，没有保证原子性
        number++;
        atomicInteger.getAndIncrement();
    }

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increase();
                }
            }, String.valueOf(i)).start();
        }

        // 当所有累加线程都结束
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(number);
        System.out.println(atomicInteger.get());
    }
}