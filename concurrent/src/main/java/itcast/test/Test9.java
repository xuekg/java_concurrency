package itcast.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test9")
//调用 yield 会让当前线程从 Running 进入 Runnable 就绪状态，然后调度执行其它线程
//具体的实现依赖于操作系统的任务调度器
//让出CPU时间片
//都不能真正的去控制线程的调度，只有一个提示作用（调度器）
public class Test9 {

    public static void main(String[] args) {
        Runnable task1 = () -> {
            int count = 0;
            for (; ; ) {
                System.out.println("---->1 " + count++);
            }
        };
        Runnable task2 = () -> {
            int count = 0;
            for (; ; ) {
//                Thread.yield();
                System.out.println("              ---->2 " + count++);
            }
        };
        Thread t1 = new Thread(task1, "t1");
        Thread t2 = new Thread(task2, "t2");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }
}
