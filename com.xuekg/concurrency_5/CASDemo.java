package concurrency_5;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS
 *  自旋 不加锁
 * 缺点：
 *  1.循环时间长 cpu开销大
 *  2.只能保证一个共享变量的操作
 *  3.ABA问题
 * 
 * 总的来说，Atomic实现了高效无锁(底层还是用到排它锁，不过底层处理比java层处理要快很多)与线程安全(volatile变量特性)，
 * CAS一般适用于计数；多线程编程也适用，多个线程执行AtomicXXX类下面的方法，当某个线程执行的时候具有排他性，
 * 在执行方法中不会被打断，直至当前线程完成才会执行其他的线程
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        //unsafe.getAndAddInt(this,valueOffset,1);
        System.out.println(atomicInteger.compareAndSet(5, 2019) + " current data:" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2020) + " current data:" + atomicInteger.get());

    }

}