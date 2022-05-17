package itcast.test;

import itcast.n2.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test32")
public class Test32 {
    // 易变
    static boolean run = true;

    /**
     * JVM 会尽力保证内存的可见性，即便这个变量没有被同步关键字修饰。
     * 也就是说，只要 CPU 有时间，JVM 会尽力去保证变量值的更新。
     * 这种与 volatile 关键字的不同在于，volatile 关键字会强制的保证线程的可见性。
     * 而不加这个关键字，JVM 也会尽力去保证可见性，但是如果 CPU 一直有其他的事情在处理，就不能保证变量的更新。
     *
     * 第一段代码使用了while死循环，占用了CPU的大量时间，第二段代码在while死循环中增加了System.out.println()，
     * 由于是同步的，在IO过程中，CPU空闲时间比较多就有可能有时间去保证内存的可见性。
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                // TODO: 2022/5/17 程序在运行一段时间后退出了
                System.out.println("hello");
                if (!run) {
                    break;
                }
            }
        });
        t.start();

        Sleeper.sleep(1);
        run = false; // 线程t不会如预想的停下来
    }
}
