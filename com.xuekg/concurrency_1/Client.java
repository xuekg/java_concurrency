package concurrency_1;

/**
 * 编写一个多线程程序，实现目标如下：
 * 1.存在一个对象，有一个int类型的成员变量，初始值为0
 * 2.创建两个线程，一个让变量加1，一个让变量减1
 * 3.输出该变量每次变化后的值，结果应该为10101010......
 */
public class Client {

    public static  void main(String[] args) {
        MyObject myObject = new MyObject();

        /**
         * 两个线程的情况下，是符合预期的 1-0-1-0-1-0-1-0
         */
        IncreaseThread increaseThread = new IncreaseThread(myObject);
        DecreaseThread decreaseThread = new DecreaseThread(myObject);
        // increaseThread.start();
        // decreaseThread.start();

        /**
         * 四个线程的情况下，不符合预期，会有负数出现，同时程序被挂起，JVM未退出
         * 关键在于nofity()方法，不能确定此方法唤醒的是对应的方法
         */
        IncreaseThread increaseThread1 = new IncreaseThread(myObject);
        IncreaseThread increaseThread2 = new IncreaseThread(myObject);
        DecreaseThread decreaseThread1 = new DecreaseThread(myObject);
        DecreaseThread decreaseThread2 = new DecreaseThread(myObject);
        increaseThread1.start();
        increaseThread2.start();
        decreaseThread1.start();
        decreaseThread2.start();
    }
    
}