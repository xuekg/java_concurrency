package register.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author xuekg
 * @description 大量线程并发更新一个原子类的时候，还是有一个自旋的问题，并发性能还是有待提升
 * LongAdder 使用了一个比较简单的原理，解决了 AtomicLong 类，在极高竞争下的性能问题。
 * 但是 LongAdder 的具体实现却非常精巧和细致，分散竞争，逐步升级竞争的解决方案，相当漂亮，值得我们细细品味。
 * @date 2021/8/1 18:21
 **/
public class LongAdderDemo {

    //并发的增加，AtomicLong性能是急剧下降的，耗时是LongAdder的数倍
    public static void main(String[] args) throws Exception {
        testAtomicLongAdder(1, 10000000);
        testAtomicLongAdder(10, 10000000);
        testAtomicLongAdder(100, 10000000);
    }

    static void testAtomicLongAdder(int threadCount, int times) throws Exception {
        System.out.println("threadCount: " + threadCount + ", times: " + times);
        long start = System.currentTimeMillis();
        testLongAdder(threadCount, times);
        System.out.println("LongAdder 耗时：" + (System.currentTimeMillis() - start) + "ms");
        System.out.println("threadCount: " + threadCount + ", times: " + times);
        long atomicStart = System.currentTimeMillis();
        testAtomicLong(threadCount, times);
        System.out.println("AtomicLong 耗时：" + (System.currentTimeMillis() - atomicStart) + "ms");
        System.out.println("----------------------------------------");
    }

    static void testAtomicLong(int threadCount, int times) throws Exception {
        AtomicLong atomicLong = new AtomicLong();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    atomicLong.incrementAndGet();
                }
            }));
        }

        for (Thread thread : list) {
            thread.start();
        }

        for (Thread thread : list) {
            thread.join();
        }

        System.out.println("AtomicLong value is : " + atomicLong.get());
    }

    static void testLongAdder(int threadCount, int times) throws Exception {
        LongAdder longAdder = new LongAdder();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    longAdder.increment();
                }
            }));
        }

        for (Thread thread : list) {
            thread.start();
        }

        for (Thread thread : list) {
            thread.join();
        }

        System.out.println("LongAdder value is : " + longAdder.longValue());
    }
}
