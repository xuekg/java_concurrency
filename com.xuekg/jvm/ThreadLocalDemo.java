package jvm;

/**
 * @author xuekg
 * @description
 * @date 2021/8/30 21:13
 **/
public class ThreadLocalDemo {

    public static void main(String[] args) {
        ThreadLocal<Long> threadLocal = new ThreadLocal<>();

        new Thread(() -> {
            threadLocal.set(2L);
            System.out.println("线程1---" + threadLocal.get());
        }, "thread1").start();

        new Thread(() -> {
            threadLocal.set(4L);
            System.out.println("线程2---" + threadLocal.get());
        }, "thread2").start();
    }
}
