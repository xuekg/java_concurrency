package concurrency_4;

import java.util.concurrent.TimeUnit;

/**
 * Volatile是Java虚拟机提供的轻量级的同步机制（三大特性-可见性-原子性-有序性） 保证可见性 不保证原子性 禁止指令重排
 * JMM是Java内存模型，也就是Java Memory Model，简称JMM，本身是一种抽象的概念，实际上并不存在，
 * 它描述的是一组规则或规范，通过这组规范定义了程序中各个变量（包括实例字段，静态字段和构成数组对象的元素）的访问方式。 主内存 Java堆中对象实例数据部分
 * 对应于物理硬件的内存 工作内存 Java栈中的部分区域 优先存储于寄存器和高速缓存
 * 
 * 主内存是共享内存区域，所有线程都可以访问，但线程对变量的操作（读取赋值等）必须在工作内存中进行，
 * 首先要将变量从主内存拷贝到自己的工作内存空间，然后对变量进行操作，操作完成后再将变量写会主内存
 */
public class VolatileTest {
    public static void main(String[] args) {
        // 资源类
        ShareData shareData = new ShareData();

        // 子线程 实现了Runnable接口的，lambda表达式
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            // 线程睡眠3秒，假设在进行运算
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 修改number的值
            shareData.setNumberTo100();

            // 输出修改后的值
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + shareData.number);

        }, "子线程").start();

        while (shareData.number == 0) {
            // main线程就一直在这里等待循环，直到number的值不等于零
        }

        // 按道理这个值是不可能打印出来的，因为主线程运行的时候，number的值为0，所以一直在循环
        // 如果能输出这句话，说明子线程在睡眠3秒后，更新的number的值，重新写入到主内存，并被main线程感知到了
        System.out.println(Thread.currentThread().getName() + "\t 主线程感知到了 number 不等于 0");

    }
}

class ShareData {
    //volatile 修饰的关键字，是为了增加多个线程之间的可见性，只要有一个线程修改了内存中的值，其它线程也能马上感知
    volatile int number = 0;
    
    // int number = 0;

    public void setNumberTo100() {
        this.number = 100;
    }
}